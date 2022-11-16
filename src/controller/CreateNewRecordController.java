package controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.RecordCollections;

public class CreateNewRecordController {

//	private RecordCollections recordCollections;
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private GoToScene goToScene;

//	private int validatedFields = 0;
	private final int ZERO = 0;
	private int allFieldsValidated = 0;
	private int recordFormErrors;

	@FXML
	private Label bloodHighMessage;

	@FXML
	private TextField bloodHighTextField;

	@FXML
	private Label bloodLowMessage;

	@FXML
	private TextField bloodLowTextField;

	@FXML
	private Button cancelRecordButton;

	@FXML
	private Button createRecordButton;

	@FXML
	private Label dateMessage;

	@FXML
	private TextField dateTextField;

	@FXML
	private Button deleteRecordButton;

	@FXML
	private Button editRecordButton;

	@FXML
	private Button homeButton;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private Label message;

	@FXML
	private Button newRecordButton;

	@FXML
	private Label notesMessage;

	@FXML
	private TextArea notesTextArea;

	@FXML
	private Label recordCreatedMessage;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private Label tempMessage;

	@FXML
	private TextField tempTextField;

	@FXML
	private Button viewAllRecordsButton;

	@FXML
	private Label weightMessage;

	@FXML
	private TextField weightTextField;

	@FXML
	public void initialize() {

//		RecordCollections recordCollections = RecordCollections.getInstance();

		goToScene = new GoToScene();

		System.out.println("INSIDE records  CONTROLLER");

		newRecordButton.setOnAction(event -> {
			System.out.println("newRecordButton PRESSED");
			recordCreatedMessage.setText("");
		});

		createRecordButton.setOnAction(event -> {
			recordCreatedMessage.setText("");
			System.out.println("createRecordButton PRESSED");
			recordFormErrors = checkAllFields();
			System.out.println("recordFormErrors: " + recordFormErrors);

			if (recordFormErrors == allFieldsValidated) {
				System.out.println("all fields passed");
				System.out.println("recordFormErrors: " + recordFormErrors);
				recordCreatedMessage.setTextFill(Color.GREEN);
				recordCreatedMessage.setText("New record created!");
				createNewRecord();

				clearInputFields();
			}

		});

		cancelRecordButton.setOnAction(event -> {
			System.out.println("cancelRecordButton PRESSED");
			clearInputFields();
//			dateTextField.clear();
//			weightTextField.clear();
//			tempTextField.clear();
//			bloodLowTextField.clear();
//			bloodHighTextField.clear();
//			notesTextArea.clear();
			clearMessageFields();
//			dateMessage.setText("");
//			weightMessage.setText("");
//			tempMessage.setText("");
//			bloodLowMessage.setText("");
//			bloodHighMessage.setText("");
//			notesMessage.setText("");
			recordCreatedMessage.setText("");
		});

		editRecordButton.setOnAction(event -> {
			System.out.println("editRecordButton PRESSED");

			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		deleteRecordButton.setOnAction(event -> {
			System.out.println("deleteRecordButton PRESSED");

			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		viewAllRecordsButton.setOnAction(event -> {

			System.out.println("viewAllRecordsButton PRESSED");
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		homeButton.setOnAction(event -> {
			System.out.println("home button PRESSED");
			try {
				goToScene.moveToHomeScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});
	}

	private void createNewRecord() {
		recordCollections.createNewRecord(dateTextField.getText(), weightTextField.getText(), tempTextField.getText(),
				bloodLowTextField.getText(), bloodHighTextField.getText(), notesTextArea.getText());

	}

	private void clearInputFields() {
		dateTextField.clear();
		weightTextField.clear();
		tempTextField.clear();
		bloodLowTextField.clear();
		bloodHighTextField.clear();
		notesTextArea.clear();
	}

	private void clearMessageFields() {
		dateMessage.setText("");
		weightMessage.setText("");
		tempMessage.setText("");
		bloodLowMessage.setText("");
		bloodHighMessage.setText("");
		notesMessage.setText("");
	}

	private int checkAllFields() {
		recordFormErrors = 0;

		if (!validatedDate()) {
			recordFormErrors++;
		}
		if (!validatedWeight()) {
			recordFormErrors++;
		}
		if (!validatedTemp()) {
			recordFormErrors++;
		}
		if (!validatedBloodPressureLow()) {
			recordFormErrors++;
		}
		if (!validatedBloodPressureHigh()) {
			recordFormErrors++;
		}
		if (!validatedNotes()) {
			recordFormErrors++;

		}
		return recordFormErrors;
	}

	private boolean validatedNotes() {
		boolean notesChecked = false;
		if (notesTextArea.getText().isEmpty()) {
			notesMessage.setTextFill(Color.RED);
			notesMessage.setText("Must enter at least a few words below");
		} else {
			String userNotes = notesTextArea.getText();
			if (!recordCollections.checkNotesLength(userNotes)) {
				notesMessage.setTextFill(Color.RED);
				notesMessage.setText("Too many words, must be 50 or less");
			} else {
				notesMessage.setText("");
				notesChecked = true;
			}
		}
		return notesChecked;
	}

	private boolean validatedBloodPressureLow() {
		boolean checkedBloodLow = false;
		try {
			if (bloodLowTextField.getText().isEmpty()) {
				bloodLowMessage.setTextFill(Color.RED);
				bloodLowMessage.setText("Must complete field");
//			} else if (!bloodLowTextField.getText().isEmpty()) {
			} else {

				Double bloodLow = Double.parseDouble(bloodLowTextField.getText());
				if (!recordCollections.bloodPressureHigh(bloodLow)) {
					System.out.println("inside blood low ELSE");
					bloodLowMessage.setTextFill(Color.RED);
					bloodLowMessage.setText("Temp not in safe range!");
				} else {
					bloodLowMessage.setText("");
					checkedBloodLow = true;
				}
			}
		} catch (NumberFormatException e) {
			bloodLowMessage.setTextFill(Color.RED);
			bloodLowMessage.setText("Enter a valid number");
		}
		return checkedBloodLow;
	}

	private boolean validatedBloodPressureHigh() {
		boolean checkedBloodHigh = false;
		try {
			if (bloodHighTextField.getText().isEmpty()) {
				bloodHighMessage.setTextFill(Color.RED);
				bloodHighMessage.setText("Must complete field");
//			} else if (!bloodHighTextField.getText().isEmpty()) {
			} else {
				Double bloodHigh = Double.parseDouble(bloodHighTextField.getText());
				if (!recordCollections.bloodPressureHigh(bloodHigh)) {
					bloodHighMessage.setTextFill(Color.RED);
					bloodHighMessage.setText("Temp not in safe range!");
				} else {
					System.out.println("inside blood high ELSE");
					bloodHighMessage.setText("");
					checkedBloodHigh = true;
				}
			}
		} catch (NumberFormatException e) {
			bloodHighMessage.setTextFill(Color.RED);
			bloodHighMessage.setText("Enter a valid number");
		}
		return checkedBloodHigh;
	}

	private boolean validatedTemp() {
		boolean checkedTemp = false;
		try {
			if (tempTextField.getText().isEmpty()) {
				tempMessage.setTextFill(Color.RED);
				tempMessage.setText("Temperature cannot be blank");
//			} else if (!tempTextField.getText().isEmpty()) {
			} else {
				Double temp = Double.parseDouble(tempTextField.getText());
				if (!recordCollections.tempRange(temp)) {
					tempMessage.setTextFill(Color.RED);
					System.out.println("inside TEMP second if");
					tempMessage.setText("Temperature not in safe range - seek help!");
				} else {
					System.out.println("inside TEMP nested else");
					tempMessage.setText("");
					checkedTemp = true;
				}
			}
		} catch (NumberFormatException e) {
			tempMessage.setTextFill(Color.RED);
			tempMessage.setText("Enter a valid number");
		}
		return checkedTemp;
	}

	private boolean validatedWeight() {
		boolean checkedWeight = false;
		try {
			if (weightTextField.getText().isEmpty()) {
				weightMessage.setTextFill(Color.RED);
				weightMessage.setText("Weight cannot be blank");
//			} else if (!weightTextField.getText().isEmpty()) {
			} else {
				Double weight = Double.parseDouble(weightTextField.getText());
				if (!recordCollections.weightRange(weight)) {
					weightMessage.setTextFill(Color.RED);
					weightMessage.setText("Weight must be in range 0-650 kgs");
				} else {
					weightMessage.setText("");
					checkedWeight = true;
				}
			}
		} catch (NumberFormatException e) {
			weightMessage.setTextFill(Color.RED);
			weightMessage.setText("Enter a valid number");
		}
		return checkedWeight;
	}

	private boolean validatedDate() {
		boolean checkedDate = false;
		if (dateTextField.getText().isEmpty()) {
			dateMessage.setTextFill(Color.RED);
			dateMessage.setText("Date cannot be blank");
		} else if (!recordCollections.dateFormatCheck(dateTextField.getText())) {
			dateMessage.setTextFill(Color.RED);
			dateMessage.setText("Date needs to be in format dd/MM/yyyy");
		} else if (recordCollections.searchRecordExists(dateTextField.getText()) != ZERO) {
			dateMessage.setTextFill(Color.RED);
			dateMessage.setText("This date has already been recorded!");
		} else {
			dateMessage.setText("");
			checkedDate = true;
		}
		return checkedDate;

	}

}
