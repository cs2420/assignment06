package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	// number of elements in the list
	private int size;
	// first node
	private Node<E> first;
	// last node
	private Node<E> last;

	// constructor
	public DoublyLinkedList() {
		size = 0;

	}

	// Node class
	public static final class Node<E> {
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
		
		public E getData(){
			return data;
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E element) {
		Node<E> n = new Node<E>(element);
		if (size == 0) {
			first = n;
			last = n;
		} else {
			n.next = first;
			first.previous = n;
			first = n;

		}
		size++;

	}

	@Override
	public void addLast(E o) {
		Node<E> n = new Node<E>(o);
		if (size == 0) {
			first = n;
			last = n;
		} else {
			n.previous = last;
			last.next = n;
			last = n;

		}
		size++;

	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> n = new Node<E>(element);

	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return first.data;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return last.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(E element) {
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
	
	// Helper method, returns the node at index of i
	Node<E> getNode(int i) {
		Node<E> n;
		if (i < size / 2) {
			n = first;
			// n less than size/2, iterate from start
			while (i > 0) {
				i--;
				n = n.next;
			}
		} else {
			n = last;
			// n greater than size/2, iterate from end
			while (i < size-1) {
				i++;
				n = n.previous;
			}
		}
		return n;
	}

}
