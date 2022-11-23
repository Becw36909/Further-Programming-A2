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

		System.out.println("INSIDE records  CONTROLLER");

		newRecordButton.setOnAction(event -> {
			System.out.println("newRecordButton PRESSED");
			try {
				goToScene.moveToCreateNewRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		editRecordButton.setOnAction(event -> {
			System.out.println("editRecordButton PRESSED");
			try {
				goToScene.moveToEditRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		deleteRecordButton.setOnAction(event -> {
			System.out.println("deleteRecordButton PRESSED");//
			try {
				goToScene.moveToDeleteRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		viewAllRecordsButton.setOnAction(event -> {
			userMessage.setText("Viewing All Records");
			System.out.println("viewAllRecordsButton PRESSED");

			try {
				goToScene.moveToViewAllRecordsScene(event);
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

}
