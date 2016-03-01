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

				// If we hit an in-line comment
				if (currentChar == '/' && charArray[column + 1] == '/') {
					break;
				}

				// If we hit a char literal
				if (currentChar == '\'') {
					currentChar = charArray[column + 3];
					column = column + 3;
				}

				// If we hit a String literal
				if (currentChar == '\"') {
					symbolStack.push('\"');
					break;
				}

				// If we hit the end of a String literal
				if (currentChar == '\"' && symbolStack.peek() == '\"') {
					symbolStack.pop();
					break;
				}

				// If we hit a block comment
				if (currentChar == '/' && charArray[column + 1] == '*') {
					symbolStack.push('*');
					column++;
					break;
				}

				// If we hit the end of a block comment
				if (currentChar == '*' && charArray[column + 1] == '/' && symbolStack.peek() == '*') {
					symbolStack.pop();
					column++;
					break;
				}

				// If found opening symbol, push to stack
				if (charArray[column] == '(' || charArray[column] == '{' || charArray[column] == '[') {
					// If we're in a block comment or String literal, ignore
					if (!symbolStack.isEmpty()) {
						if (symbolStack.peek() == '\"' || symbolStack.peek() == '*') {
							continue;
						}
					}

					symbolStack.push(charArray[column]);
					continue;
				}

				// If found a closing symbol, ensure it's correct
				if (charArray[column] == ')' || charArray[column] == '}' || charArray[column] == ']') {
					// If we're in a block comment or String literal, ignore
					if (symbolStack.peek() == '\"' || symbolStack.peek() == '*') {
						continue;
					}

					// If the stacked symbol matches
					if (checkMatch(charArray[column], symbolStack.peek())) {
						symbolStack.pop();
						continue;
					}

					// If the stacked symbol doesn't match
					else if (!checkMatch(charArray[column], symbolStack.peek())) {
						return unmatchedSymbol(row, column + 1, charArray[column], reverseChar(symbolStack.peek()));
					}
				}

			}

		}

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
	 * opening character, and returns an opening character if passed an open one.
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
		} else {
			return ']';
		}
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