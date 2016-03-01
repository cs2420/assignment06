package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Connor Ottenbacher and Doug Garding
 */
public class BalancedSymbolChecker {
	
	private boolean inBlockComment = false;
	
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * 
	 * @throws IOException,
	 *             FileNotFoundException
	 */
	public String checkFile(String filename) throws IOException, FileNotFoundException {
		LinkedListStack<Character> symbolStack = new LinkedListStack<Character>();
		Scanner inputScanner = new Scanner(new File(filename));

		// Search through each line of the input file
		while (inputScanner.hasNextLine()) {
			String currentLine = new String(inputScanner.next());
			char[] lineOfChars = currentLine.toCharArray();

			// Search through each char in array of chars
			for (int i = 0; i < lineOfChars.length; i++) {
				char currentChar = lineOfChars[i];
				
				// If currently in a block comment, see if it's ended
				if(inBlockComment){
					// If the comment block is ending
					if(currentChar == '*' && lineOfChars[i + 1] == '/'){
						inBlockComment = false;
						i++;
						continue;
					}
					// If the comment block has not ended
					else{
						continue;
					}
				}
				
				// If we hit a char literal
				if(currentChar == '\''){
					currentChar = lineOfChars[i + 3];
					i = i +3;
				}
				
				// If we hit a String literal
				if(currentChar == '\"'){
					// Find the close to the String literal
					for (int j = i + 1; j < lineOfChars.length; i++){
						if (lineOfChars[j] == '\"'){
							currentChar = lineOfChars[j + 1];
							i = j + 1;
							break;
						}
					}
				}
				
				// If we hit an in-line comment
				if(currentChar == '/' && lineOfChars[i + 1] == '/'){
					break;
				}
				
				// If we hit a block comment
				if(currentChar == '/' && lineOfChars[i + 1] == '*'){
					// See if end to the block comment is in this line
					
					// If the end of the block comment is in this line
					if(currentLine.substring(i).contains("*/")){
						i = i + currentLine.substring(i).indexOf("*/", i);
					}
					else{
						inBlockComment = true;
						break;
					}
				}
				
				// If found opening symbol, push to stack
				if (lineOfChars[i] == '(' || lineOfChars[i] == '{' || lineOfChars[i] == '[') {
					symbolStack.push(lineOfChars[i]);
					continue;
				}

			}

		}

		// I DON'T KNOW HOW TO USE FILEREADER, SO I USED A SCANNER AND WE CAN
		// SWITCH IT LATER
		/*
		 * FileReader reader = new FileReader(filename); int i; char c; //reads
		 * all the characters from the file. apparently this is more efficient
		 * than a scanner while((i = reader.read())!=0){ c = (char)i; }
		 */

		return null;
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
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