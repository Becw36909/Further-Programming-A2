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
 * NewProfileController is a Controller class between CreateProfile.fxml and
 * UserCollections.java. Takes all inputs from the view fxml and runs all
 * intermediate method checks to make sure input data is valid before passing
 * validated input to the backend class.
 * 
 * @author Rebecca Watson
 *
 */
public class CreateProfileController {

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
	private Button createUser;

	@FXML
	private TextField firstName;

	@FXML
	private Label firstNameMessage;

	@FXML
	private TextField lastName;

	@FXML
	private Label lastNameMessage;

	@FXML
	private Label message;

	@FXML
	private PasswordField password;

	@FXML
	private Label passwordMessage;

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

		System.out.println("INSIDE PROFILE CONTROLLER");
		createUser.setOnAction(event -> {
			if ((!validateUsernameChoice() || !validatePasswordChoice())
					|| (!validateFirstName() || !validateLastName())) {
				message.setText("");
				System.out.println("inside IF");
				System.out.println(validateUsernameChoice());
				System.out.println(validatePasswordChoice());
				System.out.println(validateFirstName());
				System.out.println(validateLastName());

			} else {
				System.out.println("inside ELSE");
				System.out.println(validateUsernameChoice());
				System.out.println(validatePasswordChoice());
				System.out.println(validateFirstName());
				System.out.println(validateLastName());
				message.setTextFill(Color.GREEN);
				message.setText("New user " + username.getText() + " created!");
				createNewUser();
			}
		});

		password.setOnAction(event -> {
			System.out.println("password text field event");
//			maskText(password.getText());

		});

		close.setOnAction(event -> {
//			message.setText("Close button PRESSED");
			System.out.println("Close button PRESSED");
			try {
				goToScene.moveToLogInScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}
		});
	}

//	private void maskText(String text) {
//		// TODO Auto-generated method stub
//		
//	}

	/**
	 * Collects all valid text field entries to pass to UserCollections class to
	 * create a new User object.
	 */
	public void createNewUser() {
//		userCollections = UserCollections.getInstance();
		userCollections.createNewUser(username.getText().toString(), firstName.getText().toString(),
				lastName.getText().toString(), password.getText().toString());
	}

	/**
	 * Checks if username text field is empty or is already in use according to the
	 * UserCollections check.
	 * 
	 * @return true if username text field is not empty and username not already in
	 *         use; false otherwise.
	 */
	public Boolean validateUsernameChoice() {
		boolean usernameValidated = false;
		if (username.getText().isEmpty()) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Must enter a username");
		} else if (userCollections.searchUsernameExists(username.getText()) != ZERO) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Invalid username");
			message.setTextFill(Color.RED);
			message.setText("Username already in use. Choose another username!");
		} else if (!userCollections.strictInputIsValid(username.getText())) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Username must be between 5-20 characters");
		} else {
			usernameMessage.setText("");
			usernameValidated = true;
		}
		return usernameValidated;
	}

	/**
	 * Checks if password text field is empty and passes a valid format
	 * UserCollections check.
	 * 
	 * @return true if password text field is not empty and password passes
	 *         UserCollections validity check; false otherwise.
	 */
	public Boolean validatePasswordChoice() {
		boolean passwordValidated = false;
		if (password.getText().isEmpty()) {
			passwordMessage.setTextFill(Color.RED);
			passwordMessage.setText("Must enter a password");
		} else if (!userCollections.strictInputIsValid(password.getText())) {
			passwordMessage.setTextFill(Color.RED);
			passwordMessage.setText("Password must be between 5-20 characters");
		} else {
			passwordMessage.setText("");
			passwordValidated = true;
		}
		return passwordValidated;
	}

	/**
	 * Checks if first name text field is empty or not.
	 * 
	 * @return true if first name text field is not empty; false otherwise.
	 */
	public Boolean validateFirstName() {
		boolean firstNameValidated = false;
		if (firstName.getText().isEmpty()) {
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
	public Boolean validateLastName() {
		boolean lastNameValidated = false;
		if (lastName.getText().isEmpty()) {
			lastNameMessage.setTextFill(Color.RED);
			lastNameMessage.setText("Must enter a last name");
		} else {
			lastNameMessage.setText("");
			lastNameValidated = true;
		}
		return lastNameValidated;
	}

}
