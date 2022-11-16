package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RecordCollections implements DateFormatter, ExportRecords, validateRange, InsertRowIntoRecordTable,
		SearchRecordExists, GetRecords {

	private UserInputValidator userInputValidator;
	private UserCollections userCollections = UserCollections.getInstance();
	private ArrayList<Record> records;
	private ArrayList<Record> selectedRecordsToExport;
	private DateTimeFormatter dateFormatter;
	private static RecordCollections recordCollections;
	final String RECORD_TABLE_NAME = "Record";

	protected RecordCollections() {

		records = new ArrayList<Record>();
		selectedRecordsToExport = new ArrayList<Record>();
		userInputValidator = new UserInputValidator();
		getRecords();

	}

	public static synchronized RecordCollections getInstance() {
		if (recordCollections == null) {
			recordCollections = new RecordCollections();
		}
		return recordCollections;
	}

	public void createNewRecord(String dateStr, String weightKG, String tempCELC, String bloodPressureLow,
			String bloodPressureHigh, String notes) {
		// need to split the date string and enter into the YYYY-MM-DD format for
		// database
		String newDateFormat = changeDateFormat(dateStr);
		String bloodPressure = bloodPressureLow + "-" + bloodPressureHigh;
		String username = userCollections.getUserName();
		insertRowIntoRecordTable(RECORD_TABLE_NAME, username, newDateFormat, weightKG, tempCELC, bloodPressure, notes);

	}

	public int searchRecordExists(String dateStr) {
		String newDateFormat = changeDateFormat(dateStr);
		return searchRecordExists(RECORD_TABLE_NAME, userCollections.getUserName(), newDateFormat);
	}
	
	public ArrayList<Record> getRecordArray() {
		return records;
	}

	public void editExistingRecord() {

	}

	public void deleteExistingRecord() {

	}

	public void viewAllExistingRecords() {

	}

	public void exportSelectedRecords() {

	}

	public boolean checkNotesLength(String userNotes) {
		return userInputValidator.checkNotesLength(userNotes);
	}

	@Override
	public void getRecords() {
		//clearing arrayList to get all records and no duplicates added
		records.clear();
		String username = userCollections.getUserName();
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String query = "SELECT username, record_date, weight_KG, temp_CELC, blood_pressure, notes FROM "
					+ RECORD_TABLE_NAME + " where username = '" + username + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {

				while (resultSet.next()) {
					System.out.print(resultSet.getString("username") + "\t");
					System.out.print(resultSet.getString("record_date") + "\t");
					System.out.print(resultSet.getString("weight_KG") + "\t");
					System.out.print(resultSet.getString("temp_CELC") + "\t");
					System.out.print(resultSet.getString("blood_pressure") + "\t");
					System.out.print(resultSet.getString("notes") + "\n");

					Record record = new Record(resultSet.getString("username"), resultSet.getString("record_date"),
							resultSet.getString("weight_KG"), resultSet.getString("temp_CELC"),
							resultSet.getString("blood_pressure"), resultSet.getString("notes"));
					records.add(record);
					

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print(printRecordArrayList());

	}

	// helper method for printing out current User in currentUser ArrayList to check
	// the user has been
	// created correctly
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

}
