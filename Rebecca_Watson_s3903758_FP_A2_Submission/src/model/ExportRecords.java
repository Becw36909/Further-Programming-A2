package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

//public interface ExportRecords extends Serializable{

/**
 * ExportRecords is an interface for exporting record objects into a .csv file.
 * 
 * @author Rebecca Watson
 *
 */
public interface ExportRecords {

	String CSVheaders = "Record Date, Time, Weight, TempCelc, Blood Pressure, Notes";
	String fileSeparator = FileSystems.getDefault().getSeparator();
	String CSVfileExtension = ".csv";

	/**
	 * Takes record objects and writes them to a new .csv file.
	 * 
	 * @param filePath
	 * @param fileName
	 * @param records
	 */
	default void exportAllRecordsToCSV(String filePath, String fileName, ArrayList<Record> records) {

		File csvFile = new File(filePath + fileSeparator + fileName + CSVfileExtension);

		FileWriter fileWriter;
		System.out.println(filePath + fileSeparator + fileName + CSVfileExtension);

		try {
			fileWriter = new FileWriter(csvFile);
			StringBuilder recordLine = new StringBuilder();
			recordLine.append(CSVheaders);
			recordLine.append("\n");
			for (Record record : records) {
				recordLine.append(record.toCSVString());
				recordLine.append("\n");
			}
			fileWriter.write(recordLine.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
