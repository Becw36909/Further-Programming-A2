package model;

/**
 * GetRecords is an interface providing a getRecords method with a database
 * connection.
 * 
 * @author Rebecca Watson
 *
 */
public interface GetRecords extends DatabaseGetConnection {

	/**
	 * Only method of the interface. 
	 */
	void getRecords();
}
