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
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> inputNode = new Node<E>(element);
		if (index == 0) {
			addFirst(element);
		} else if (index == size) {
			addLast(element);
		} else {
			// The node at the index will be shifted after the input node
			Node<E> atIndex = getNode(index);
			// input node's next becomes the atIndex node.
			inputNode.next = atIndex;
			// input node's previous becomes the atIndex node previous
			inputNode.previous = atIndex.previous;
			// atIndex node replaced by input node now that next and previous
			// are set
			atIndex.previous.next = inputNode;
			// atIndex node's previous changes to the input node
			inputNode.next.previous = inputNode;
			size++;
		}

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
		if (index < 0 || index > size - 1) {
			throw new NoSuchElementException();
		}
		return getNode(index).data;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E result = first.data;
		if (size == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.previous = null;
		}
		size--;
		return result;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E result = last.data;
		if (size == 1) {
			first = null;
			last = null;
		} else {
			last = last.previous;
			last.next = null;
		}
		size--;
		return result;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> n = getNode(index);
		E result = n.data;
		if(size == 1 || index == 0){
			removeFirst();
		}
		else if(index == size-1){
			removeLast();
		}
		else{
			n.previous.next = n.next;
			n.next.previous = n.previous;
			size--;
		}
		return result;
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
		return size == 0;
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
	private Node<E> getNode(int index) {
		Node<E> n;
		int i = 0;
		if (index < size / 2) {
			n = first;
			// n less than size/2, iterate from start
			while (i < index) {
				i++;
				n = n.next;
			}
		} else {
			index = size - 1 - index;
			n = last;
			// n greater than size/2, iterate from end
			while (i < index) {
				i++;
				n = n.previous;
			}
		}
		return n;
	}

}
