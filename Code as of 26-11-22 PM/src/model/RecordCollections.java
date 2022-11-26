package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * RecordCollections is a model class responsible for communicating with
 * controllers requesting creating, access, deletion and updates to record
 * objects. This class is in charge of communicating with other backend classes
 * and interfaces involved in creating, accessing, deleting and updating record
 * object data.
 * 
 * @author Rebecca Watson
 *
 */
public class RecordCollections implements DateAndTimeFormatter, ValidateRange, InsertRowIntoRecordTable,
		SearchRecordExists, GetRecords, DeleteRowInRecordTable, UpdateRecordTable, ExportRecords {

	/**
	 * Setting up private variables for the class.
	 */
	private UserInputValidator userInputValidator;
	private UserCollections userCollections = UserCollections.getInstance();
	private ArrayList<Record> records;
	private ArrayList<Record> recordToEdit;
	private ArrayList<Record> selectedRecordsToExport;
	private static RecordCollections recordCollections;
	final String RECORD_TABLE_NAME = "Record";
	private int oneChar = 1;
	private String emptyString = "";
	private String time;
	private String date;
	private String weight;
	private String temp;
	private String bloodLow;
	private String bloodHigh;
	private String notes;

	/**
	 * Constructor of the class.
	 */
	private RecordCollections() {

		records = new ArrayList<Record>();
		selectedRecordsToExport = new ArrayList<Record>();
		recordToEdit = new ArrayList<Record>();
		userInputValidator = new UserInputValidator();

	}

	/**
	 * Synchronizes a singleton instance of the RecordCollections class.
	 * 
	 * @return the current instance of the singleton RecordCollections class.
	 */
	public static synchronized RecordCollections getInstance() {
		if (recordCollections == null) {
			recordCollections = new RecordCollections();
		}
		return recordCollections;
	}

	/**
	 * Inputs a new row for a record into the connected database record table.
	 * 
	 * @param dateStr           date string for reformatting
	 * @param recordTime        time string for database record column value
	 * @param weightKG          weight in kilograms string for database record
	 *                          column value
	 * @param tempCELC          temp in celcius string for database record column
	 *                          value
	 * @param bloodPressureLow  blood pressure low string for database record column
	 *                          value
	 * @param bloodPressureHigh blood pressure high string for database record
	 *                          column value
	 * @param notes             notes string for database record column value
	 */
	public void createNewRecord(String dateStr, String recordTime, String weightKG, String tempCELC,
			String bloodPressureLow, String bloodPressureHigh, String notes) {

		/**
		 * need to split the date string and enter it as YYYY-MM-DD format for the
		 * database
		 */
		String newDateFormat = changeDateFormat(dateStr);
		String bloodPressure = bloodPressureLow + "-" + bloodPressureHigh;
		String username = userCollections.getUserName();
		insertRowIntoRecordTable(RECORD_TABLE_NAME, username, newDateFormat, recordTime, weightKG, tempCELC,
				bloodPressure, notes);

	}

	/**
	 * Updates an existing row for a record in the connected database record table.
	 * 
	 * @param dateStr           date string for reformatting
	 * @param recordTime        time string for database record column value
	 * @param weightKG          weight in kilograms string for database record
	 *                          column value
	 * @param tempCELC          temp in celcius string for database record column
	 *                          value
	 * @param bloodPressureLow  blood pressure low string for database record column
	 *                          value
	 * @param bloodPressureHigh blood pressure high string for database record
	 *                          column value
	 * @param notes             notes string for database record column value
	 */
	public void editExistingRecord(String dateStr, String recordTime, String weightKG, String tempCELC,
			String bloodPressureLow, String bloodPressureHigh, String notes) {

		/**
		 * need to split the date string and enter it as YYYY-MM-DD format for the
		 * database
		 */
		String newDateFormat = changeDateFormat(dateStr);
		String bloodPressure = bloodPressureLow + "-" + bloodPressureHigh;
		String username = userCollections.getUserName();
		updateRecordTable(RECORD_TABLE_NAME, username, newDateFormat, recordTime, weightKG, tempCELC, bloodPressure,
				notes);
		recordToEdit.clear();

	}

	/**
	 * Checks that there is a record present in the recordToEdit arrayList for a
	 * user to edit.
	 * 
	 * @return true if a record is present in the recordToEdit arrayList; false
	 *         otherwise.
	 */
	public boolean recordToEditInArray() {
		boolean recordInArray = false;
		if (recordToEdit.size() == 1) {
			recordInArray = true;
		}
		return recordInArray;
	}

	/**
	 * Performs database search to see if a record already exists.
	 * 
	 * @param dateStr date string for reformatting
	 * @param time    time string for database match
	 * @return an integer with a value of 0 or 1.
	 */
	public int searchRecordExists(String dateStr, String time) {
		String newDateFormat = changeDateFormat(dateStr);
		return searchRecordExists(RECORD_TABLE_NAME, userCollections.getUserName(), newDateFormat, time);
	}

	/**
	 * Passes the current records arrayList to the caller.
	 * 
	 * @return records arrayList.
	 */
	public ArrayList<Record> getRecordArray() {
		return records;
	}

	/**
	 * Adds a record matching the input params if there is one found in the records
	 * arrayList to the recordToEdit arrayList. Clears the recordToEdit arrayList
	 * each time the method is called.
	 * 
	 * @param username   username string part of a match to find in the record array
	 * @param recordDate recordDate string part of a match to find in the record
	 *                   array
	 * @param recordTime recordTime string part of a match to find in the record
	 *                   array
	 */
	public void addToEditRecordArray(String username, String recordDate, String recordTime) {
		recordToEdit.clear();
		for (int i = 0; i < records.size(); i++) {
			if (username == records.get(i).getUsername() && recordDate == records.get(i).getRecordDate()
					&& recordTime == records.get(i).getRecordTime()) {
				recordToEdit.add(records.get(i));
				System.out.println("added to recordToEdit array and printing toString below");
				System.out.println(records.get(i).toString());
			}
		}

	}

	/**
	 * Finds a matching record with the params in the connected database and deletes
	 * the row. Updates the record arrayList by calling the getRecords method.
	 * 
	 * @param username the username to match in the database row
	 * @param date     the date to match in the database row
	 * @param time     the time to match in the database row
	 */
	public void deleteExistingRecord(String username, String date, String time) {
		deleteRowInRecordTable(RECORD_TABLE_NAME, username, date, time);
		getRecords();

	}

	public void viewAllExistingRecords() {

	}

	public void exportSelectedRecords() {

	}

	/**
	 * Validates the length of the notes string is an acceptable length.
	 * 
	 * @param userNotes the string to check the length of
	 * @return true if the length of the string is an acceptable length; false
	 *         otherwise.
	 */
	public boolean checkNotesLength(String userNotes) {
		return userInputValidator.checkNotesLength(userNotes);
	}

	/**
	 * Finds all matching record rows in the records database table according to the
	 * current user in the UserCollections class and adds the records into the
	 * records arrayList.
	 */
	@Override
	public void getRecords() {
		/**
		 * clearing arrayList to get all records and so no duplicates added.
		 */
		records.clear();
		String username = userCollections.getUserName();
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String query = "SELECT username, record_date, record_time, weight_KG, temp_CELC, blood_pressure, notes FROM "
					+ RECORD_TABLE_NAME + " where username = '" + username + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {

				while (resultSet.next()) {
					System.out.print(resultSet.getString("username") + "\t");
					System.out.print(resultSet.getString("record_date") + "\t");
					System.out.print(resultSet.getString("record_time") + "\t");
					System.out.print(resultSet.getString("weight_KG") + "\t");
					System.out.print(resultSet.getString("temp_CELC") + "\t");
					System.out.print(resultSet.getString("blood_pressure") + "\t");
					System.out.print(resultSet.getString("notes") + "\n");

					Record record = new Record(resultSet.getString("username"), resultSet.getString("record_date"),
							resultSet.getString("record_time"), resultSet.getString("weight_KG"),
							resultSet.getString("temp_CELC"), resultSet.getString("blood_pressure"),
							resultSet.getString("notes"));
					records.add(record);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print(printRecordArrayList());

	}

	/**
	 * Helper method for printing out current records in records ArrayList to check
	 * the records have been retrieved correctly.
	 * 
	 * @return a string of all the records in the records arrayList.
	 */
	public String printRecordArrayList() {
		StringBuilder sb = new StringBuilder();
		System.out.println("Iterating current Record ArrayList...");
		for (int i = 0; i < records.size(); i++) {
//			System.out.println("size of current record ArrayList..." + records.size());
//			System.out.println("Record at postion in the ArrayList..." + (i));

			sb.append(records.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * * Takes record objects and writes them to a new .csv file.
	 * 
	 * @param filePath the file directory path for the file creation
	 * @param fileName the file name for the file being created
	 */
	public void exportAllRecordsToCSV(String filePath, String fileName) {
		exportAllRecordsToCSV(filePath, fileName, records);

//		File csvFile = new File("records.csv");
//		FileWriter fileWriter;
//		System.out.println("inside export method");
//		try {
//			fileWriter = new FileWriter(csvFile);
//			StringBuilder recordLine = new StringBuilder();
//			recordLine.append(CSVheaders);
//			recordLine.append("\n");
//			for (Record record : records) {
//				recordLine.append(record.toCSVString());
//				recordLine.append("\n");
//			}
//			fileWriter.write(recordLine.toString());
//			fileWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Gets the time string of the record object in the recordToEdit arrayList.
	 * 
	 * @return a time string from the record object in the recordToEdit arrayList.
	 */
	public String recordToEditGetTime() {
		return recordToEdit.get(0).getRecordTime();
	}

	/**
	 * Gets the date string of the record object in the recordToEdit arrayList and
	 * reformats it for output.
	 * 
	 * @return a reformatted date string from the record object in the recordToEdit
	 *         arrayList.
	 */
	public String recordToEditGetDate() {
		String dateStr = recordToEdit.get(0).getRecordDate();
		String newDateFormat = changeBackDateFormat(dateStr);

		return newDateFormat;
	}

	/**
	 * Gets the weightKG string of the record object in the recordToEdit arrayList.
	 * 
	 * @return a weightKG string from the record object in the recordToEdit
	 *         arrayList.
	 */
	public String recordToEditGetWeight() {
		return recordToEdit.get(0).getWeightKG();

	}

	/**
	 * Gets the tempCELC string of the record object in the recordToEdit arrayList.
	 * 
	 * @return a tempCELC string from the record object in the recordToEdit
	 *         arrayList.
	 */
	public String recordToEditGetTemp() {
		return recordToEdit.get(0).getTempCELC();
	}

	/**
	 * Gets the bloodPressure string of the record object in the recordToEdit
	 * arrayList. If the bloodPressure string is not one character equal to '-' the
	 * string is split on that character and the low pressure value is retrieved.
	 * 
	 * @return a string for bloodPressureLow.
	 */
	public String recordToEditGetBloodLow() {
		if (recordToEdit.get(0).getBloodPressure().length() > oneChar) {

			System.out.println("inside the if of the recordToEditGetBloodLow()");
			String bloodPressureParts[] = recordToEdit.get(0).getBloodPressure().split("-");

			System.out.println("printing out blood low " + bloodPressureParts[0]);
			bloodLow = bloodPressureParts[0];

		} else {
			System.out.println("inside the else of the recordToEditGetBloodLow()");

			bloodLow = emptyString;

		}
		return bloodLow;

	}

	/**
	 * Gets the bloodPressure string of the record object in the recordToEdit
	 * arrayList. If the bloodPressure string is not one character equal to '-' the
	 * string is split on that character and the high pressure value is retrieved.
	 * 
	 * @return a string for bloodPressureHigh.
	 */
	public String recordToEditGetBloodHigh() {
		if (recordToEdit.get(0).getBloodPressure().length() > oneChar) {
			System.out.println("inside the if of the recordToEditGetBloodHigh()");
			String bloodPressureParts[] = recordToEdit.get(0).getBloodPressure().split("-");

			System.out.println("printing out blood low " + bloodPressureParts[1]);

			bloodHigh = bloodPressureParts[1];

		} else {
			System.out.println("inside the else of the recordToEditGetBloodHigh()");

			bloodHigh = emptyString;
		}
		return bloodHigh;

	}

	/**
	 * Gets the notes string of the record object in the recordToEdit arrayList.
	 * 
	 * @return a notes string from the record object in the recordToEdit arrayList.
	 */
	public String recordToEditGetNotes() {
		return recordToEdit.get(0).getNotes();
	}

}
