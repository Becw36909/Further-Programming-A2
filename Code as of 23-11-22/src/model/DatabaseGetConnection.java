package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseGetConnection is an interface for connecting to a database, according
 * to the value of the only class variable.
 * 
 * @author Rebecca Watson
 *
 */
public interface DatabaseGetConnection {

	/**
	 * Defines the connection between the Eclipse jdbc and the selected database.
	 */
	public static final String DB_URL = "jdbc:sqlite:MyHealthDB.db";

	/**
	 * @return a connection with a database according to the DB_URL variable path. 
	 * @throws SQLException error messages
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}