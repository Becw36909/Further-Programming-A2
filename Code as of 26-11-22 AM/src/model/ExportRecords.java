package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		System.out.println("inside export method");
		System.out.println("FILE PATH " + filePath);
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

////	    FileWriter writer = new FileWriter("src/test/resources/custData.csv");
//	    FileWriter writer = new FileWriter(filePath);
////
////
////	    List<String> customers = new ArrayList<>(customers);
////	    List<String> references = new ArrayList<>(references);
//
//	    String recordsToWrite = records.stream().map(records(Collectors.joining(",")));
////	    String collect2 = references.stream().collect(Collectors.joining(",\n" + ":"));
//
//	    writer.write(recordsToWrite);
//	    writer.close();

	}

//	public default String convertToCSV(ArrayList<Record> records) {
//	    return Stream.of(records)
//	      .map(record::escapeSpecialCharacters)
//	      .collect(Collectors.joining(","));
//	}
//	
//	public default void givenDataArray_whenConvertToCSV_thenOutputCreated(ArrayList<Record> records) throws IOException {
//	    File csvOutputFile = new File(CSV_FILE_NAME);
//	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
//	    	records.stream()
//	          .map(records::convertToCSV)
//	          .forEach(pw::println);
//	    }
//	}
//	public default String escapeSpecialCharacters(String data) {
//	    String escapedData = data.replaceAll("\\R", " ");
//	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
//	        data = data.replace("\"", "\"\"");
//	        escapedData = "\"" + data + "\"";
//	    }
//	    return escapedData;
//	}
}
