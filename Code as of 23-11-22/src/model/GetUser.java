package model;

/**
 * GetUser is an interface providing a getUser method with a database
 * connection.
 * 
 * @author Rebecca Watson
 *
 */
public interface GetUser extends DatabaseGetConnection {

	/**
	 * Only method of the interface.
	 * 
	 * @param username the username to be matched in the statement in the method
	 *                 implementation
	 * @param password the password to be matched in the statement in the method
	 *                 implementation
	 */
	void getUser(String username, String password);

}
