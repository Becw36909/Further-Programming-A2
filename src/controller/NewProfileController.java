package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.UserCollections;

public class NewProfileController {

	private final int ZERO = 0;

//	private UserInputValidator userInputValidator;

	private GoToScene goToScene;

	private UserCollections userCollections =  UserCollections.getInstance();

	@FXML
	private Button close;

	@FXML
	private Label message;

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
	private TextField password;

	@FXML
	private Label passwordMessage;

	@FXML
	private TextField username;

	@FXML
	private Label usernameMessage;

	public NewProfileController() {
//    	userCollections= new UserCollections();
	}

	@FXML
	public void initialize() {
//		userCollections = new UserCollections();
//		UserCollections userCollections = UserCollections.getInstance();

//		userInputValidator = new UserInputValidator(); 

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

	public void createNewUser() {
//		userCollections = UserCollections.getInstance();
		userCollections.createNewUser(username.getText().toString(), firstName.getText().toString(),
				lastName.getText().toString(), password.getText().toString());
	}

	public Boolean validateUsernameChoice() {
//		UserCollections userCollections = UserCollections.getInstance();
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

	public Boolean validatePasswordChoice() {
//		UserCollections userCollections = UserCollections.getInstance();
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

}
