package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DeleteRowInRecordTable is an interface for deleting a row in a table of a
 * connected database with a sql statement.
 * 
 * @author Rebecca watson
 *
 */
public interface DeleteRowInRecordTable extends DatabaseGetConnection {

	/**
	 * Deletes a row into a database table with an sql statememt.
	 * 
	 * @param tableName the database table name to be found in the statement
	 * @param username  the username to be matched in the statement
	 * @param date      the date to be matched in the statement
	 * @param time      the time to be matched in the statement
	 */
	default void deleteRowInRecordTable(String tableName, String username, String date, String time) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "DELETE FROM " + tableName + " WHERE username = '" + username + "' and record_date = '" + date
					+ "' and record_time ='" + time + "';";

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
