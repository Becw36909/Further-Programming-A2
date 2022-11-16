package model;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface DateFormatter {

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

	default String changeDateFormat(String dateStr) {
		String dateParts[] = dateStr.split("/");
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];
		String newDateFormat = year + "-" + month + "-" + day;
		return newDateFormat;

	}

}
