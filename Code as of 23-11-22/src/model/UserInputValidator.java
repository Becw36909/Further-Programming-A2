package model;

import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/**
 * UserInputValidator is a class for checking input strings match a specified
 * pattern, either simple or strict.
 * 
 * @author Rebecca Watson
 *
 */
public class UserInputValidator {

	/**
	 * Setting up private variables for the class.
	 */
	/**
	 * simple name regex
	 */
	private static final String NAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

	/**
	 * strict username and password regex
	 */
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

	private static final Pattern patternSimple = Pattern.compile(NAME_PATTERN);

	private static final Pattern patternStrict = Pattern.compile(USERNAME_PATTERN);

	private int notesLengthAllowed = 50;

	private int userNotesLength;

	/**
	 * Checks that the input string matches the strict pattern defined in private
	 * class variable.
	 * 
	 * @param userInput the string input to match with the pattern
	 * @return true if the userInput string matches the specified pattern; false
	 *         otherwise.
	 */
	public boolean strictInputIsValid(final String userInput) {
		Matcher matcher = patternStrict.matcher(userInput);
		return matcher.matches();
	}

	/**
	 * Checks that the input string matches the simple pattern defined in private
	 * class variable.
	 * 
	 * @param userInput the string input to match with the pattern
	 * @return true if the userInput string matches the specified pattern; false
	 *         otherwise.
	 */
	public boolean simpleInputIsValid(final String userInput) {
		Matcher matcher = patternSimple.matcher(userInput);
		return matcher.matches();
	}

	/**
	 * Validates that the input userNotes string is not longer than the class
	 * variable notesLengthAllowed value.
	 * 
	 * @param userNotes the string length to be checked
	 * @return true if the input string is less than or equal to the class variable
	 *         notesLengthAllowed value; false otherwise.
	 */
	public boolean checkNotesLength(String userNotes) {
		boolean notesLengthChecked = false;
		StringTokenizer tokens = new StringTokenizer(userNotes);
		userNotesLength = tokens.countTokens();
		if (userNotesLength <= notesLengthAllowed) {
			notesLengthChecked = true;
		}
		return notesLengthChecked;
	}
}
