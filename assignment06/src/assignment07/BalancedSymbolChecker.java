package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Connor Ottenbacher and Doug Garding
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * @throws IOException, FileNotFoundException 
	 */
	public String checkFile(String filename) throws IOException, FileNotFoundException {
		HashMap<Character, Character> symbol = new HashMap<Character, Character>();
		symbol.put(')', '(');
		symbol.put('}', '{');
		symbol.put(']', '[');
		FileReader reader = new FileReader(filename);
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		int i;
		char c;
		char popped;
		//reads all the characters from the file. apparently this is more efficient than a scanner
		while((i = reader.read())!=0){
			c = (char)i;
			if(c=='(' ||c=='{' ||c=='['){
				stack.push(c);
			}
			if(c==')' ||c=='}' ||c==']'){
				popped = stack.pop();
				if(symbol.get(c)!=popped){
					unmatchedSymbol(0, 0, c, symbol.get(c));
				}
			}
		}
		return null;
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}