package assignment06;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.NoSuchElementException;

public class DoublyLinkedListTest {

	@Test
	public void testAddFirstEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertEquals(0, list.size());
		list.addFirst(5);
		assertEquals(5, list.getFirst());
		assertEquals(1, list.size());
	}
	@Test (expected=NoSuchElementException.class) 
	public void testGetFirstEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.getFirst();
	}

}
