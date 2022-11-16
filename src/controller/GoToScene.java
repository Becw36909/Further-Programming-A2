package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// I wanted to make an interface, but was having problems making one work so settled for a class that other controller classes could 
// extend from..... might refactor later

public class GoToScene {

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

	

	public void loadScene(ActionEvent event, String fxmlFile, String setTitle) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxmlFile));
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root, 800, 600);

		primaryStage.setTitle(setTitle);

		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void moveToCreateNewRecordScene(ActionEvent event) throws IOException {
		loadScene(event, createNewRecordFxml, createNewRecordScreen );

	}

	public void moveToRecordScene(ActionEvent event) throws IOException {
		loadScene(event, recordsScreenFxml, recordsScreenTitle);

	}
	public void moveToCreateProfileScene(ActionEvent event) throws IOException {
		loadScene(event, createProfileFxml, createProfileTitle);
	}

	public void moveToHomeScene(ActionEvent event) throws IOException {
		loadScene(event, homeScreenFxml, homeScreenTitle);

	}

	public void moveToLogInScene(ActionEvent event) throws IOException {
		loadScene(event, loginFxml, loginTitle);

	}
	
	public void moveToProfileScene(ActionEvent event) throws IOException {
		loadScene(event, profileFxml, profileScreenTitle);

	}
	
	

}
