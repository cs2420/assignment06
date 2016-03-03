package assignment07;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import org.junit.Before;

public class BalancedSymbolCheckerTest {
	
	BalancedSymbolChecker checker;
	
	@Before
	public void createCheckerClass(){
		checker = new BalancedSymbolChecker();
	}

	@Test
	public void Class1() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				checker.checkFile("A7_examples/Class1.java"));
	}
	@Test
	public void Class2() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				checker.checkFile("A7_examples/Class2.java"));
	}
	@Test
	public void Class3() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class3.java"));
	}
	@Test
	public void Class4() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: File ended before closing comment.",
				checker.checkFile("A7_examples/Class4.java"));
	}
	@Test
	public void Class5() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				checker.checkFile("A7_examples/Class5.java"));
	}
	@Test
	public void Class6() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class6.java"));
	}
	@Test
	public void Class7() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				checker.checkFile("A7_examples/Class7.java"));
	}
	@Test
	public void Class8() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				checker.checkFile("A7_examples/Class8.java"));
	}
	@Test
	public void Class9() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				checker.checkFile("A7_examples/Class9.java"));
	}
	@Test
	public void Class10() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				checker.checkFile("A7_examples/Class10.java"));
	}
	@Test
	public void Class11() throws FileNotFoundException, IOException {
		assertEquals(
				"ERROR: Unmatched symbol at the end of file. Expected }.",
				checker.checkFile("A7_examples/Class11.java"));
	}
	@Test
	public void Class12() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class12.java"));
	}
	@Test
	public void Class13() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class13.java"));
	}
	@Test
	public void Class14() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class14.java"));
	}
	@Test
	public void Class15() throws FileNotFoundException, IOException {
		assertEquals(
				"No errors found. All symbols match.",
				checker.checkFile("A7_examples/Class15.java"));
	}
}
