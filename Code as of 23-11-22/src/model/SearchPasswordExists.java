package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface SearchPasswordExists extends DatabaseGetConnection {
	
	
	// MIGHT BE A REDUNDANT INTERFACE - CONSIDER DELETING

	default int searchPasswordExists(String tableName, String password) {
//		final String TABLE_NAME = "User";
		int count = 0;

//		try (Connection con = DatabaseConnection.getConnection();
		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
//			String query = "SELECT COUNT( * )  FROM " + TABLE_NAME + " where username = '" + username + "';";
			String query = "SELECT COUNT( * )  FROM " + tableName + " where password = '" + password + "';";


			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Count for PASSWORD match: %d \n",
							resultSet.getInt(1) );
//					count = Integer.parseInt(resultSet.toString());
					count = resultSet.getInt(1);
				}
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;	
	}
	
}
