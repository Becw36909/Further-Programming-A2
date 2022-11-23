package model;

/**
 * User is the class for creating a new User object.
 * 
 * @author Rebecca Watson
 *
 */
public class User {

	/**
	 * Setting up private variables for the class.
	 */

	private String firstName;
	private String lastName;
	private String username;
	private String password;

	/**
	 * Constructor of the class.
	 * 
	 * @param username  string value for username class variable
	 * @param firstName string value for firstName class variable
	 * @param lastName  string value for lastName class variable
	 * @param password  string value for password class variable
	 */
	protected User(String username, String firstName, String lastName, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	/**
	 * Accesses the firstName private variable.
	 * 
	 * @return user object firstName string.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Mutates the firstName private variable.
	 * 
	 * @param firstName updates the firstName class variable
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Accesses the lasttName private variable.
	 * 
	 * @return user object lastName string.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Mutates the lastName private variable.
	 * 
	 * @param updates the lastName class variable
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Accesses the username private variable.
	 * 
	 * @return user object username string.
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * toString method for the user object variables, mainly used for testing
	 * purposes
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("--------------------------------------------------------------------------\n");
		sb.append("> Show User details\n");
		sb.append("--------------------------------------------------------------------------\n");
		/**
		 * Note: \t is the tab character, I want to align the information
		 */
		sb.append(String.format("First Name:\t\t%s\n", firstName));
		sb.append(String.format("Last Name:\t\t%s\n", lastName));
		sb.append(String.format("Username:\t\t%s\n", username));
		sb.append(String.format("Password:\t\t%s\n", password));

		return sb.toString();
	}

}
