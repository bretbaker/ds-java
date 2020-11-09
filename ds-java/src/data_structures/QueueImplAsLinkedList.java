package data_structures;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueImplAsLinkedList<T> implements Iterable<T> {
	
	private LinkedList<T> list = new LinkedList<T>();
	
	public QueueImplAsLinkedList() {}
	
	public QueueImplAsLinkedList(T firstElem) {
		offer(firstElem);
	}
	
	// return size of the queue
	public int size() {
		return list.size();
	}
	
	// returns whether or not the queue is empty
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// peek the element at the front of the queue 
	// throws an error if the queue is empty
	public T peek() {
		if (isEmpty()) throw new RuntimeException("Queue Empty");
		return list.peekFirst();
	}

	// poll an element from the front of the queue
	// throws an error if queue is empty
	public T poll() {
		if (isEmpty()) throw new RuntimeException("Queue Empty");
		return list.removeFirst();
	}
	
	// add an element to the back of the queue
	public void offer(T elem) {
		list.addLast(elem);
	}
	
	// return at iterator to allow the user to traverse
	// through the elements found inside the queue
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
}
