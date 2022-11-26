
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.RecordCollections;

/**
 * EditRecordController is a Controller class between EditRecordScreen.fxml and
 * RecordCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data is valid before passing
 * validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class EditRecordController {

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
	private Label message;
	
    @FXML
    private AnchorPane header;

	@FXML
	private Button clearSelectionButton;

	@FXML
	private Button editRecordButton;

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

//        table.getSelectionModel().setCellSelectionEnabled(true);
		//DO I NEED TO HAVE THE BELOW ANYMORE????
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		goToScene = new GoToScene();

		userMessage.setText("Choose a Record to Edit");
		message.setText("");
		recordCollections.getRecords();
		viewAllExistingRecords();

		System.out.println("INSIDE EDIT records CONTROLLER");

		backButton.setOnAction(event -> {
			System.out.println("home button PRESSED");
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		clearSelectionButton.setOnAction(event -> {
			System.out.println("clear selection button PRESSED");
			table.getSelectionModel().clearSelection();
			message.setText("");

		});

		table.setOnMouseClicked(event -> {
			System.out.println("row in table click");
			table.getSelectionModel().getSelectedItem();
			System.out.println(table.getSelectionModel().getSelectedItem());
			message.setText("");

		});

//		table.setOnDragDetected(event -> {
//			System.out.println("row in table click");
//			table.getSelectionModel().getSelectedItem();
//			System.out.println(table.getSelectionModel().getSelectedItem());
//			message.setText("");
//
//		});
//		
//		table.setOnMouseDragEntered(event -> {
//			System.out.println("row in table click");
//			table.getSelectionModel().getSelectedItem();
//			System.out.println(table.getSelectionModel().getSelectedItem());
//			message.setText("");
//
//		});

		editRecordButton.setOnAction(event -> {
			System.out.println("edit button PRESSED");
			if (table.getSelectionModel().getSelectedItem() != null) {
				try {
					username = table.getSelectionModel().getSelectedItem().getUsername();
					recordDate = table.getSelectionModel().getSelectedItem().getRecordDate();
					recordTime = table.getSelectionModel().getSelectedItem().getRecordTime();
					recordCollections.addToEditRecordArray(username, recordDate, recordTime);
					goToScene.moveToRecordToEditScene(event);
				} catch (IOException e) {
					System.out.println("DIDN'T WORK");
					e.printStackTrace();
				}
			} else {
				message.setTextFill(Color.RED);
				message.setText("No record is selected!");
			}

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

//		try {
//			for (int i = 0; i > records.size(); i++) {
//				dateCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("RecordDate"));
//			}
//			
//		} catch (NullPointerException e) {
//			System.out.println("nothing in table");
//			e.printStackTrace();{
//			
//		}

	}

}