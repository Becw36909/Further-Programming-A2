package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GoToScene is a Controller class for all screen loads in the program. I wanted
 * to make an interface, but was having problems making one work so settled for
 * a class that other controller classes could extend from..... might refactor
 * later.
 * 
 * @author Rebecca Watson
 *
 */

public class GoToScene {

	/**
	 * Setting up private variables for the class.
	 */
	private Parent root;
	private Stage primaryStage;
	private Scene scene;
	private String loginFxml = "/view/Login.fxml";
	private String loginTitle = "Log In to MyHealth";
	private String homeScreenFxml = "/view/HomeScreen.fxml";
	private String homeScreenTitle = "MyHealth Application";
	private String createProfileFxml = "/view/CreateProfile.fxml";
	private String createProfileTitle = "Create a Profile";
	private String recordsScreenFxml = "/view/RecordScreen.fxml";
	private String recordsScreenTitle = "MyHealth Application - Records";
	private String profileFxml = "/view/Profile.fxml";
	private String profileScreenTitle = "MyHealth Application - Profile";
	private String createNewRecordScreen = "MyHealth Application - Create a New Record";
	private String createNewRecordFxml = "/view/CreateNewRecord.fxml";
	private String viewAllRecordsTitle = "MyHealth Application - View All Records";
	private String viewAllRecordsFxml = "/view/viewAllRecordsScreen.fxml";
	private String editRecordTitle = "MyHealth Application - Edit a Record";
	private String editRecordScreenFxml = "/view/EditRecordScreen.fxml";
	private String deleteRecordTitle = "MyHealth Application - Delete a Record";
	private String deleteRecordScreenFxml = "/view/DeleteRecordScreen.fxml";
	private String recordToEditTitle = "MyHealth Application - Edit an Individual Record";
	private String recordToEditScreenFxml = "/view/RecordToEditScreen.fxml";

	/**
	 * @param event    triggering event
	 * @param fxmlFile file that generates the GUI view
	 * @param setTitle title on the GUI
	 * @throws IOException If an input or output exception occurred
	 */
	public void loadScene(ActionEvent event, String fxmlFile, String setTitle) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxmlFile));
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root, 800, 600);

		primaryStage.setTitle(setTitle);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToRecordToEditScene(ActionEvent event) throws IOException {
		loadScene(event, recordToEditScreenFxml, recordToEditTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToDeleteRecordScene(ActionEvent event) throws IOException {
		loadScene(event, deleteRecordScreenFxml, deleteRecordTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToEditRecordScene(ActionEvent event) throws IOException {
		loadScene(event, editRecordScreenFxml, editRecordTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToCreateNewRecordScene(ActionEvent event) throws IOException {
		loadScene(event, createNewRecordFxml, createNewRecordScreen);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToRecordScene(ActionEvent event) throws IOException {
		loadScene(event, recordsScreenFxml, recordsScreenTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToCreateProfileScene(ActionEvent event) throws IOException {
		loadScene(event, createProfileFxml, createProfileTitle);
	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToHomeScene(ActionEvent event) throws IOException {
		loadScene(event, homeScreenFxml, homeScreenTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToLogInScene(ActionEvent event) throws IOException {
		loadScene(event, loginFxml, loginTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToProfileScene(ActionEvent event) throws IOException {
		loadScene(event, profileFxml, profileScreenTitle);

	}

	/**
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToViewAllRecordsScene(ActionEvent event) throws IOException {
		loadScene(event, viewAllRecordsFxml, viewAllRecordsTitle);
	}

}
