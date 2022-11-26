package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

/**
 * RecordsController is a Controller class for Record related screen directs
 * according to button events. Acts as the Records home screen.
 * 
 * @author Rebecca Watson
 *
 */
public class RecordsController {

	/**
	 * Setting up private variables for the class.
	 */
	private GoToScene goToScene;

	@FXML
	private Button exportButton;

	@FXML
	private AnchorPane header;

	@FXML
	private Button deleteRecordButton;

	@FXML
	private Button editRecordButton;

	@FXML
	private Button homeButton;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private Button newRecordButton;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private Label userMessage;

	@FXML
	private Button viewAllRecordsButton;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {

		goToScene = new GoToScene();

		newRecordButton.setOnAction(event -> {
			try {
				goToScene.moveToCreateNewRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		editRecordButton.setOnAction(event -> {
			try {
				goToScene.moveToEditRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		deleteRecordButton.setOnAction(event -> {
			try {
				goToScene.moveToDeleteRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		viewAllRecordsButton.setOnAction(event -> {
			userMessage.setText("Viewing All Records");

			try {
				goToScene.moveToViewAllRecordsScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		exportButton.setOnAction(event -> {
			try {
				goToScene.moveToExportRecordsScreen(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		homeButton.setOnAction(event -> {
			try {
				goToScene.moveToHomeScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
