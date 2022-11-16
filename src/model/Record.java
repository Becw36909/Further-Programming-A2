package model;


public class Record {

	private String username;
	private String recordDate;
	private String weightKG;
	private String tempCELC;
	private String bloodPressure;
//	private int bloodPressureLow;
//	private int bloodPressureHigh;
	private String notes;

	protected Record(String username, String recordDate, String weightKG, String tempCELC, String bloodPressure, 
			String notes) {
		this.username = username;
		this.recordDate = recordDate;
		this.weightKG = weightKG;
		this.tempCELC = tempCELC;
		this.bloodPressure = bloodPressure;
//		this.bloodPressureLow = bloodPressureLow;
//		this.bloodPressureHigh = bloodPressureHigh;
		this.notes = notes;
	}

	public String getRecordDate() {
		return recordDate;
	}
	
	public String getUsername() {
		return username;
	}

	public String getWeightKG() {
		return weightKG;
	}

	public String getTempCELC() {
		return tempCELC;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}
//	public int getBloodPressureLow() {
//		return bloodPressureLow;
//	}

//	public int getBloodPressureHigh() {
//		return bloodPressureHigh;
//	}
//
	public String getNotes() {
		return notes;
	}
	
//	public String getBloodPressureString() {
//		String bloodPressureString = bloodPressureLow + "-" + bloodPressureHigh;
//		return bloodPressureString;
//	}

	// toString method, mainly used for testing purposes
	public String toString() {
		return String.format(
				"Record Date: " + recordDate.toString() + " Weight: %s TempCelc: %s Blood Pressure: %s "
						+ "Notes: %s",
				weightKG, tempCELC, bloodPressure, notes);
	}

}
