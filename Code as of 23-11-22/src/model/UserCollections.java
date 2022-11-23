package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * UserCollections is a model class responsible for communicating with
 * controllers requesting creating, access, deletion and updates to user
 * objects. This class is in charge of communicating with other backend classes
 * and interfaces involved in creating, accessing, deleting and updating user
 * object data.
 * 
 * @author Rebecca Watson
 *
 */
public class UserCollections implements InsertRowIntoUserTable, SearchUsernameExists, SearchPasswordExists,
		FindUserAndPasswordMatch, GetUser, UpdateUserFirstName, UpdateUserLastName {

	/**
	 * Setting up private variables for the class.
	 */
	private UserInputValidator userInputValidator;
	private int foundUser = 1;
	private static UserCollections usercollections;
	final String USER_TABLE_NAME = "User";
	private ArrayList<User> currentUser;

	/**
	 * Constructor of the class.
	 */
	protected UserCollections() {
		currentUser = new ArrayList<User>();
		userInputValidator = new UserInputValidator();

	}

	/**
	 * Synchronizes a singleton instance of the UserCollections class.
	 * 
	 * @return the current instance of the singleton UserCollections class.
	 */
	public static synchronized UserCollections getInstance() {
		if (usercollections == null) {
			usercollections = new UserCollections();
		}
		return usercollections;
	}

	/**
	 * Finds the user object's first name in the currentUser arrayList.
	 * 
	 * @return a string of the user object first name.
	 */
	public String getUserFirstName() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentUser.get(0).getFirstName());
		return sb.toString();

	}

	/**
	 * Finds the user object's last name in the currentUser arrayList.
	 * 
	 * @return a string of the user object last name.
	 */
	public String getUserLastName() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentUser.get(0).getLastName());
		return sb.toString();

	}

	/**
	 * Finds the user object's username in the currentUser arrayList.
	 * 
	 * @return a string of the user object username.
	 */
	public String getUserName() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentUser.get(0).getUserName());
		return sb.toString();

	}

	/**
	 * Performs database search to see if a username already exists.
	 * 
	 * @param username username string for database match
	 * @return an integer with a value of 0 or 1.
	 */
	public int searchUsernameExists(String username) {
		return searchUsernameExists(USER_TABLE_NAME, username);

	}

//	public int searchPasswordExists(String password) {
//		return searchPasswordExists(USER_TABLE_NAME, password);
//
//	}

	/**
	 * Inputs a new row for a user into the connected database user table.
	 * 
	 * @param username  username string for database user column value
	 * @param firstName first name string for database user column value
	 * @param lastName  last name string for database user column value
	 * @param password  password string for database user column value
	 */
	public void createNewUser(String username, String firstName, String lastName, String password) {
		insertRowIntoUserTable(USER_TABLE_NAME, username, firstName, lastName, password);

	}

	/**
	 * Performs database search to match a user with the inputs.
	 * 
	 * @param username username string for database match
	 * @param password password string for database match
	 * @return an integer with a value of 0 or 1.
	 */
	public int findUserAndPasswordMatch(String username, String password) {

		int userFound = findUserAndPasswordMatch(USER_TABLE_NAME, username, password);

		if (userFound == foundUser) {
			System.out.println("user match found");
			getUser(username, password);
		} else {
			System.out.println("No match found for username and password");
		}
		return userFound;
	}

	/**
	 * Updates database and user in currentUser arrayList - however the arrayList
	 * update may not be needed, need to reassess.
	 * 
	 * @param firstName the string to update the database first name value with
	 */
	public void updateUserFirstName(String firstName) {
		String username = currentUser.get(0).getUserName();
		updateUserFirstName(USER_TABLE_NAME, firstName, username);
		currentUser.get(0).setFirstName(firstName);
		System.out.println("updated first name");
	}

	/**
	 * Updates database and user in currentUser arrayList - however the arrayList
	 * update may not be needed, need to reassess.
	 * 
	 * @param lastName the string to update the database last name value with
	 */
	public void updateUserLastName(String lastName) {
		String username = currentUser.get(0).getUserName();
		updateUserLastName(USER_TABLE_NAME, lastName, username);
		currentUser.get(0).setLastName(lastName);
		System.out.println("updated last name");
	}

	/**
	 * Finds the matching user row in the user database table and adds the user to
	 * the currentUser arrayList.
	 */
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

	/**
	 * Helper method for printing out current user in currentUser ArrayList to check
	 * the user has been retrieved correctly.
	 * 
	 * @return a string of the user in the currentUser arrayList.
	 */
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

	/**
	 * Checks that user input is valid according to a pattern in the validator
	 * interface.
	 * 
	 * @param userInput the string to check in the validator
	 * @return true if the userInput string matches the specified pattern; false
	 *         otherwise.
	 */
	public boolean strictInputIsValid(String userInput) {
		return userInputValidator.strictInputIsValid(userInput);

	}

	/**
	 * Clears the user from the currentUser arrayList.
	 */
	public void logOut() {
		currentUser.clear();
	}
}
