package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserCollections implements InsertRowIntoUserTable, SearchUsernameExists, SearchPasswordExists,
		FindUserAndPasswordMatch, GetUser, UpdateUserFirstName, UpdateUserLastName {

//	private static UserCollections usercollections = null;
//	private RecordCollections recordCollections = RecordCollections.getInstance();
	private UserInputValidator userInputValidator;
	private int foundUser = 1;
	private static UserCollections usercollections;
	final String USER_TABLE_NAME = "User";
	private ArrayList<User> currentUser;

	protected UserCollections() {
		currentUser = new ArrayList<User>();
		userInputValidator = new UserInputValidator(); 
		//IS THE BELOW NECESSARY IN THIS CLASS? PROBABLY JUST NOT USED YET
//		recordCollections = new RecordCollections();

	}

	public static synchronized UserCollections getInstance() {
		if (usercollections == null) {
			usercollections = new UserCollections();
		}
		return usercollections;
	}

	public String getUserFirstName() {
//		System.out.println("size of current User ArrayList..." + currentUser.size());
//		System.out.println("inside getUserFirstName method ");
		StringBuilder sb = new StringBuilder();
//		System.out.println("trying to get name of current User in ArrayList...");
		sb.append(currentUser.get(0).getFirstName());

		return sb.toString();

	}

	public String getUserLastName() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentUser.get(0).getLastName());
		return sb.toString();

	}
	
	public String getUserName() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentUser.get(0).getUserName());
		return sb.toString();

	}

	public int searchUsernameExists(String username) {
		return searchUsernameExists(USER_TABLE_NAME, username);

	}

//	public int searchPasswordExists(String password) {
//		return searchPasswordExists(USER_TABLE_NAME, password);
//
//	}

	public void createNewUser(String username, String firstName, String lastName, String password) {
//		insertRow.insertRowIntoUserTable(username, firstName, lastName, password);
		insertRowIntoUserTable(USER_TABLE_NAME, username, firstName, lastName, password);

	}

	public int findUserAndPasswordMatch(String username, String password) {
		int userFound = findUserAndPasswordMatch(USER_TABLE_NAME, username, password);

		if (userFound == foundUser) {
			System.out.println("user match found");
			// next step in this method below
			getUser(username, password);
			// IS THIS THE RIGHT PLACE TO DO THIS????
//			recordCollections.getRecords(username);

		} else {
			System.out.println("No match found for username and password");

		}
		return userFound;
	}
	
	
	public void updateUserFirstName(String firstName) {
		String username = currentUser.get(0).getUserName();
		updateUserFirstName(USER_TABLE_NAME, firstName, username);
		currentUser.get(0).setFirstName(firstName);
		System.out.println("updated first name");
	}
	
	public void updateUserLastName(String lastName) {
		String username = currentUser.get(0).getUserName();
		updateUserLastName(USER_TABLE_NAME, lastName, username);
		currentUser.get(0).setLastName(lastName);
		System.out.println("updated last name");
	}

	@Override
	public void getUser(String username, String password) {
		try (Connection con = DatabaseGetConnection.getConnection(); Statement stmt = con.createStatement();) {
			String query = "SELECT username, first_name, last_name, password FROM " + USER_TABLE_NAME
					+ " where username = '" + username + "' and password = '" + password + "';";

			try (ResultSet resultSet = stmt.executeQuery(query)) {

				while (resultSet.next()) {
					System.out.print(resultSet.getString("username") + "\t");
					System.out.print(resultSet.getString("first_name") + "\t");
					System.out.print(resultSet.getString("last_name") + "\t");
					System.out.print(resultSet.getString("password") + "\n");

					User user = new User(resultSet.getString("username"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("password"));
					currentUser.add(user);
					System.out.print(printUserArrayList());

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// helper method for printing out current User in currentUser ArrayList to check
	// the user has been
	// created correctly
	public String printUserArrayList() {
		StringBuilder sb = new StringBuilder();
		System.out.println("Iterating current User ArrayList...");
		for (int i = 0; i < currentUser.size(); i++) {
			System.out.println("size of current User ArrayList..." + currentUser.size());
			System.out.println("User at postion in the ArrayList..." + (i));

			sb.append(currentUser.get(i).toString() + "\n");
		}
		return sb.toString();
	}
	
	public boolean strictInputIsValid(String userInput) {
		return userInputValidator.strictInputIsValid(userInput);
		
	}

	public void logOut() {
		currentUser.clear();
	}
}
