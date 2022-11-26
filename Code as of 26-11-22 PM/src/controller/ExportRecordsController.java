package controller;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import model.RecordCollections;

public class ExportRecordsController {

	/**
	 * Setting up private variables for the class.
	 */
	private RecordCollections recordCollections = RecordCollections.getInstance();

	private DirectoryChooser directoryChooser = new DirectoryChooser();

	private GoToScene goToScene;

	@FXML
	private AnchorPane InnerSplitPaneBottom;
	
    @FXML
    private AnchorPane header;
    
	@FXML
	private Button backButton;

	@FXML
	private Button chooseFilePathButton;

	@FXML
	private Label confirmationMessage;

	@FXML
	private Button exportAllRecordsButton;

	@FXML
	private TextField fileNameTextField;

	@FXML
	private TextField filePathTextField;

	@FXML
	private SplitPane innerSplitPane;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private Label message;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private Label userMessage;

	@FXML
	public void initialize() {
		goToScene = new GoToScene();
		recordCollections.getRecords();

		backButton.setOnAction(event -> {
			System.out.println("home button PRESSED");
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		chooseFilePathButton.setOnAction(event -> {
			System.out.println("choose file path button PRESSED");

			File selectedDirectory = directoryChooser.showDialog(goToScene.getPrimaryStage());
			if (selectedDirectory != null) {
				filePathTextField.setText(selectedDirectory.getAbsolutePath());
			}
			System.out.println(selectedDirectory.getAbsolutePath());

		});

		exportAllRecordsButton.setOnAction(event -> {

			if (!filePathTextField.getText().isEmpty() && !fileNameTextField.getText().isEmpty()) {
				System.out.println("exportAllRecordsButton button PRESSED");
				recordCollections.exportAllRecordsToCSV(filePathTextField.getText(), fileNameTextField.getText());
				confirmationMessage.setTextFill(Color.GREEN);
				confirmationMessage.setText("All records successfully exported.");
			} else {
				confirmationMessage.setTextFill(Color.RED);
				confirmationMessage.setText("Must choose a folder and a file name to save!");
			}
		});
	}


}
