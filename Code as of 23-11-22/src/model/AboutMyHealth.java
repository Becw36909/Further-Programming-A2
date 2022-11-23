package model;

/**
 * AboutMyHealth holds the version number for the MyHealth Application. To hold
 * further updates for the version number and any extra MyHealth company
 * specific information.
 * 
 * @author Rebecca Watson
 *
 */
public class AboutMyHealth {

	/**
	 * current MyHealth version
	 */
	private static final String VERSION_NUMBER = "MyHealth Version 1.1.2";

	/**
	 * Class constructor.
	 */
	public AboutMyHealth() {
	}

	/**
	 * Gets private class variable VERSION_NUMBER.
	 * 
	 * @return private class variable VERSION_NUMBER.
	 */
	public String getVersionNumber() {
		return VERSION_NUMBER;
	}
}
