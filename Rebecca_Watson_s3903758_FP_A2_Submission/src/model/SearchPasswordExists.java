package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface SearchPasswordExists extends DatabaseGetConnection {
	
	
	// MIGHT BE A REDUNDANT INTERFACE - CONSIDER DELETING IF UPDATE PASSWORD NOT IMPLEMENTED

	default int searchPasswordExists(String tableName, String password) {
		int count = 0;

		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
			String query = "SELECT COUNT( * )  FROM " + tableName + " where password = '" + password + "';";


			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Count for PASSWORD match: %d \n",
							resultSet.getInt(1) );
					count = resultSet.getInt(1);
				}
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;	
	}
	
}
