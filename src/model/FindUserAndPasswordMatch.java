package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface FindUserAndPasswordMatch extends DatabaseGetConnection {

	default int findUserAndPasswordMatch(String tableName, String username, String password) {
//		final String TABLE_NAME = "User";
		int count = 0;

//		try (Connection con = DatabaseConnection.getConnection();
		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
//			String query = "SELECT COUNT( * )  FROM " + TABLE_NAME + " where username = '" + username + "';";
			String query = "SELECT COUNT( * )  FROM " + tableName + " where username = '" + username
					+ "' and password = '" + password + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					System.out.printf("Count for USERNAME AND PASSWORD MATCH: %d \n", resultSet.getInt(1));
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
