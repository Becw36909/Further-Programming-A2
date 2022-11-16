package model;

public interface validateRange {

	static final double weightMin = 0;
	static final double weightMax = 650;
	static final double tempMin = 35;
	static final double tempMax = 42.3;
	static final double lowPressureMin = 0;
	static final double lowPressureMax = 200;
	static final double highPressureMin = 0;
	static final double highPressureMax = 250;

	default boolean weightRange(double weight) {
		boolean validatedWeight = false;
		if (weight >= weightMin && weight <= weightMax) {
			validatedWeight = true;
		}
		return validatedWeight;

	}

	default boolean tempRange(double temp) {
		boolean validatedTemp = false;
		if (temp >= tempMin && temp <= tempMax) {
			validatedTemp = true;
		}
		return validatedTemp;

	}

	default boolean bloodPressureLow(double lowPressure) {
		boolean validatedLowPressure = false;
		if (lowPressure >= lowPressureMin && lowPressure <= lowPressureMax) {
			validatedLowPressure = true;
		}
		return validatedLowPressure;

	}

	default boolean bloodPressureHigh(double highPressure) {
		boolean validatedHighPressure = false;
		if (highPressure >= highPressureMin && highPressure <= highPressureMax) {
			validatedHighPressure = true;
		}
		return validatedHighPressure;

	}
}
