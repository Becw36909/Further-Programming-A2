package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface UpdateUserFirstName extends DatabaseGetConnection {

	default void updateUserFirstName(String tableName, String firstName, String username) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + tableName + " SET first_name = '" + firstName + "'"
					+ " WHERE username = '" + username + "'";

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
