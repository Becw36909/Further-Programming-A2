package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.RecordCollections;

/**
 * DeleteRecordController is a Controller class between DeleteRecordScreen.fxml
 * and RecordCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data is valid before passing
 * validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class DeleteRecordController {

	/**
	 * Setting up private variables for the class.
	 */
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private GoToScene goToScene;

	private String username;

	private String recordDate;

	private String recordTime;

	private ArrayList<model.Record> records;

	private ObservableList<model.Record> recordData = null;

	@FXML
	private Button noButton;

	@FXML
	private AnchorPane header;

	@FXML
	private Button yesButton;

	@FXML
	private Button clearSelectionButton;

	@FXML
	private Label confirmationMessage;

	@FXML
	private Button deleteRecordButton;

	@FXML
	private Button backButton;

	@FXML
	private AnchorPane InnerSplitPaneBottom;

	@FXML
	private AnchorPane InnerSplitPaneTop;

	@FXML
	private TableColumn<model.Record, String> bloodPressCol;

	@FXML
	private TableColumn<model.Record, String> dateCol;

	@FXML
	private SplitPane innerSplitPane;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private TableColumn<model.Record, String> notesCol;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private TableView<model.Record> table;

	@FXML
	private TableColumn<model.Record, String> tempCol;

	@FXML
	private Label userMessage;

	@FXML
	private TableColumn<model.Record, String> weightCol;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {

		goToScene = new GoToScene();
		hideConfirmDeleteControls();

		userMessage.setText("Select a Record to Delete");
		recordCollections.getRecords();
		viewAllExistingRecords();

		backButton.setOnAction(event -> {
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		deleteRecordButton.setOnAction(event -> {
			if (table.getSelectionModel().getSelectedItem() != null) {
				confirmationMessage.setText("Are you sure you want to delete this record?");
				showConfirmDeleteControls();
			} else {
				confirmationMessage.setTextFill(Color.RED);
				confirmationMessage.setText("No record is selected!");
			}
		});

		noButton.setOnAction(event -> {
			confirmationMessage.setText("");
			hideConfirmDeleteControls();
		});

		yesButton.setOnAction(event -> {
			username = table.getSelectionModel().getSelectedItem().getUsername();
			recordDate = table.getSelectionModel().getSelectedItem().getRecordDate();
			recordTime = table.getSelectionModel().getSelectedItem().getRecordTime();
			recordCollections.deleteExistingRecord(username, recordDate, recordTime);
			confirmationMessage.setTextFill(Color.GREEN);
			confirmationMessage.setText("Record successfully deleted");
			hideConfirmDeleteControls();
			viewAllExistingRecords();
		});

		clearSelectionButton.setOnAction(event -> {
			table.getSelectionModel().clearSelection();
			hideConfirmDeleteControls();
			confirmationMessage.setText("");
		});

		table.setOnMouseClicked(event -> {
			table.getSelectionModel().getSelectedItem();
			confirmationMessage.setText("");
		});

	}

	/**
	 * Sets up table view with the required column attributes, from the
	 * RecordCollection records array being put into the Observable Array List
	 * recordData.
	 */
	public void viewAllExistingRecords() {
		records = recordCollections.getRecordArray();
		recordData = FXCollections.observableArrayList(records);

		dateCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("RecordDate"));
		weightCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("WeightKG"));
		tempCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("TempCELC"));
		bloodPressCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("BloodPressure"));
		notesCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("Notes"));

		table.setItems(recordData);
	}

	/**
	 * Sets no and yes buttons to invisible on screen.
	 */
	public void hideConfirmDeleteControls() {
		noButton.setVisible(false);
		yesButton.setVisible(false);
	}

	/**
	 * Sets no and yes buttons and confirmation message to visible on screen.
	 */
	public void showConfirmDeleteControls() {
		noButton.setVisible(true);
		yesButton.setVisible(true);
		confirmationMessage.setVisible(true);
	}

}