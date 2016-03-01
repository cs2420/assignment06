package assignment06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * A testing class to test the DoublyLinkedList.
 * 
 * @author Doug Garding and Connor Ottenbacher
 */
public class DoublyLinkedListTest {
	DoublyLinkedList<Integer> empty;
	DoublyLinkedList<Integer> oneItem;
	DoublyLinkedList<Integer> multipleItems;
	DoublyLinkedList<Integer> duplicateList;

	@Before
	public void setUpLists() {
		empty = new DoublyLinkedList<Integer>();
		oneItem = new DoublyLinkedList<Integer>();
		oneItem.addFirst(0);
		multipleItems = new DoublyLinkedList<Integer>();
		multipleItems.addLast(0);
		multipleItems.addLast(1);
		multipleItems.addLast(2);
		multipleItems.addLast(3);
		multipleItems.addLast(4);

		duplicateList = new DoublyLinkedList<Integer>();
		duplicateList.addLast(0);
		duplicateList.addLast(0);
		duplicateList.addLast(1);
		duplicateList.addLast(1);
		duplicateList.addLast(2);
		duplicateList.addLast(3);
		duplicateList.addLast(4);
		duplicateList.addLast(1);
		duplicateList.addLast(4);

	}

	@Test
	public void addFirst() {
		empty.addFirst(0);
		assertEquals(1, empty.size());
		assertEquals((Integer) 0, empty.getFirst());
		assertEquals((Integer) 0, empty.getLast());
		Integer result = new Integer(-1);
		oneItem.addFirst(result);
		assertEquals(result, oneItem.getFirst());
		assertEquals((Integer) 0, oneItem.getLast());
		assertEquals(2, oneItem.size());
		multipleItems.addFirst(-1);
		multipleItems.addFirst(-2);
		assertEquals(7, multipleItems.size());
		Integer n = new Integer(-2);
		assertEquals(n, multipleItems.getFirst());
		assertEquals(result, multipleItems.get(1));

	}

	@Test
	public void addLast() {
		empty.addLast(0);
		assertEquals(1, empty.size());
		assertEquals((Integer) 0, empty.getFirst());
		assertEquals((Integer) 0, empty.getLast());
		Integer result = new Integer(-1);
		oneItem.addLast(result);
		assertEquals(result, oneItem.getLast());
		assertEquals((Integer) 0, oneItem.getFirst());
		assertEquals(2, oneItem.size());

		multipleItems.addLast(-1);
		multipleItems.addLast(-2);
		assertEquals(7, multipleItems.size());
		Integer n = new Integer(-2);
		assertEquals(n, multipleItems.getLast());
		assertEquals(result, multipleItems.get(5));

	}

	@Test
	public void add() {
		empty.add(0, 0);
		assertEquals(1, empty.size());
		assertEquals((Integer) 0, empty.getFirst());
		assertEquals((Integer) 0, empty.getLast());

		oneItem.add(0, -1);
		assertEquals((Integer) (-1), oneItem.getFirst());
		assertEquals((Integer) 0, oneItem.getLast());
		assertEquals(2, oneItem.size());

		multipleItems.add(0, -10);
		multipleItems.add(2, 20);
		multipleItems.add(6, 30);
		multipleItems.add(8, 80);
		assertEquals((Integer) (-10), multipleItems.getFirst());

		Integer[] array = { -10, 0, 20, 1, 2, 3, 30, 4, 80 };

		for (int i = 0; i < 8; i++) {
			assertEquals(multipleItems.get(i), array[i]);
		}
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addExceptions1() {
		empty.add(5, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addExceptions2() {
		multipleItems.add(-1, 5);
	}

	@Test(expected = NoSuchElementException.class)
	public void getFirstExceptions() {
		multipleItems.clear();
		multipleItems.getFirst();
	}

	@Test(expected = NoSuchElementException.class)
	public void getLastExceptions() {
		multipleItems.clear();
		multipleItems.getLast();
	}

	@Test
	public void removeFirst() {
		assertEquals((Integer) 0, oneItem.removeFirst());
		assertEquals(0, oneItem.size());
		assertEquals((Integer) 0, multipleItems.removeFirst());
		assertEquals(4, multipleItems.size());
		assertEquals((Integer) 1, multipleItems.removeFirst());
		assertEquals(3, multipleItems.size());
		Integer[] expectedResult = { 2, 3, 4 };
		for (int i = 0; i < multipleItems.size(); i++) {
			assertEquals(multipleItems.get(i), expectedResult[i]);
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void removeFirstExceptions() {
		empty.removeFirst();
	}

	@Test
	public void removeLast() {
		assertEquals((Integer) 0, oneItem.removeLast());
		assertEquals(0, oneItem.size());
		assertEquals((Integer) 4, multipleItems.removeLast());
		assertEquals(4, multipleItems.size());
		assertEquals((Integer) 3, multipleItems.removeLast());
		assertEquals(3, multipleItems.size());
		Integer[] expectedResult = { 0, 1, 2 };
		for (int i = 0; i < multipleItems.size(); i++) {
			assertEquals(multipleItems.get(i), expectedResult[i]);
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void removeLastExceptions() {
		empty.removeLast();
	}

	@Test
	public void remove() {

		assertEquals((Integer) 0, oneItem.remove(0));
		assertEquals(0, oneItem.size());

		assertEquals((Integer) 0, multipleItems.remove(0));
		assertEquals((Integer) 3, multipleItems.remove(2));
		assertEquals((Integer) 2, multipleItems.remove(1));

		Integer[] array = { 1, 4 };

		for (int i = 0; i < multipleItems.size(); i++) {
			assertEquals(multipleItems.get(i), array[i]);
		}
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeException1() {
		empty.remove(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeException2() {
		multipleItems.remove(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeException3() {
		multipleItems.remove(5);
	}

	@Test
	public void indexOf() {

		Integer i = new Integer(0);
		assertEquals(-1, empty.indexOf(i));

		assertEquals(0, oneItem.indexOf(0));
		assertEquals(-1, oneItem.indexOf(5));

		assertEquals(2, multipleItems.indexOf(2));
		assertEquals(-1, multipleItems.indexOf(9));

		assertEquals(0, duplicateList.indexOf(0));
		assertEquals(2, duplicateList.indexOf(1));
		assertEquals(6, duplicateList.indexOf(4));
		assertEquals(5, duplicateList.indexOf(3));
		assertEquals(-1, duplicateList.indexOf(50));

	}

	@Test
	public void lastIndexOf() {

		Integer i = new Integer(0);
		assertEquals(-1, empty.lastIndexOf(i));

		assertEquals(0, oneItem.lastIndexOf(0));
		assertEquals(-1, oneItem.lastIndexOf(5));

		assertEquals(2, multipleItems.lastIndexOf(2));
		assertEquals(-1, multipleItems.lastIndexOf(9));

		assertEquals(1, duplicateList.lastIndexOf(0));
		assertEquals(7, duplicateList.lastIndexOf(1));
		assertEquals(8, duplicateList.lastIndexOf(4));
		assertEquals(5, duplicateList.lastIndexOf(3));
		assertEquals(-1, duplicateList.lastIndexOf(50));

	}

	@Test
	public void clearAndIsEmpty() {
		assertTrue(empty.isEmpty());

		assertFalse(oneItem.isEmpty());
		oneItem.clear();
		assertTrue(oneItem.isEmpty());
		assertEquals(0, oneItem.size());

		assertFalse(multipleItems.isEmpty());
		multipleItems.clear();
		assertTrue(multipleItems.isEmpty());
		assertEquals(0, multipleItems.size());

		multipleItems.add(0, 22);
		multipleItems.removeFirst();
		assertTrue(multipleItems.isEmpty());
		assertEquals(0, multipleItems.size());
	}

	@Test
	public void toArray() {
		Object emptyArray[] = empty.toArray();
		Object expectedEmptyArray[] = new Object[0];
		assertArrayEquals(expectedEmptyArray, emptyArray);

		Object elementArray[] = oneItem.toArray();
		assertEquals(0, elementArray[0]);

		Object multipleItemsArray[] = multipleItems.toArray();
		for (int i = 0; i < multipleItems.size(); i++)
			assertEquals(i, multipleItemsArray[i]);
	}
	
	@Test
	public void iteratorHasNext(){
		Iterator<Integer> emptyItr = empty.iterator();
		Iterator<Integer> mItr = multipleItems.iterator();
		Iterator<Integer> oneItr = oneItem.iterator();
		
		assertFalse(emptyItr.hasNext());
		int i = 0;
		assertTrue(mItr.hasNext());
		while(mItr.hasNext()){
			mItr.next();
			i++;
		}
		assertFalse(mItr.hasNext());
		assertEquals(5, i);
		
		int n = 0;
		assertTrue(oneItr.hasNext());
		while(oneItr.hasNext()){
			oneItr.next();
			n++;
		}
		assertFalse(oneItr.hasNext());
		assertEquals(1, n);
		
		
	}

	@Test
	public void iteratorNext(){
		Iterator<Integer> emptyItr = empty.iterator();
		Iterator<Integer> mItr = multipleItems.iterator();
		Iterator<Integer> oneItr = oneItem.iterator();
		
		Boolean exception = false;
		Boolean emptyException = false;
		Boolean oneException = false;
		Integer i = 0;
		while(mItr.hasNext()){
			assertEquals(i, mItr.next());
			i++;
		}
		try{
			mItr.next();
		}
		catch (NoSuchElementException e){
			exception = true;
		}
		assertTrue(exception);
		
		try{
			emptyItr.next();
		}
		catch (NoSuchElementException e){
			emptyException = true;
		}
		assertTrue(emptyException);
		
		Integer n = 0;
		while(oneItr.hasNext()){
			assertEquals(n,oneItr.next());
			n++;
		}
		try{
			oneItr.next();
		}
		catch (NoSuchElementException e){
			oneException = true;
		}
		assertTrue(oneException);
	}
	
	@Test
	public void iteratorRemove(){
		Iterator<Integer> emptyItr = empty.iterator();
		Iterator<Integer> mItr = multipleItems.iterator();
		Iterator<Integer> oneItr = oneItem.iterator();
		Boolean multiException = false;
		Boolean emptyException = false;
		Boolean oneException = false;
		
		try{
			emptyItr.remove();
		}
		catch (IllegalStateException e){
			emptyException = true;
		}
		assertTrue(emptyException);
		
		assertEquals((Integer) 0, oneItr.next());
		oneItr.remove();
		assertEquals(0, oneItem.size());
		oneException = false;
		try{
			oneItr.remove();
		}
		catch (IllegalStateException e){
			oneException = true;
		}
		assertTrue(oneException);
		
		assertEquals((Integer) 0, mItr.next());
		assertEquals((Integer) 1, mItr.next());
		mItr.remove();
		assertEquals(4, multipleItems.size());
		try{
			mItr.remove();
		}
		catch (IllegalStateException e){
			multiException = true;
		}
		assertTrue(multiException);
		
		mItr.next();
		multipleItems.removeFirst();
		multiException = false;
		try{
		mItr.remove();
		}
		catch (ConcurrentModificationException e){
			multiException = true;
		}
		assertTrue(multiException);
	}
	
	@Test
	public void removeIterator(){
		Iterator<Integer> mItr = multipleItems.iterator();
		int i = 0;
		while(mItr.hasNext()){
			mItr.next();
			assertEquals((Integer)i, multipleItems.getFirst());
			mItr.remove();
			i++;
		}
		assertEquals(0, multipleItems.size());
	}
}
