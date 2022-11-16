package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface InsertRowIntoRecordTable extends DatabaseGetConnection {

	default void insertRowIntoRecordTable(String tableName, String username, String recordDate, String weightKG,
			String tempCELC, String bloodPressure, String notes) {
//		final String TABLE_NAME = "User";

//		try (Connection con = DatabaseConnection.getConnection();
		try (Connection con = DatabaseGetConnection.getConnection();

				Statement stmt = con.createStatement();) {
//			String query = "INSERT INTO " + TABLE_NAME +
			String query = "INSERT INTO " + tableName +

					" VALUES ('" + username + "', '" + recordDate + "', '" + weightKG + "', '" + tempCELC + "', '"
					+ bloodPressure + "', '" + notes + "')";

			int result = stmt.executeUpdate(query);

			if (result == 1) {
//				System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
				System.out.println("Insert into table " + tableName + " executed successfully");

				System.out.println(result + " row(s) affected");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
