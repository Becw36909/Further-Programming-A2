package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * InsertRowIntoRecordTable is an interface for inserting a row in a table of a
 * connected database with a sql statement.
 * 
 * @author Rebecca watson
 *
 */
public interface InsertRowIntoRecordTable extends DatabaseGetConnection {

	/**
	 * Inserts a new row into a database table with an sql statememt.
	 * 
	 * @param tableName     the database table name to be found in the statement
	 * @param username      the username value to be inserted in the statement
	 * @param recordDate    the date value to be inserted in the statement
	 * @param time          the time value to be inserted in the statement
	 * @param weightKG      the weight value to be inserted in the statement
	 * @param tempCELC      the temp value to be inserted in the statement
	 * @param bloodPressure the blood pressure value to be inserted in the statement
	 * @param notes         the notes value value to be inserted in the statement
	 */
	default void insertRowIntoRecordTable(String tableName, String username, String recordDate, String time,
			String weightKG, String tempCELC, String bloodPressure, String notes) {

		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + tableName +

					" VALUES ('" + username + "', '" + recordDate + "', '" + time + "', '" + weightKG + "', '"
					+ tempCELC + "', '" + bloodPressure + "', '" + notes + "')";

			int result = stmt.executeUpdate(query);

			if (result == 1) {
				System.out.println("Insert into table " + tableName + " executed successfully");

				System.out.println(result + " row(s) affected");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
