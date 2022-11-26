package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * UpdateUserFirstName is an interface for updating a row in a table of a
 * connected database with a sql statement.
 * 
 * @author Rebecca watson
 *
 */
public interface UpdateUserFirstName extends DatabaseGetConnection {

	/**
	 * Updates a row in a database with an sql statement.
	 * 
	 * @param tableName the database table name to be found in the statement
	 * @param firstName the first name to be updated in the statement
	 * @param username  the username to be matched in the statement
	 */
	default void updateUserFirstName(String tableName, String firstName, String username) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + tableName + " SET first_name = '" + firstName + "'" + " WHERE username = '"
					+ username + "'";

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
