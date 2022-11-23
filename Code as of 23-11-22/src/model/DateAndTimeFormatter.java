package model;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateAndTimeFormatter is an interface for checking formats of date and time
 * inputs and changing date input formats.
 * 
 * @author Rebecca Watson
 *
 */
public interface DateAndTimeFormatter {

	/**
	 * @param dateStr the string to be checked
	 * @return true if the checked string matches the required format; false
	 *         otherwise.
	 */
	default boolean dateFormatCheck(String dateStr) {
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
	 * @param time the string to be checked
	 * @return true if the checked string matches the required format; false
	 *         otherwise.
	 */
	default boolean timeFormatCheck(String time) {
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
	 * @param dateStr the string to be split
	 * @return the param string in a new format.
	 */
	default String changeDateFormat(String dateStr) {
		String dateParts[] = dateStr.split("/");
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];
		String newDateFormat = year + "-" + month + "-" + day;
		return newDateFormat;

	}

	/**
	 * @param dateStr the string to be split
	 * @return the param string in a new format.
	 */
	default String changeBackDateFormat(String dateStr) {
		String dateParts[] = dateStr.split("-");
		String day = dateParts[2];
		String month = dateParts[1];
		String year = dateParts[0];
		String newDateFormat = day + "/" + month + "/" + year;
		return newDateFormat;

	}

}
