package data_structures;

import java.util.Iterator;
import java.lang.Integer;

@SuppressWarnings("hiding")
public class DynamicArrayImpl<Integer> implements Iterable<Integer> {

	public static void main(String[] args) {
		System.out.println("dynamic array");
	}
	
	private int[] arr;
	private int len = 0;
	private int capacity = 0;
	
	public DynamicArrayImpl() {
		this(16);
	}
	
	public DynamicArrayImpl(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity;
		arr = new int[capacity];
	}
	
	public int size() {
		return len;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int get(int index) {
		return arr[index];
	}
	
	public void set(int index, int elem) {
		arr[index] = elem;
	}
	
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			arr[i] = 0;
		}
		len = 0;
	}
	
	public void add(int elem) {
		if (len + 1 >= capacity) {
			if (capacity == 0) capacity = 1;
			else capacity *= 2;
			int[] new_arr = new int[capacity];
			for (int i = 0; i < len; i++) {
				new_arr[i] = arr[i];
			}
			arr = new_arr;
		}
		arr[len++] = elem;
	}
	
	public int removeAt(int rm_index) {
		if (rm_index >= len && rm_index < 0) throw new IndexOutOfBoundsException();
		int data = arr[rm_index];
		int[] new_arr = new int[len - 1];
		for (int i = 0, j = 0; i < len; i++, j++) {
			if (i == rm_index) j--;
			else new_arr[j] = arr[i];
		}
		arr = new_arr;
		capacity = --len;
		return data;
	}
	
	public boolean remove(int elem) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == elem) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	public int indexOf(int elem) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == elem) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean contains(int elem) {
		return indexOf(elem) != -1;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer> () {
			int index = 0;
			public boolean hasNext() {
				return index < len;
			}
			@SuppressWarnings("unchecked")
			public Integer next() {
				return (Integer) java.lang.Integer.valueOf(arr[index++]);
			}
		};
	}
	
	@Override
	public String toString() {
		if (len == 0) return "[]";
		else {
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < len - 1; i++) {
				sb.append(arr[i] + ", ");
			}
			return sb.append(arr[len - 1] +"]").toString();
		}
	}

}
