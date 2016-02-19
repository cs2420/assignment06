package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List, Iterable {

	// number of elements in the list
	private int size;
	// first node
	private Node first;
	// last node
	private Node last;

	// constructor
	public DoublyLinkedList() {
		size = 0;

	}

	// Node class
	private static final class Node<E> {
		/** The element in the list. */
		E data;

		/** The next list entry, null if this is last. */
		Node<E> next;

		/** The previous list entry, null if this is first. */
		Node<E> previous;

		/**
		 * Construct an entry.
		 * 
		 * @param data
		 *            the list element
		 */
		Node(E data) {
			this.data = data;
		}
	} 

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(Object element) {
		Node<E> n = new Node((E) element);
		if (size == 0) {
			first = n;
			last = n;
		}
		else{
			n.next = first;
			first.previous = n;
			first = n;
			
		}
		size++;

	}

	@Override
	public void addLast(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		return first.data;
	}

	@Override
	public Object getLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object element) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
