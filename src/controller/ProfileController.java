package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.UserCollections;

public class ProfileController {

	private UserCollections userCollections = UserCollections.getInstance();

	private GoToScene goToScene;

    @FXML
    private AnchorPane InnerSplitPaneBottom;

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

	@FXML
	public void initialize() {
//		userCollections = new UserCollections();
		goToScene = new GoToScene();

//		UserCollections userCollections = UserCollections.getInstance();
		System.out.println("INSIDE profile CONTROLLER");
//		firstNameLabel.setVisible(false);
//		confirmUpdateButton.setVisible(false);
//		updateGrid.setVisible(false);
		hideUpdateProfile();

		firstNameLabel.setText(userCollections.getUserFirstName());
		lastNameLabel.setText(userCollections.getUserLastName());
		usernameLabel.setText(userCollections.getUserName());

		homeButton.setOnAction(event -> {
			System.out.println("home button PRESSED");
			try {
				goToScene.moveToHomeScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

		updateProfileButton.setOnAction(event -> {
			showUpdateProfile();

		});

		confirmUpdateButton.setOnAction(event -> {

			if (!checkFirstName() || !checkLastName()) {
				System.out.println("inside IF");
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

	}

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
	
	public void showUpdateProfile() {
		message.setText("");
		System.out.println("updateProfileButton PRESSED");
		updateGrid.setVisible(true);
		confirmUpdateButton.setVisible(true);
		firstNameTextField.clear();
		lastNameTextField.clear();

	}
	
	public void hideUpdateProfile() {
		System.out.println("updateProfileButton PRESSED");
		updateGrid.setVisible(false);
		confirmUpdateButton.setVisible(false);

	}

	public String updateProfilePicture() {
		return null;

	}

	public String resetPassword() {
		return null;

	}
}
