package model;

/**
 * Record is the class for creating new record objects.
 * 
 * @author Rebecca Watson
 *
 */
public class Record {

	/**
	 * Setting up private variables for the class.
	 */
	private String username;
	private String recordDate;
	private String recordTime;
	private String weightKG;
	private String tempCELC;
	private String bloodPressure;
	private String notes;

	/**
	 * Constructor of the class.
	 * 
	 * @param username      string value for username class variable
	 * @param recordDate    string value for recordDate class variable
	 * @param recordTime    string value for recordTime class variable
	 * @param weightKG      string value for weightKG class variable
	 * @param tempCELC      string value for tempCELC class variable
	 * @param bloodPressure string value for bloodPressure class variable
	 * @param notes         string value for notes class variable
	 */
	protected Record(String username, String recordDate, String recordTime, String weightKG, String tempCELC,
			String bloodPressure, String notes) {
		this.username = username;
		this.recordDate = recordDate;
		this.recordTime = recordTime;
		this.weightKG = weightKG;
		this.tempCELC = tempCELC;
		this.bloodPressure = bloodPressure;
		this.notes = notes;
	}

	/**
	 * Accesses the recordDate private variable.
	 * 
	 * @return record object recordDate string.
	 */
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * Accesses the recordTime private variable.
	 * 
	 * @return record object recordTime string.
	 */
	public String getRecordTime() {
		return recordTime;
	}

	/**
	 * Accesses the username private variable.
	 * 
	 * @return record object username string.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Accesses the weightKG private variable.
	 * 
	 * @return record object weightKG string.
	 */
	public String getWeightKG() {
		return weightKG;
	}

	/**
	 * Accesses the tempCELC private variable.
	 * 
	 * @return record object tempCELC string.
	 */
	public String getTempCELC() {
		return tempCELC;
	}

	/**
	 * Accesses the bloodPressure private variable.
	 * 
	 * @return record object bloodPressure string.
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * Accesses the notes private variable.
	 * 
	 * @return record object notes string.
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * toString method for the record object variables, mainly used for testing purposes
	 */
	public String toString() {
		return String.format("Record Date: %s Time: %s Weight: %s TempCelc: %s Blood Pressure: %s " + "Notes: %s",
				recordDate, recordTime, weightKG, tempCELC, bloodPressure, notes);
	}

}
