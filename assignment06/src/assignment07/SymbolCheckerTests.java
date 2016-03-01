package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
import static assignment07.BalancedSymbolChecker.*;

public class SymbolCheckerTests {

	@Test
	public void testClass1() {
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				checkFile("Class1.java"));
	}

}
