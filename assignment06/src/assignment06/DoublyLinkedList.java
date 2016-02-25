package assignment06;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a Linked List.
 * 
 * @author Connor Ottenbacher and Doug Garding
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private int size; // number of elements in the list
	private Node<E> first; // first node
	private Node<E> last; // last node

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

		E data; // The element contained in the node.
		Node<E> next; // The next list entry, null if this is last.
		Node<E> previous; // The previous list entry, null if this is first.

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

	/**
	 * Provides an iterator for the DoublyLinkedList.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private boolean canRemove = false;
			private Node<E> currentNode;
			private int sizeForIterator = size;

			public boolean hasNext() {
				// List has at least one item, returns true
				if (currentNode == null && first != null) {
					return true;
				}
				// List is empty, returns false
				if (currentNode == null && first == null) {
					return false;
				}
				// Returns false if the iterator has reached the last node
				return currentNode.next != null;
			}

			public E next() {

				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				// If the current node has not yet been set and the list has at
				// least one item, sets current node to first
				if (currentNode == null) {
					currentNode = first;
					canRemove = true;
					return currentNode.data;
				}
				// Sets the current node to the next node
				canRemove = true;
				currentNode = currentNode.next;
				return currentNode.data;
			}

			public void remove() {
				// If next() hasn't been called, can't remove
				if (!canRemove)
					throw new IllegalStateException();
				// If the list's size has been modified by anything other than
				// Iterators remove method, will
				// throw concurrentModificationException
				if (size != sizeForIterator)
					throw new ConcurrentModificationException();
				// removes the currentNode, throws an exception if this node was
				// removed without Iterators remove method
				try {
					canRemove = false;
					removeNode(currentNode);
					currentNode = currentNode.previous;
					sizeForIterator--;
				} catch (NullPointerException e) {
					throw new ConcurrentModificationException();
				}
			}
		};
	}

	/**
	 * Inserts the specified element at the beginning of the list.
	 */
	@Override
	public void addFirst(E element) {
		Node<E> newNode = new Node<E>(element);
		// If list is empty
		if (size == 0) {
			first = newNode;
			last = newNode;
		}
		// If list is not empty
		else {
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}
		size++;

	}

	/**
	 * Inserts the specified element at the end of the list.
	 */
	@Override
	public void addLast(E o) {
		Node<E> newNode = new Node<E>(o);
		// If list is empty
		if (size == 0) {
			first = newNode;
			last = newNode;
		}
		// If list is not empty
		else {
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
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
		// If there's only one item in linked list
		if (size == 1) {
			first = null;
			last = null;
		}
		// If there's multiple items in linked list
		else {
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
		// If there's only one item in linked list
		if (size == 1) {
			first = null;
			last = null;
		}
		// If there's multiple items in linked list
		else {
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

		Node<E> node = getNode(index);
		E result = node.data;
		removeNode(node);
		return result;
	}

	/**
	 * Removes the Parameter node from this DoublyLinkedList
	 */
	private void removeNode(Node<E> e) {
		// If removing first item
		if (size == 1 || e == first) {
			removeFirst();
		}
		// If removing last item
		else if (e == last) {
			removeLast();
		}
		// Find and remove item
		else {
			e.previous.next = e.next;
			e.next.previous = e.previous;
			size--;
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(E element) {
		// Check the element of each node, starting at beginning
		Node<E> currentNode = this.first;
		for (int i = 0; i < this.size; i++) {
			if (currentNode.data.equals(element))
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
		// Check the element of each node, starting at back end
		Node<E> currentNode = this.last;
		for (int i = this.size - 1; i >= 0; i--) {
			if (currentNode.data.equals(element))
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
		this.first = null;
		this.last = null;
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
		for (int i = 0; i < this.size; i++) {
			elementArray[i] = currentNode.data;
			currentNode = currentNode.next;
		}

		return elementArray;
	}

	/**
	 * Helper method, returns the node at the specified index.
	 */
	private Node<E> getNode(int index) {
		Node<E> node;
		int i = 0;

		// If the index is in the first half of the list
		if (index < size / 2) {
			node = first;
			// node less than size/2, iterate from start
			while (i < index) {
				i++;
				node = node.next;
			}
		}

		// If the index is in the second half of the list
		else {
			index = size - 1 - index;
			node = last;
			// node greater than size/2, iterate from end
			while (i < index) {
				i++;
				node = node.previous;
			}
		}
		return node;
	}

}
