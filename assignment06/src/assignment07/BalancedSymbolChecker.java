package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

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
	public static String checkFile(String filename) throws IOException, FileNotFoundException {
		// used to pair the symbols rather than doing it through multiple if statements
		HashMap<Character, Character> symbol = new HashMap<Character, Character>();
		symbol.put('(', ')');
		symbol.put('{', '}');
		symbol.put('[', ']');
		symbol.put(')', '(');
		symbol.put('}', '{');
		symbol.put(']', '[');
		FileReader reader = new FileReader(filename);
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		int i;
		char c;
		char popped;
		int col = 1;
		int row = 1;
		//reads all the characters from the file. apparently this is more efficient than a scanner
		while((i = reader.read())!=-1){
			c = (char)i;
			if(c=='(' ||c=='{' ||c=='['){
				stack.push(c);
			}
			//increments column count
			if(c=='\t' || c==' '){
				col++;
			}
			//resets column and increments row count
			if(c=='\n'){
				col=1;
				row++;
			}
			if(c==')' ||c=='}' ||c==']'){
				if(stack.isEmpty()){
					return unmatchedSymbol(row, col, c, ' ');
				}
				popped = stack.pop();
				if(symbol.get(c)!=popped){
					reader.close();
					// need to figure out way of finding line/column
					return unmatchedSymbol(row, col, c, symbol.get(popped));
				}
			}
		}
		if(!stack.isEmpty()){
			return unmatchedSymbolAtEOF(symbol.get(stack.pop()));
		}
		else{
		reader.close();
		return allSymbolsMatch();
		}
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}