package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * InsertRowIntoUserTable is an interface for inserting a row in a table of a
 * connected database with a sql statement.
 * 
 * @author Rebecca watson
 *
 */
public interface InsertRowIntoUserTable extends DatabaseGetConnection {

	/**
	 * @param tableName the database table name to be found in the statement
	 * @param username  the username value to be inserted in the statement
	 * @param firstName the first name value to be inserted in the statement
	 * @param lastName  the last name value to be inserted in the statement
	 * @param password  the password value to be inserted in the statement
	 */
	default void insertRowIntoUserTable(String tableName, String username, String firstName, String lastName,
			String password) {

		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + tableName +

					" VALUES ('" + username + "', '" + firstName + "', '" + lastName + "', '" + password + "')";

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