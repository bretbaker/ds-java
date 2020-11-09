package data_structures;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class StackImplAsLinkedList<T> implements Iterable<T> {

	LinkedList<T> list = new LinkedList<T>();
	
	// create an empty stack
	public StackImplAsLinkedList() {}
	
	// create a stack with an initial element
	public StackImplAsLinkedList(T firstElem) {
		push(firstElem);
	}
	
	// return the number of elements in the stack
	public int size() {
		return list.size();
	}
	
	// check if the stack is empty
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// push an element on the stack
	public void push(T elem) {
		list.addLast(elem);
	}
	
	// pop an element off the stack 
	// throws an error if the stack is empty
	public T pop() {
		if (isEmpty()) throw new EmptyStackException();
		return list.removeLast();
	}
	
	// peek the top of the stack without removing an element
	// throws and exception if the stack is empty
	public T peek() {
		if (isEmpty()) throw new EmptyStackException();
		return list.peekLast();
	}
	
	// allow users to iterate through the stack using an iterator
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
