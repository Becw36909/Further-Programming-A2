package model;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private String password;

	protected User(String username, String firstName, String lastName, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return username;
	}

	// toString method, mainly used for testing purposes
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("--------------------------------------------------------------------------\n");
		sb.append("> Show User details\n");
		sb.append("--------------------------------------------------------------------------\n");
		// Note: \t is the tab character, I want to align the information
		sb.append(String.format("First Name:\t\t%s\n", firstName));
		sb.append(String.format("Last Name:\t\t%s\n", lastName));
		sb.append(String.format("Username:\t\t%s\n", username));
		sb.append(String.format("Password:\t\t%s\n", password));

//		return String.format("First Name: %s %s", firstName, lastName);
		return sb.toString();
	}

}
