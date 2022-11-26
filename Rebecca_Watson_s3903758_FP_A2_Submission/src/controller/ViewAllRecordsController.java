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
import model.RecordCollections;

/**
 * ViewAllRecordsController is a Controller class between ViewAllRecords.fxml
 * and RecordCollections.java. Generates a table view of all records for the
 * logged in user.
 * 
 * @author Rebecca Watson
 *
 */
public class ViewAllRecordsController {

	/**
	 * Setting up private variables for the class.
	 */
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private GoToScene goToScene;

	private ArrayList<model.Record> records;

	private ObservableList<model.Record> recordData = null;

	@FXML
	private Button backButton;

	@FXML
	private AnchorPane header;

	@FXML
	private AnchorPane InnerSplitPaneBottom;

	@FXML
	private AnchorPane InnerSplitPaneTop;

	@FXML
	private TableColumn<model.Record, String> bloodPressCol;

	@FXML
	private TableColumn<model.Record, String> timeCol;

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

		userMessage.setText("Viewing All Records");
		recordCollections.getRecords();
		viewAllExistingRecords();

		backButton.setOnAction(event -> {
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
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
		timeCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("RecordTime"));
		weightCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("WeightKG"));
		tempCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("TempCELC"));
		bloodPressCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("BloodPressure"));
		notesCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("Notes"));

		table.setItems(recordData);
	}

}
