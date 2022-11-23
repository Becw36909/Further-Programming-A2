package model;

/**
 * ValidateRange is an interface for checking the range of inputs are within
 * acceptable values defined by the private class variables.
 * 
 * @author Rebecca Watson
 *
 */
public interface ValidateRange {

	/**
	 * Setting up private variables for the class.
	 */
	static final double weightMin = 0;
	static final double weightMax = 650;
	static final double tempMin = 35;
	static final double tempMax = 42.3;
	static final double lowPressureMin = 0;
	static final double lowPressureMax = 200;
	static final double highPressureMin = 0;
	static final double highPressureMax = 250;

	/**
	 * Checks the double weight input is within an acceptable range according to
	 * private class variables.
	 * 
	 * @param weight the double weight input value to be checked
	 * @return true if the input double weight is within range of weightMin and
	 *         weightMax; false otherwise.
	 */
	default boolean weightRange(double weight) {
		boolean validatedWeight = false;
		if (weight >= weightMin && weight <= weightMax) {
			validatedWeight = true;
		}
		return validatedWeight;

	}

	/**
	 * Checks the double temp input is within an acceptable range according to
	 * private class variables.
	 * 
	 * @param temp the double temp input value to be checked
	 * @return true if the input double temp is within range of tempMin and tempMax;
	 *         false otherwise.
	 */
	default boolean tempRange(double temp) {
		boolean validatedTemp = false;
		if (temp >= tempMin && temp <= tempMax) {
			validatedTemp = true;
		}
		return validatedTemp;

	}

	/**
	 * Checks the double lowPressure input is within an acceptable range according
	 * to private class variables.
	 * 
	 * @param lowPressure the double lowPressure input value to be checked
	 * @return true if the input double lowPressure is within range of
	 *         lowPressureMin and lowPressureMax; false otherwise.
	 */
	default boolean bloodPressureLow(double lowPressure) {
		boolean validatedLowPressure = false;
		if (lowPressure >= lowPressureMin && lowPressure <= lowPressureMax) {
			validatedLowPressure = true;
		}
		return validatedLowPressure;

	}

	/**
	 * Checks the double highPressure input is within an acceptable range according
	 * to private class variables.
	 * 
	 * @param highPressure the double highPressure input value to be checked
	 * @return true if the input double highPressure is within range of
	 *         highPressureMin and highPressureMax; false otherwise.
	 */
	default boolean bloodPressureHigh(double highPressure) {
		boolean validatedHighPressure = false;
		if (highPressure >= highPressureMin && highPressure <= highPressureMax) {
			validatedHighPressure = true;
		}
		return validatedHighPressure;

	}
}
