package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SearchRecordExists is an interface for finding a matching record in a
 * connected database with the params inputted into an sql statement, returning
 * an integer count of 1 if there is a match.
 * 
 * @author Rebecca Watson
 *
 */
public interface SearchRecordExists extends DatabaseGetConnection {

	/**
	 * Finds a matching row in the database according to the inputted parameters if
	 * there is one.
	 * 
	 * @param tableName the database table name to be found in the statement
	 * @param username  the username to be matched in the statement
	 * @param date      the date to be matched in the statement
	 * @param time      the time to be matched in the statement
	 * @return an integer variable 'count' with a value of 0 or 1.
	 */
	default int searchRecordExists(String tableName, String username, String date, String time) {
		int count = 0;

		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
			String query = "SELECT COUNT( * )  FROM " + tableName + " where username = '" + username
					+ "' and record_date = '" + date + "' and record_time ='" + time + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					System.out.printf("Count for RECORD MATCH: %d \n", resultSet.getInt(1));
					count = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}

}
