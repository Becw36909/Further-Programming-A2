package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SearchUsernameExists is an interface for finding a matching record in a
 * connected database with the params inputted into an sql statement, returning
 * an integer count of 1 if there is a match.
 * 
 * @author Rebecca Watson
 *
 */
public interface SearchUsernameExists extends DatabaseGetConnection {

	/**
	 * Finds a matching row in the database according to the inputted parameters if
	 * there is one.
	 * 
	 * @param tableName the database table name to be found in the statement
	 * @param username  the username to be matched in the statement
	 * @return an integer variable 'count' with a value of 0 or 1.
	 */
	default int searchUsernameExists(String tableName, String username) {
		int count = 0;

		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
			String query = "SELECT COUNT( * )  FROM " + tableName + " where username = '" + username + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					System.out.printf("Count for USERNAME MATCH: %d \n", resultSet.getInt(1));
					count = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}

}