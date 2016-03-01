package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import static assignment07.BalancedSymbolChecker.*;

public class SymbolCheckerTests {
	
	@Test
	public void testClass00() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				checkFile("mytest.java"));
	}
	
	@Test
	public void testClass01() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				checkFile("Class1.java"));
	}
	
	@Test
	public void testClass02() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				checkFile("Class2.java"));
	}
	
	@Test
	public void testClass03() throws FileNotFoundException, IOException {
		assertEquals("No errors found. All symbols match.",
				checkFile("Class3.java"));
	}
	
	@Test
	public void testClass04() throws FileNotFoundException, IOException {
		assertEquals("ERROR: File ended before closing comment.",
				checkFile("Class4.java"));
	}
	
	@Test
	public void testClass05() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				checkFile("Class5.java"));
	}
	
	@Test
	public void testClass06() throws FileNotFoundException, IOException {
		assertEquals("No errors found. All symbols match.",
				checkFile("Class6.java"));
	}
	
	@Test
	public void testClass07() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				checkFile("Class7.java"));
	}
	
	@Test
	public void testClass08() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				checkFile("Class8.java"));
	}
	
	@Test
	public void testClass09() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				checkFile("Class9.java"));
	}
	
	@Test
	public void testClass10() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				checkFile("Class10.java"));
	}
	
	@Test
	public void testClass11() throws FileNotFoundException, IOException {
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
				checkFile("Class11.java"));
	}
	
	@Test
	public void testClass12() throws FileNotFoundException, IOException {
		assertEquals("No errors found. All symbols match.",
				checkFile("Class12.java"));
	}
	
	@Test
	public void testClass13() throws FileNotFoundException, IOException {
		assertEquals("No errors found. All symbols match.",
				checkFile("Class13.java"));
	}
	
	@Test
	public void testClass14() throws FileNotFoundException, IOException {
		assertEquals("No errors found. All symbols match.",
				checkFile("Class14.java"));
	}

}
