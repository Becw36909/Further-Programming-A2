package model;

public class Profile {
	
	private String username;
	private String password;
	
	protected Profile (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	// toString method, mainly used for testing purposes
	public String toString() {
		return String.format("Username: %s, Password: %s", username, password);
	}
}
