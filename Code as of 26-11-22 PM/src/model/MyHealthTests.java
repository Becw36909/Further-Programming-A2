package model;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * MyHealthTests is the JUnit testing class for this MyHealth program.
 * 
 * @author Rebecca Watson
 *
 */
public class MyHealthTests {

	/**
	 * Setting up private variables for the class.
	 */
	private static String correctDatabaseURL = "jdbc:sqlite:MyHealthDB.db";
	private static String incorrectDatabaseURL = "MyHealth.file";

	private ArrayList<Record> records;
	private ArrayList<User> users;
	private ArrayList<Profile> profiles;
	private String validRecordDate = "02/02/2022";
	private String invalidRecordDate = "2/2/22";
	private String validRecordTime = "03:21";
	private String invalidRecordTime = "3.21";

	private double weightMin = 0;
	private double weightMax = 650;
	private double tempMin = 35;
	private double tempMax = 42.3;
	private double lowPressureMin = 0;
	private double lowPressureMax = 200;
	private double highPressureMin = 0;
	private double highPressureMax = 250;

	private double validWeightKG = 50;
	private double invalidWeightKG = 700;
	private double validTempCELC = 35.5;
	private double invalidTempCELC = 25;
	private int validBloodPressureLow = 85;
	private int invalidBloodPressureLow = 250;
	private int validBloodPressureHigh = 90;
	private int invalidBloodPressureHigh = 300;

	private int notesLengthAllowed = 50;
	private int userNotesLength;
	private String validNotesLength = "Today has been a great day.";
	private String invalidNotesLength = "Today has been a great day. I went to the shops and did some shopping and then I went home and did all the "
			+ "gardening and it was a lovely sunny day with great warm weather. Can't want to do it all again tomorrow and visit with some friends which will "
			+ "be fabulous. I will also bake a cake tomorrow for morning tea with a coffee and also for after dinner for the family when they "
			+ "come over to see me.";

	private String USERNAME_AND_PASSWORD_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
	private Pattern patternStrict = Pattern.compile(USERNAME_AND_PASSWORD_PATTERN);

	private String validUsername = "testUsername123";
	private String invalidUsername = "use";
	private String validPassword = "password123";
	private String invalidPassword = "123";

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

	/**
	 * Validates the input string matches a specified date pattern.
	 * 
	 * @param dateStr the input string to be parsed through the formatter
	 * @return true if the string matches the pattern; false otherwise.
	 */
	public boolean dateFormatCheck(String dateStr) {
		boolean checkedDate = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			formatter.parse(dateStr);
			checkedDate = true;
		} catch (DateTimeParseException e) {
		}
		return checkedDate;
	}

	/**
	 * Validates the input string matches a specified time pattern.
	 * 
	 * @param time the string to be checked
	 * @return true if the checked string matches the required format; false
	 *         otherwise.
	 */
	public boolean timeFormatCheck(String time) {
		boolean checkedTime = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		try {
			formatter.parse(time);
			checkedTime = true;
		} catch (DateTimeParseException e) {
		}
		return checkedTime;
	}

	/**
	 * Checks the double weight input is within an acceptable range according to
	 * private class variables.
	 * 
	 * @param weight the double weight input value to be checked
	 * @return true if the input double weight is within range of weightMin and
	 *         weightMax; false otherwise.
	 */
	public boolean weightRange(double weight) {
		boolean validatedWeight = false;
		if (weight >= weightMin && weight <= weightMax) {
			validatedWeight = true;
		}
		return validatedWeight;

	}

	/**
	 * Checks the double temp input is within an acceptable range according to
	 * private class variables.
	 * 
	 * @param temp the double temp input value to be checked
	 * @return true if the input double temp is within range of tempMin and tempMax;
	 *         false otherwise.
	 */
	public boolean tempRange(double temp) {
		boolean validatedTemp = false;
		if (temp >= tempMin && temp <= tempMax) {
			validatedTemp = true;
		}
		return validatedTemp;

	}

	/**
	 * Checks the double lowPressure input is within an acceptable range according
	 * to private class variables.
	 * 
	 * @param lowPressure the double lowPressure input value to be checked
	 * @return true if the input double lowPressure is within range of
	 *         lowPressureMin and lowPressureMax; false otherwise.
	 */
	public boolean bloodPressureLow(double lowPressure) {
		boolean validatedLowPressure = false;
		if (lowPressure >= lowPressureMin && lowPressure <= lowPressureMax) {
			validatedLowPressure = true;
		}
		return validatedLowPressure;

	}

	/**
	 * Checks the double highPressure input is within an acceptable range according
	 * to private class variables.
	 * 
	 * @param highPressure the double highPressure input value to be checked
	 * @return true if the input double highPressure is within range of
	 *         highPressureMin and highPressureMax; false otherwise.
	 */
	public boolean bloodPressureHigh(double highPressure) {
		boolean validatedHighPressure = false;
		if (highPressure >= highPressureMin && highPressure <= highPressureMax) {
			validatedHighPressure = true;
		}
		return validatedHighPressure;

	}

	/**
	 * Asserts true if test passes with a valid date string.
	 */
	@Test
	public void testValidDate() {
		assertTrue(dateFormatCheck(validRecordDate));
	}

	/**
	 * Asserts false if the test does not pass with an invalid date string.
	 */
	@Test
	public void testInvalidDate() {
		assertFalse(dateFormatCheck(invalidRecordDate));
	}

	/**
	 * Asserts true if test passes with a valid time string.
	 */
	@Test
	public void testValidTime() {
		assertTrue(timeFormatCheck(validRecordTime));
	}

	/**
	 * Asserts false if the test does not pass with an invalid time string.
	 */
	@Test
	public void testInvalidTime() {
		assertFalse(timeFormatCheck(invalidRecordTime));
	}

	/**
	 * Asserts true if test passes with a valid weight double input.
	 */
	@Test
	public void testValidWeightInput() {
		assertTrue(weightRange(validWeightKG));
	}

	/**
	 * Asserts false if the test does not pass with an invalid weight double input.
	 */
	@Test
	public void testInvalidWeightInput() {
		assertFalse(weightRange(invalidWeightKG));
	}

	/**
	 * Asserts true if test passes with a valid low blood pressure double input.
	 */
	@Test
	public void testValidLowBloodPressureInput() {
		assertTrue(bloodPressureLow(validBloodPressureLow));
	}

	/**
	 * Asserts false if test does not pass with an invalid low blood pressure double
	 * input.
	 */
	@Test
	public void testInvalidLowBloodPressureInput() {
		assertFalse(bloodPressureLow(invalidBloodPressureLow));
	}

	/**
	 * Asserts true if test passes with a valid high blood pressure double input.
	 */
	@Test
	public void testValidHighBloodPressureInput() {
		assertTrue(bloodPressureHigh(validBloodPressureHigh));
	}

	/**
	 * Asserts false if test does not pass with an invalid high blood pressure
	 * double input.
	 */
	@Test
	public void testInvalidHighBloodPressureInput() {
		assertFalse(bloodPressureHigh(invalidBloodPressureHigh));
	}

	/**
	 * Asserts true if test passes with a valid temperature double input.
	 */
	@Test
	public void testValidTempRangeInput() {
		assertTrue(tempRange(validTempCELC));
	}

	/**
	 * Asserts false if test does not pass with an invalid temperature double input.
	 */
	@Test
	public void testInvalidTempRangeInput() {
		assertFalse(tempRange(invalidTempCELC));
	}

	/**
	 * Asserts true if test passes with a valid notes string length input.
	 */
	@Test
	public void testValidNotesInput() {
		assertTrue(checkNotesLength(validNotesLength));
	}

	/**
	 * Asserts false if test does not pass with an invalid string length input.
	 */
	@Test
	public void testInvalidNotesInput() {
		assertFalse(checkNotesLength(invalidNotesLength));
	}

	/**
	 * Asserts true if test passes the pattern check with a valid username string
	 * input.
	 */
	@Test
	public void testValidUsername() {
		assertTrue(strictInputIsValid(validUsername));
	}

	/**
	 * Asserts false if test does not pass the pattern check with an invalid
	 * username string input.
	 */
	@Test
	public void testInvalidUsername() {
		assertFalse(strictInputIsValid(invalidUsername));
	}

	/**
	 * Asserts true if test passes the pattern check with a valid password string
	 * input.
	 */
	@Test
	public void testValidPassword() {
		assertTrue(strictInputIsValid(validPassword));
	}

	/**
	 * Asserts false if test does not pass the pattern check with an invalid
	 * password string input.
	 */
	@Test
	public void testInvalidPassword() {
		assertFalse(strictInputIsValid(invalidPassword));
	}

	/**
	 * Tests if a database connection has been found.
	 * 
	 * @throws SQLException if no matching database URL is found.
	 */
	@Test
	public void testGetConnectionCorrectDatabaseURL() throws SQLException {
		DriverManager.getConnection(correctDatabaseURL);
	}

	/**
	 * Tests if a database connection has been found.
	 * 
	 * @throws SQLException if no matching database URL is found.
	 */
	@Test(expected = SQLException.class)
	public void testGetConnectionIncorrectDatabaseURL() throws SQLException {
		DriverManager.getConnection(incorrectDatabaseURL);
	}
}
