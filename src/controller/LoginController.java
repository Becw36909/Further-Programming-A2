package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.UserCollections;
//import model.UserCollections;
//import view.Main;

public class LoginController {

	private final int ZERO = 0;

	private GoToScene goToScene;

//	private UserCollections userCollections;
	private UserCollections userCollections = UserCollections.getInstance();

	@FXML
	private Button close;

	@FXML
	private Label message;

	@FXML
	private TextField password;

	@FXML
	private Label passwordMessage;

	@FXML
	private Button signin;

	@FXML
	private TextField username;

	@FXML
	private Label usernameMessage;

	@FXML
	private Button createProfile;

//    @FXML
//    void moveToCreateProfileScene(ActionEvent event) {
//
//    }

	@FXML
	public void initialize() {
//		UserCollections userCollections = UserCollections.getInstance();
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

	public Boolean validateUsername() {
		boolean usernameValidated = false;
		if (username.getText().isEmpty()) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Must enter a username");

		} else if (userCollections.searchUsernameExists(username.getText()) == ZERO) {
			usernameMessage.setTextFill(Color.RED);
			usernameMessage.setText("Username does not exist");
		}
		else {
			usernameMessage.setText("");
			usernameValidated = true;
		}
		return usernameValidated;
	}

	public Boolean validatePassword() {
		boolean passwordValidated = false;
		if (password.getText().isEmpty()) {
			passwordMessage.setTextFill(Color.RED);
			passwordMessage.setText("Must enter a password");
		}
		else {
			passwordMessage.setText("");
			passwordValidated = true;
		}
		return passwordValidated;
	}



}
