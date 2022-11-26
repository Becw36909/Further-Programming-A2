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

	private String exportRecordsTitle = "MyHealth Application - Export Records";

	private String exportRecordsScreenFxml = "/view/ExportRecordsScreen.fxml";

	/**
	 * Gives the root scene and primary stage for all new scenes to be loaded into.
	 * 
	 * @param event    triggering event
	 * @param fxmlFile file that generates the GUI view
	 * @param setTitle title on the GUI
	 * @throws IOException If an input or output exception occurred
	 */
	public void loadScene(ActionEvent event, String fxmlFile, String setTitle) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxmlFile));
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root, 800, 600);
//		scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
		scene.getStylesheets().add(("view/application.css"));


		getPrimaryStage().setTitle(setTitle);

		getPrimaryStage().setScene(scene);
		getPrimaryStage().show();

	}

	/**
	 * Loads the scene for exporting records.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToExportRecordsScreen(ActionEvent event) throws IOException {
		loadScene(event, exportRecordsScreenFxml, exportRecordsTitle);

	}

	/**
	 * Loads the scene for editing a single record.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToRecordToEditScene(ActionEvent event) throws IOException {
		loadScene(event, recordToEditScreenFxml, recordToEditTitle);

	}

	/**
	 * Loads the scene to delete records.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToDeleteRecordScene(ActionEvent event) throws IOException {
		loadScene(event, deleteRecordScreenFxml, deleteRecordTitle);

	}

	/**
	 * Loads the scene for choosing a record edit.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToEditRecordScene(ActionEvent event) throws IOException {
		loadScene(event, editRecordScreenFxml, editRecordTitle);

	}

	/**
	 * Loads the scene for creating a new record.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToCreateNewRecordScene(ActionEvent event) throws IOException {
		loadScene(event, createNewRecordFxml, createNewRecordScreen);

	}

	/**
	 * Loads the scene for the records 'home screen'.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToRecordScene(ActionEvent event) throws IOException {
		loadScene(event, recordsScreenFxml, recordsScreenTitle);

	}

	/**
	 * Loads the scene for new user creating.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToCreateProfileScene(ActionEvent event) throws IOException {
		loadScene(event, createProfileFxml, createProfileTitle);
	}

	/**
	 * Loads the home scene for the application.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToHomeScene(ActionEvent event) throws IOException {
		loadScene(event, homeScreenFxml, homeScreenTitle);

	}

	/**
	 * Loads the scene for logging in to the application.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToLogInScene(ActionEvent event) throws IOException {
		loadScene(event, loginFxml, loginTitle);

	}

	/**
	 * Loads the scene for user profile.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToProfileScene(ActionEvent event) throws IOException {
		loadScene(event, profileFxml, profileScreenTitle);

	}

	/**
	 * Loads the scene for viewing all user records.
	 * 
	 * @param event triggering event
	 * @throws IOException If an input or output exception occurred
	 */
	public void moveToViewAllRecordsScene(ActionEvent event) throws IOException {
		loadScene(event, viewAllRecordsFxml, viewAllRecordsTitle);
	}

	/**
	 * Allows accces to the private variable for other package classes.
	 * 
	 * @return the primaryStage variable
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
