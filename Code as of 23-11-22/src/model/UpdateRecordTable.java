package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * UpdateRecordTable is an interface for updating a row in a table of a
 * connected database with a sql statement.
 * 
 * @author Rebecca watson
 *
 */
public interface UpdateRecordTable extends DatabaseGetConnection {

	/**
	 * Updates a row in a database with an sql statement.
	 * 
	 * @param tableName     the database table name to be found in the statement
	 * @param username      the username to be matched in the statement
	 * @param recordDate    the date to be matched and updated in the statement
	 * @param recordTime    the time to be matched and updated in the statement
	 * @param weightKG      the weight value to be updated in the statement
	 * @param tempCELC      the temp value to be updated in the statement
	 * @param bloodPressure the bloodPressure value to be updated in the statement
	 * @param notes         the notes value to be updated in the statement
	 */
	default void updateRecordTable(String tableName, String username, String recordDate, String recordTime,
			String weightKG, String tempCELC, String bloodPressure, String notes) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + tableName + " SET record_date = '" + recordDate + "', record_time = '" + recordTime
					+ "', weight_KG = '" + weightKG + "', temp_CELC = '" + tempCELC + "', blood_pressure = '"
					+ bloodPressure + "', notes = '" + notes + "' " + " WHERE username = '" + username
					+ "' and record_date = '" + recordDate + "' and record_time = '" + recordTime + "';";

			int result = stmt.executeUpdate(sql);

			if (result == 1) {
				System.out.println("Update table " + tableName + " executed successfully");
				System.out.println(result + " row(s) affected");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
