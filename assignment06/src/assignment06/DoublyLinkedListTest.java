package assignment06;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.NoSuchElementException;

public class DoublyLinkedListTest {

	@Test
	public void AddFirstEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertEquals(0, list.size());
		list.addFirst(5);
		assertEquals((Integer)5, list.getFirst());
		assertEquals(1, list.size());
	}
	@Test
	public void AddLastEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertEquals(0, list.size());
		list.addLast(5);
		assertEquals((Integer)5, list.getLast());
		assertEquals(1, list.size());
	}
	@Test
	public void GetNode() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		
		assertEquals((Integer) 6, list.get(1));
	}
	@Test
	public void AddFirstLastMix() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		list.addLast(6);
		assertEquals(5, list.size());
		assertEquals((Integer)6, list.getLast());
		assertEquals((Integer)2, list.getFirst());
	}
	@Test (expected=NoSuchElementException.class) 
	public void testGetFirstEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.getFirst();
	}
	@Test
	public void AddAtIndex() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.add(3, 1);
		
		assertEquals((Integer) 9, list.get(5));
	}
	@Test
	public void RemoveFirst() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		
		assertEquals((Integer) 0, list.removeFirst());
		for(int i = 0; i<list.size(); i++){
			Integer result = new Integer(i+1);
			assertEquals(result, list.get(i));
		}
	}
	@Test
	public void RemoveLast() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		
		assertEquals((Integer) 4, list.removeLast());
		for(int i = 0; i<list.size(); i++){
			Integer result = new Integer(i);
			assertEquals(result, list.get(i));
		}
	}
	@Test
	public void RemoveIndex() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		
		assertEquals((Integer) 3, list.remove(3));
		for(int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}

}
