package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface UpdateUserLastName extends DatabaseGetConnection {

	default void updateUserLastName(String tableName, String lastName, String username) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + tableName + " SET last_name = '" + lastName + "'"
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
