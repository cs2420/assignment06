package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Connor Ottenbacher and Doug Garding
 */
public class BalancedSymbolChecker {

	// *********************************************************
	// I CHANGED ALL METHODS TO STATIC FOR TESTING, NEEDS TO BE CHANGED BACK!
	// *********************************************************

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * 
	 * @throws IOException,
	 *             FileNotFoundException
	 */
	public static String checkFile(String filename) throws IOException, FileNotFoundException {
		LinkedListStack<Character> symbolStack = new LinkedListStack<Character>();
		Scanner inputScanner = new Scanner(new File(filename));
		int row = 0;

		// Search through each line of the input file
		while (inputScanner.hasNextLine()) {
			String currentLine = new String(inputScanner.nextLine());
			char[] charArray = currentLine.toCharArray();
			row++;

			// Search through each char in array of chars
			for (int column = 0; column < charArray.length; column++) {
				char currentChar = charArray[column];
				char peekedItem = '0';
				try {
					peekedItem = symbolStack.peek();
				} catch (NoSuchElementException e) {
				}

				// If we hit an in-line comment
				if (currentChar == '/' && charArray[column + 1] == '/' && peekedItem != '\"' && peekedItem != '*') {
					break;
				}

				// If we hit a char literal
				if (currentChar == '\'' && peekedItem != '\"' && peekedItem != '*') {
					currentChar = charArray[column + 3];
					column = column + 3;
				}

				// If we hit a String literal
				if (currentChar == '\"' && peekedItem != '\"' && peekedItem != '*') {
					symbolStack.push('\"');
					continue;
				}

				// If we hit the end of a String literal
				if (currentChar == '\"' && peekedItem == '\"') {
					symbolStack.pop();
					continue;
				}

				// If we hit a block comment
				if (currentChar == '/' && charArray[column + 1] == '*' && peekedItem != '\"' && peekedItem != '*') {
					symbolStack.push('*');
					column++;
					continue;
				}

				// If we hit the end of a block comment
				if (currentChar == '*' && charArray[column + 1] == '/' && peekedItem != '\"' && peekedItem == '*') {
					symbolStack.pop();
					column++;
					continue;
				}

				// If found opening symbol, push to stack
				if (charArray[column] == '(' || charArray[column] == '{' || charArray[column] == '[') {
					// If we're in a block comment or String literal, ignore
					if (peekedItem == '\"' || peekedItem == '*') {
						continue;
					}

					symbolStack.push(charArray[column]);
					continue;
				}

				// If found a closing symbol, ensure it's correct
				if (charArray[column] == ')' || charArray[column] == '}' || charArray[column] == ']') {
					// If we're in a block comment or String literal, ignore
					if (peekedItem == '\"' || peekedItem == '*') {
						continue;
					}

					// If the stacked symbol matches
					if (checkMatch(charArray[column], peekedItem)) {
						symbolStack.pop();
						continue;
					}

					// If the stacked symbol doesn't match
					else if (!checkMatch(charArray[column], peekedItem)) {
						return unmatchedSymbol(row, column + 1, charArray[column], reverseChar(peekedItem));
					}
				}

			}

			// If there are still symbols on the stack when finished
			if (!symbolStack.isEmpty() && !inputScanner.hasNextLine()) {
				// If the symbol is from an open comment
				if (symbolStack.peek() == '*') {
					return unfinishedComment();
				}
				// If the symbol is an open bracket of some kind
				return unmatchedSymbolAtEOF(reverseChar(symbolStack.peek()));
			}
		}

		// All symbols matched up and none are left on the stack
		return allSymbolsMatch();

		// I DON'T KNOW HOW TO USE FILEREADER, SO I USED A SCANNER AND WE CAN
		// SWITCH IT LATER
		/*
		 * FileReader reader = new FileReader(filename); int i; char c; //reads
		 * all the characters from the file. apparently this is more efficient
		 * than a scanner while((i = reader.read())!=0){ c = (char)i; }
		 */
	}

	/**
	 * Private helper method that returns a closing character if passed an
	 * opening character, and returns an opening character if passed an open
	 * one.
	 * 
	 * If no opening or closing brackets are passed, an empty space char is
	 * returned.
	 * 
	 * @param originalSymbol
	 */
	private static char reverseChar(char originalSymbol) {
		if (originalSymbol == '(') {
			return ')';
		} else if (originalSymbol == '[') {
			return ']';
		} else if (originalSymbol == '{') {
			return '}';
		} else if (originalSymbol == ')') {
			return '(';
		} else if (originalSymbol == '}') {
			return '{';
		} else if (originalSymbol == '[') {
			return ']';
		}
		//
		else
			return ' ';
	}

	private static boolean checkMatch(char closingSymbol, Character peekedChar) {
		if (closingSymbol == ')') {
			return closingSymbol == reverseChar(peekedChar);
		}
		if (closingSymbol == '}') {
			return closingSymbol == reverseChar(peekedChar);
		}
		if (closingSymbol == ']') {
			return closingSymbol == reverseChar(peekedChar);
		}
		return false;
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
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