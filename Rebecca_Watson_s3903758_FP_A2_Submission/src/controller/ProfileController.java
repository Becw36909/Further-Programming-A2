package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.UserCollections;

/**
 * ProfileController is a Controller class between Profile.fxml and
 * UserCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data is valid before passing
 * validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class ProfileController {

	/**
	 * Setting up private variables for the class.
	 */
	private UserCollections userCollections = UserCollections.getInstance();

	private GoToScene goToScene;

	@FXML
	private AnchorPane InnerSplitPaneBottom;

	@FXML
	private AnchorPane header;

	@FXML
	private ToggleButton changeThemeButton;

	@FXML
	private Button confirmUpdateButton;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label firstNameMessage;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Button homeButton;

	@FXML
	private SplitPane innerSplitPane;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label lastNameMessage;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private Label message;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private GridPane updateGrid;

	@FXML
	private Button updateProfileButton;

	@FXML
	private Label usernameLabel;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {
		goToScene = new GoToScene();

		hideUpdateProfile();

		firstNameLabel.setText(userCollections.getUserFirstName());
		lastNameLabel.setText(userCollections.getUserLastName());
		usernameLabel.setText(userCollections.getUserName());

		homeButton.setOnAction(event -> {
			try {
				goToScene.moveToHomeScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		updateProfileButton.setOnAction(event -> {
			showUpdateProfile();

		});

		confirmUpdateButton.setOnAction(event -> {
			if (!checkFirstName() || !checkLastName()) {
			} else {
				userCollections.updateUserFirstName(firstNameTextField.getText());
				firstNameLabel.setText(userCollections.getUserFirstName());
				userCollections.updateUserLastName(lastNameTextField.getText());
				lastNameLabel.setText(userCollections.getUserLastName());
				hideUpdateProfile();
				message.setTextFill(Color.GREEN);
				message.setText("Profile successfully updated!");
			}
		});

		changeThemeButton.setOnAction(event -> {

			if (changeThemeButton.isSelected()) {
				header.setStyle("-fx-background-color:lightpink");
				splitPaneLeft.setStyle("-fx-background-color:lightpink");
				InnerSplitPaneBottom.setStyle("-fx-background-color:mistyrose");
			} else {
				header.setStyle("-fx-background-color:powderblue");
				splitPaneLeft.setStyle("-fx-background-color:powderblue");
				InnerSplitPaneBottom.setStyle("-fx-background-color:azure");
			}
		});

	}

	/**
	 * Checks if first name text field is empty or not.
	 * 
	 * @return true if first name text field is not empty; false otherwise.
	 */
	public boolean checkFirstName() {
		boolean firstNameValidated = false;
		if (firstNameTextField.getText().isEmpty()) {
			firstNameMessage.setTextFill(Color.RED);
			firstNameMessage.setText("Must enter a first name");

		} else {
			firstNameMessage.setText("");
			firstNameValidated = true;
		}
		return firstNameValidated;
	}

	/**
	 * Checks if last name text field is empty or not.
	 * 
	 * @return true if last name text field is not empty; false otherwise.
	 */
	public boolean checkLastName() {
		boolean lastNameValidated = false;
		if (lastNameTextField.getText().isEmpty()) {
			lastNameMessage.setTextFill(Color.RED);
			lastNameMessage.setText("Must enter a last name");

		} else {
			lastNameMessage.setText("");
			lastNameValidated = true;
		}
		return lastNameValidated;
	}

	/**
	 * Shows update button and text field grid for update first and last name
	 * inputs. Clears first name and last name text fields after update button
	 * pressed.
	 */
	public void showUpdateProfile() {
		firstNameTextField.setText(userCollections.getUserFirstName());
		lastNameTextField.setText(userCollections.getUserLastName());

		message.setText("");
		updateGrid.setVisible(true);
		confirmUpdateButton.setVisible(true);
	}

	/**
	 * Sets the text field grid for update first and last name inputs to invisible.
	 */
	public void hideUpdateProfile() {
		updateGrid.setVisible(false);
		confirmUpdateButton.setVisible(false);

	}

}
