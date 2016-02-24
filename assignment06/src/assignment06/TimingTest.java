package assignment06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * A timing tool to test the speed of DoublyLinkedList.
 * 
 * @author Doug Garding and Connor Ottenbacher
 */
public class TimingTest {

	private static final int ITER_COUNT = 10_000;

	public static void main(String[] args) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		// You spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
			;

		// Used as the exponent to calculate the size of the set (Size N).
		for (int exp = 10; exp <= 20; exp++) {

			int size = (int) Math.pow(2, exp); // or ..
			size = 1 << exp;
			// The 2 statements are equivalent, see bit-shifting for more info.

			// Do the experiment multiple times, and average out the results
			long totalTime = 0;

			// SET UP - DoublyLinkedList
			DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
			for (int i = 0; i < size; i++) {
				doublyLinkedList.addLast(i);
			}

			// SET UP - LinkedList
			LinkedList<Integer> linkedList = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				linkedList.addLast(i);
			}

			// SET UP - ArrayList
			ArrayList<Integer> arrayList = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				arrayList.add(i);
			}

			for (int iter = 0; iter < ITER_COUNT; iter++) {

				// START TIMER
				long start = System.nanoTime();

				// RUN THE TEST ON THE METHOD;
				// ADD FIRST
				// doublyLinkedList.addFirst(5);
				// linkedList.addFirst(5);
				// arrayList.add(0, 5);
				// GET METHOD
				// doublyLinkedList.get(size/4);
				// linkedList.get(size/4);
				// arrayList.get(size / 4);
				// REMOVE METHOD
				// doublyLinkedList.remove(size/4);
				// linkedList.remove(size/4);
				 arrayList.remove(size / 4);

				// STOP TIMER
				long stop = System.nanoTime();
				totalTime += stop - start;

				// REMOVE THE ADDED ITEM
				// doublyLinkedList.removeFirst();
				// linkedList.removeFirst();
				// arrayList.remove(arrayList.size() - 1);
				
				// ADD THE REMOVED ITEM
				// doublyLinkedList.addFirst(5);
				// linkedList.addFirst(5);
				arrayList.add(arrayList.size() - 1, 5);

			}
			double averageTime = totalTime / (double) ITER_COUNT;
			System.out.println(size + "\t" + averageTime); // print to console
		}
	}
}