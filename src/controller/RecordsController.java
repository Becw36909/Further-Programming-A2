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

public class RecordsController {
	
//	private UserCollections userCollections;
	
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private GoToScene goToScene;
	
	// I feel like I am breaking OO here and I am not happy about it
	private ArrayList<model.Record> records = recordCollections.getRecordArray();
 
    private final ObservableList<model.Record> recordData =
            FXCollections.observableArrayList(records);


	 @FXML
	    private AnchorPane InnerSplitPaneBottom;

	    @FXML
	    private AnchorPane InnerSplitPaneTop;

	    @FXML
	    private TableColumn<model.Record, String> bloodPressCol;

	    @FXML
	    private TableColumn<model.Record, String> dateCol;

	    @FXML
	    private Button deleteRecordButton;

	    @FXML
	    private Button editRecordButton;

	    @FXML
	    private Button homeButton;

	    @FXML
	    private SplitPane innerSplitPane;

	    @FXML
	    private SplitPane mainSplitPane;

	    @FXML
	    private Button newRecordButton;

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
	    private Button viewAllRecordsButton;

	    @FXML
	    private TableColumn<model.Record, String> weightCol;
	    
    @FXML
	public void initialize() {
    	
//    	RecordCollections recordCollections = RecordCollections.getInstance();

		goToScene = new GoToScene();
//		message.setText("");

//		userMessage.setText("");

//		hideTable();
		
		viewAllExistingRecords();
		
		System.out.println("INSIDE records  CONTROLLER");
		
		newRecordButton.setOnAction(event ->  {
			System.out.println("newRecordButton PRESSED");
			try {
				goToScene.moveToCreateNewRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});
		
		editRecordButton.setOnAction(event ->  {
			System.out.println("editRecordButton PRESSED");
			userMessage.setText("Select a Record to Edit");
//			try {
//				goToScene.moveToRecordScene(event);
//			} catch (IOException e) {
//				System.out.println("DIDN'T WORK");
//				e.printStackTrace();
//			}
		});
		
		deleteRecordButton.setOnAction(event ->  {
			System.out.println("deleteRecordButton PRESSED");
			userMessage.setText("Select a Record to Delete");
//
//			try {
//				goToScene.moveToRecordScene(event);
//			} catch (IOException e) {
//				System.out.println("DIDN'T WORK");
//				e.printStackTrace();
//			}
		});
		
		viewAllRecordsButton.setOnAction(event ->  {
			userMessage.setText("Viewing All Records");
			System.out.println("viewAllRecordsButton PRESSED");
		
			recordCollections.getRecords();
			
			viewAllExistingRecords();
//			try {
//				goToScene.moveToRecordScene(event);
//			} catch (IOException e) {
//				System.out.println("DIDN'T WORK");
//				e.printStackTrace();
//			}
		});
		
		homeButton.setOnAction(event ->  {
			System.out.println("home button PRESSED");
			try {
				goToScene.moveToHomeScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});
	}
	
    public void hideTable() {
    	table.setVisible(false);
    }
	
	public String newRecord() {
		return null;

	}

	public String editExistingRecord() {
		return null;

	}

	public String deleteExistingRecord() {
		return null;

	}

	public void viewAllExistingRecords() {
//		recordData
//		TableView<model.Record> table = new TableView<model.Record>();
//		TableColumn<model.Record, String> dateCol = new TableColumn<model.Record, String>("Date");
//		recordData.clear();
		dateCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("RecordDate"));
		weightCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("WeightKG"));
		tempCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("TempCELC"));
		bloodPressCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("BloodPressure"));
		notesCol.setCellValueFactory(new PropertyValueFactory<model.Record, String>("Notes"));
		
		table.setItems(recordData);
		
//		table.getColumns().addAll(dateCol, weightCol);
//		table.getColumns().addAll(dateCol);
		
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
		
//		table.getColumns().add(dateCol);
		}


	public String exportSelectedRecords() {
		return null;

	}

}
