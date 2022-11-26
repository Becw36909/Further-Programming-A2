package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.UserCollections;

/**
 * LoginController is a Controller class between Login.fxml and
 * UserCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data and user is valid before
 * passing validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class LoginController {

	/**
	 * Setting up private variables for the class.
	 */
	private final int ZERO = 0;

	private GoToScene goToScene;

	private UserCollections userCollections = UserCollections.getInstance();

	@FXML
	private Button close;
	
    @FXML
    private AnchorPane header;

	@FXML
	private Button createProfile;

	@FXML
	private Label message;

	@FXML
	private PasswordField password;

	@FXML
	private Label passwordMessage;

	@FXML
	private Button signin;

	@FXML
	private TextField username;

	@FXML
	private Label usernameMessage;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {

		goToScene = new GoToScene();

		System.out.println("INSIDE LOGIN CONTROLLER");

		signin.setOnAction(event -> {
			if (!validateUsername() || !validatePassword()) {
				message.setText("");
				System.out.println("inside IF");
				System.out.println(validateUsername());
				System.out.println(validatePassword());
			} else if (userCollections.findUserAndPasswordMatch(username.getText().toString(),
					password.getText().toString()) == ZERO) {
//				System.out.println("inside ELSE IF");
				message.setTextFill(Color.RED);
				message.setText("No match found for username and password");
			} else {
				System.out.println("inside ELSE");
				System.out.println(validateUsername());
				System.out.println(validatePassword());
				message.setTextFill(Color.GREEN);
				message.setText("Welcome " + username.getText());
				try {
					goToScene.moveToHomeScene(event);
				} catch (IOException e) {
					System.out.println("DIDN'T WORK");
					e.printStackTrace();
				}
			}
		});
		createProfile.setOnAction(event -> {
			message.setText("CREATE PROFILE PRESSED");
			System.out.println("CREATE PROFILE PRESSED");
			try {
				goToScene.moveToCreateProfileScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});

	}

	/**
	 * Checks if the username text field is empty and if and entered username is
	 * valid.
	 * 
	 * @return true if username text field is not empty and username exists
	 *         according to userCollections check; false otherwise.
	 */
	public Boolean validateUsername() {
		boolean usernameValidated = false;
		if (username.getText().isEmpty()) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Must enter a username");

		} else if (userCollections.searchUsernameExists(username.getText()) == ZERO) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Username does not exist");
		} else {
			usernameMessage.setText("");
			usernameValidated = true;
		}
		return usernameValidated;
	}

	/**
	 * Checks if the password text field is empty and if and entered password is
	 * valid.
	 * 
	 * @return true if password text field is not empty and password exists
	 *         according to userCollections check; false otherwise.
	 */
	public Boolean validatePassword() {
		boolean passwordValidated = false;
		if (password.getText().isEmpty()) {
			passwordMessage.setTextFill(Color.RED);
			passwordMessage.setText("Must enter a password");
		} else {
			passwordMessage.setText("");
			passwordValidated = true;
		}
		return passwordValidated;
	}

}
