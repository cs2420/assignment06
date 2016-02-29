package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class BalancedSymbolCheckerTest {

	@Test
	public void CheckFile() throws FileNotFoundException, IOException {
		
		String result = BalancedSymbolChecker.checkFile("A7_examples/Class1.java");
		System.out.println(result);
	}

}
