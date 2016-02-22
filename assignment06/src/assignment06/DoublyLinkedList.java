package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a Linked List.
 * 
 * @author Connor and Doug Garding
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	// number of elements in the list
	private int size;
	// first node
	private Node<E> first;
	// last node
	private Node<E> last;

	/**
	 * Default constructor for a doubly linked list.
	 */
	public DoublyLinkedList() {
		size = 0;
	}

	/**
	 * Node Class that stores elements for a linked list.
	 *
	 * @param <E>
	 */
	private static final class Node<E> {
		// The element in the list.
		E data;
		// The next list entry, null if this is last.
		Node<E> next;
		// The previous list entry, null if this is first.
		Node<E> previous;

		/**
		 * Default constructor for a node.
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

	/**
	 * Inserts the specified element at the beginning of the list.
	 */
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

	/**
	 * Inserts the specified element at the end of the list.
	 */
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

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size())
	 */
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

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return first.data;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return last.data;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size - 1) {
			throw new NoSuchElementException();
		}
		return getNode(index).data;
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty.
	 */
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

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty.
	 */
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

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size())
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> n = getNode(index);
		E result = n.data;
		if (size == 1 || index == 0) {
			removeFirst();
		} else if (index == size - 1) {
			removeLast();
		} else {
			n.previous.next = n.next;
			n.next.previous = n.previous;
			size--;
		}
		return result;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(E element) {

		// If the DoublyLinkedList is empty
		if (this.size == 0)
			return -1;

		// Check the element of each node, starting at beginning
		Node<E> currentNode = this.first;
		for (int i = 0; i < this.size; i++){
			if (currentNode.data == element)
				return i;
			currentNode = currentNode.next;
		}

		// If element was not found
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 */
	public int lastIndexOf(E element) {
		// If the DoublyLinkedList is empty
		if (this.size == 0)
			return -1;

		// Check the element of each node, starting at end
		Node<E> currentNode = this.last;
		for (int i = this.size - 1; i >= 0; i--){
			if (currentNode.data == element)
				return i;
			currentNode = currentNode.previous;
		}
	
		// If element was not found
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		this.first.next = null;
		this.first.data = null;
		this.last.previous = null;
		this.last.data = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 */
	@Override
	public Object[] toArray() {

		Object elementArray[] = new Object[this.size];
		Node<E> currentNode = this.first;
		
		// Add each element in DoublyLinkedList to array
		for (int i = 0; i < this.size; i++){
			elementArray[i] = currentNode.data;
			currentNode = currentNode.next;
		}

		return elementArray;
	}

	/**
	 * Helper method, returns the node at index of i
	 */
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
