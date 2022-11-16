package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable implements DatabaseGetConnection {
	public static void main(String[] args) {
//		final String TABLE_NAME = "User";
		final String TABLE_NAME = "Record";


		try (Connection con = DatabaseGetConnection.getConnection();
				Statement stmt = con.createStatement();) {
//			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
//										+ "(username VARCHAR (20) NOT NULL,"
//										+ "first_name VARCHAR(20) NOT NULL,"
//										+ "last_name VARCHAR(20) NOT NULL,"
//										+ "password VARCHAR(20) NOT NULL,"
//										+ "PRIMARY KEY (username))");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
//					+ "(recordID VARCHAR (8) NOT NULL,"
					+ "(username VARCHAR (20) NOT NULL,"
					+ "record_date TEXT NOT NULL,"
					+ "weight_KG TEXT,"
					+ "temp_CELC TEXT,"
					+ "blood_pressure TEXT,"
					+ "notes TEXT,"
					+ "PRIMARY KEY (username, record_date)"
					+ "FOREIGN KEY (username)"
					+ "REFERENCES User (username))");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
