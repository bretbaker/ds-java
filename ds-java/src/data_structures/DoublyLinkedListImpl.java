package data_structures;

import java.util.Iterator;

public class DoublyLinkedListImpl<T> implements Iterable<T> {

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	// internal node class to represent data
	private static class Node<T> {
		private T data;
		private Node<T> prev;
		private Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	// empty this linked list, O(n)
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}
	
	// return the size of this linked list
	public int size() {
		return size;
	}
	
	// is this linked list empty?
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// add a node to the tail of the linked list, O(1)
	public void addLast(T elem) {
		if (isEmpty()) {
			head = tail = new Node<T>(elem, null, null);
		} else {
			tail.next = new Node<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	// add an element to the tail of the linked list, O(1)
	public void add (T elem) {
		addLast(elem);
	}
	
	// add an element to the beginning of this linked list, O(1)
	public void addFirst(T elem) {
		if (isEmpty()) {
			head = tail = new Node<T>(elem, null, null);
		} else {
			head.prev = new Node<T>(elem, null, head);
			head = head.prev;
		}
		size++;
	}
	
	// check the value of the first node if it exists, O(1)
	public T peekFirst() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		return head.data;
	}
	
	// check the value of the last node if it exists, O(1)
	public T peekLast() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		return tail.data;
	}
	
	// remove the first value at the head of the linked list, O(1)
	public T removeFirst() {
		// can't remove data from empty list
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		// extract the data at the head and move the head pointer forward one node
		T data = head.data;
		head = head.next;
		--size;
		// if the list is now empty, set the tail to null
		if (isEmpty()) {
			tail = null;
		}
		// do a memory cleanup of the previous node
		else head.prev = null;
		// return the data that was at the first node we just removed
		return data;
	}
	
	// remove the last value at the end of the tail of the linked list, O(1)
	public T removeLast() {
		// can't remove data from an empty list
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		// extract the data at the tail and move the tail pointer backward one node
		T data = tail.data;
		tail = tail.prev;
		--size;
		// if the list is now empty, set the head to null
		if (isEmpty()) {
			head = null;
		}
		// do a memory cleanup of the node that was just removed
		else tail.next = null;
		// return the data that was in the last node we just removed
		return data;
	}
	
	// remove an arbitrary node from the linked list, O(1)
	private T remove(Node<T> node) {
		// if the node to remove is either at the head or tail, handle those independently
		if (node.prev == null) return removeFirst();
		if (node.next == null) return removeLast();
		// make the pointers of adjacent nodes skip over 'node'
		node.next.prev = node.prev;
		node.prev.next = node.next;
		// temporarily store the data we want to return
		T data = node.data;
		// memory cleanup
		node.data = null;
		node = node.prev = node.next = null;
		--size;
		// return the data in the node we just removed
		return data;
	}
	
	// remove a node at a particular index, O(n)
	public T removeAt(int index) {
		// make sure the index provided is valid
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		int i;
		Node<T> trav;
		// search from the front of the list
		if (index < size / 2) {
			for (i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		}
		// search from the back of the list
		else {
			for (i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		return remove(trav);
	}
	
	// remove a particular value in the linked list, O(n)
	public boolean remove(Object obj) {
		Node<T> trav = head;
		// support searching for null
		if (obj == null) {
			for (trav = head; trav != null; trav = trav.next) {
				if (trav.data == null) {
					remove(trav);
					return true;
				}
			}
		}
		// search for non-null object
		else {
			for (trav = head; trav != null; trav = trav.next) {
				if (obj.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}
	
	// find the index of a particular value in the linked list, O(n)
	public int indexOf(Object obj) {
		int index = 0;
		Node<T> trav = head;
		// support searching for null
		if (obj == null) {
			for (trav = head; trav != null; trav = trav.next, index++) {
				if (trav.data == null) {
					return index;
				}
			}
		}
		// search for non-null object
		else {
			for (trav = head; trav != null; trav = trav.next, index++) {
				if (obj.equals(trav.data)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	// check if a value is contained within the linked list
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> trav = head;
			
			@Override
			public boolean hasNext() {
				return trav != null;
			}
			
			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> trav = head;
		while (trav != null) {
			sb.append(trav.data + ", ");
			trav = trav.next;
		}
		sb.append(" ]");
		return sb.toString();
	}

}
