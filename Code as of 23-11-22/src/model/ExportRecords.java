package model;

//public interface ExportRecords extends Serializable{

/**
 * ExportRecords is an interface for exporting record objects into a .csv file.
 * 
 * @author Rebecca Watson
 *
 */
public interface ExportRecords {

	/**
	 * Takes record objects and streams them to a .csv file. 
	 */
	default void exportToCSV() {

	}

}
