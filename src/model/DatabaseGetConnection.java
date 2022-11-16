package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseGetConnection {
	public static final String DB_URL = "jdbc:sqlite:MyHealthDB.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}