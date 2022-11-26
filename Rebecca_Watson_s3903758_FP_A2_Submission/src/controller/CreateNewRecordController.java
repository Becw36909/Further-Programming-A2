package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.RecordCollections;

/**
 * CreateNewRecordController is a Controller class between CreateNewRecord.fxml
 * and RecordCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data is valid before passing
 * validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class CreateNewRecordController {

	/**
	 * Setting up private variables for the class.
	 */
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private GoToScene goToScene;

	private final int ZERO = 0;
	private int enoughFieldsPassed = 3;
	private int formFieldsPassed;

	@FXML
	private Button backButton;

	@FXML
	private AnchorPane header;

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
	private SplitPane mainSplitPane;

	@FXML
	private Label message;

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
	private Label timeMessage;

	@FXML
	private TextField timeTextField;

	@FXML
	private Label weightMessage;

	@FXML
	private TextField weightTextField;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {
		goToScene = new GoToScene();

		createRecordButton.setOnAction(event -> {
			recordCreatedMessage.setText("");
			formFieldsPassed = checkAllFields();

			if (validatedDate() && validateTime()) {
				if (recordCollections.searchRecordExists(dateTextField.getText(), timeTextField.getText()) != ZERO) {
					recordCreatedMessage.setTextFill(Color.RED);
					recordCreatedMessage.setText("This date and time has already been recorded!");
				} else if (formFieldsPassed > enoughFieldsPassed) {
					recordCreatedMessage.setTextFill(Color.RED);
					recordCreatedMessage.setText("Need to complete at least 3 record fields");
				} else {
					recordCreatedMessage.setTextFill(Color.GREEN);
					recordCreatedMessage.setText("New record created!");
					createNewRecord();
					clearMessageFields();
					clearInputFields();
				}
			}
		});

		cancelRecordButton.setOnAction(event -> {
			clearInputFields();
			clearMessageFields();
			recordCreatedMessage.setText("");
		});

		backButton.setOnAction(event -> {
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Collects all valid text field entries to pass to RecordCollections class to
	 * create a new record object.
	 */
	public void createNewRecord() {
		recordCollections.createNewRecord(dateTextField.getText(), timeTextField.getText(), weightTextField.getText(),
				tempTextField.getText(), bloodLowTextField.getText(), bloodHighTextField.getText(),
				notesTextArea.getText());
	}

	/**
	 * All text fields will be cleared/emptied.
	 */
	public void clearInputFields() {
		timeTextField.clear();
		dateTextField.clear();
		weightTextField.clear();
		tempTextField.clear();
		bloodLowTextField.clear();
		bloodHighTextField.clear();
		notesTextArea.clear();
	}

	/**
	 * All labels with user messages will be set to empty string.
	 */
	public void clearMessageFields() {
		timeMessage.setText("");
		dateMessage.setText("");
		weightMessage.setText("");
		tempMessage.setText("");
		bloodLowMessage.setText("");
		bloodHighMessage.setText("");
		notesMessage.setText("");
	}

	/**
	 * Runs all validation methods on text field inputs.
	 * 
	 * @return the value of formFieldsPassed (possibly incremented according to
	 *         validation methods).
	 */
	public int checkAllFields() {
		formFieldsPassed = ZERO;

		if (validatedDate()) {
			formFieldsPassed++;
		}
		if (validateTime()) {
			formFieldsPassed++;
		}
		if (validatedWeight()) {
			formFieldsPassed++;
		}
		if (validatedTemp()) {
			formFieldsPassed++;
		}
		if (validatedBloodPressureLow()) {
			formFieldsPassed++;
		}
		if (validatedBloodPressureHigh()) {
			formFieldsPassed++;
		}
		if (validatedNotes()) {
			formFieldsPassed++;
		}
		return formFieldsPassed;
	}

	/**
	 * Checks if time text field is empty or if it passes a RecordCollections time
	 * format check.
	 * 
	 * @return true if time input is valid; false otherwise.
	 */
	public boolean validateTime() {
		boolean checkedTime = false;
		if (timeTextField.getText().isEmpty()) {
			timeMessage.setTextFill(Color.RED);
			timeMessage.setText("Time cannot be blank");
		} else if (!recordCollections.timeFormatCheck(timeTextField.getText())) {
			timeMessage.setTextFill(Color.RED);
			timeMessage.setText("Time needs to be in format hh:mm");
		} else {
			timeMessage.setText("");
			checkedTime = true;
		}
		return checkedTime;
	}

	/**
	 * Checks if date text field is empty or if it passes a RecordCollections date
	 * format check.
	 * 
	 * @return true if date input is valid; false otherwise.
	 */
	public boolean validatedDate() {
		boolean checkedDate = false;
		if (dateTextField.getText().isEmpty()) {
			dateMessage.setTextFill(Color.RED);
			dateMessage.setText("Date cannot be blank");
		} else if (!recordCollections.dateFormatCheck(dateTextField.getText())) {
			dateMessage.setTextFill(Color.RED);
			dateMessage.setText("Date needs to be in format dd/MM/yyyy");
		} else {
			dateMessage.setText("");
			checkedDate = true;
		}
		return checkedDate;
	}

	/**
	 * Checks if notes text area is empty or if it passes a RecordCollections notes
	 * format check for string length.
	 * 
	 * @return true if notes input is valid; false otherwise.
	 */
	public boolean validatedNotes() {
		boolean notesChecked = false;
		String userNotes = notesTextArea.getText();
		if (!recordCollections.checkNotesLength(userNotes)) {
			notesMessage.setTextFill(Color.RED);
			notesMessage.setText("Too many words, must be 50 or less");
		} else {
			notesMessage.setText("");
			notesChecked = true;
		}
		return notesChecked;
	}

	/**
	 * Checks if blood low text field is empty or if it passes a RecordCollections
	 * check for within an acceptable range.
	 * 
	 * @return true if blood low input is valid; false otherwise.
	 */
	public boolean validatedBloodPressureLow() {
		boolean checkedBloodLow = false;
		try {
			if (bloodLowTextField.getText().isEmpty()) {
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

	/**
	 * Checks if blood high text field is empty or if it passes a RecordCollections
	 * check for within an acceptable range.
	 * 
	 * @return true if blood high input is valid; false otherwise.
	 */
	public boolean validatedBloodPressureHigh() {
		boolean checkedBloodHigh = false;
		try {
			if (bloodHighTextField.getText().isEmpty()) {
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

	/**
	 * Checks if temperature text field is empty or if it passes a RecordCollections
	 * check for within an acceptable range.
	 * 
	 * @return true if temp input is valid; false otherwise.
	 */
	public boolean validatedTemp() {
		boolean checkedTemp = false;
		try {
			if (tempTextField.getText().isEmpty()) {
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

	/**
	 * Checks if weight text field is empty or if it passes a RecordCollections
	 * check for within an acceptable range.
	 * 
	 * @return true if weight input is valid; false otherwise.
	 */
	public boolean validatedWeight() {
		boolean checkedWeight = false;
		try {
			if (weightTextField.getText().isEmpty()) {
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

}
