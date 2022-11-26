package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import model.AboutMyHealth;
import model.UserCollections;

/**
 * HomeScreenController is a Controller class between HomeScreen.fxml and
 * UserCollections.java. Responsible for transitions to other screens on button
 * events and logging the user out and returning to the log in screen.
 * 
 * @author Rebecca Watson
 *
 */
public class HomeScreenController {

	/**
	 * Setting up private variables for the class.
	 */
	private GoToScene goToScene;

	private UserCollections userCollections = UserCollections.getInstance();

	@FXML
	private AnchorPane InnerSplitPaneBottom;

	@FXML
	private AnchorPane header;

	@FXML
	private Button aboutButton;

	@FXML
	private Button accountButton;

	@FXML
	private Button homeButton;

	@FXML
	private SplitPane innerSplitPane;

	@FXML
	private Button logOutButton;

	@FXML
	private Label mainMessage;

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private Button profileButton;

	@FXML
	private Button recordsButton;

	@FXML
	private AnchorPane splitPaneLeft;

	@FXML
	private AnchorPane splitPaneRight;

	@FXML
	private Label versionNumberLine;

	@FXML
	private Label welcomeLine;

	@FXML
	private Label userMessage;

	/**
	 * Sets up event handlers of the class and runs required methods on each action.
	 */
	@FXML
	public void initialize() {

		AboutMyHealth aboutMyHealth = new AboutMyHealth();

		goToScene = new GoToScene();
		userMessage.setText("Choose an Action from the Menu");

		welcomeLine.setText(userCollections.getUserFirstName() + "!");
		welcomeMessage();
		hideLogoutButton();

		recordsButton.setOnAction(event -> {
			try {
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		profileButton.setOnAction(event -> {
			try {
				goToScene.moveToProfileScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		aboutButton.setOnAction(event -> {
			hideMainMessageLine();
			hideWelcomeLine();
			userMessage.setText("About the Application");
			versionNumberLine.setText(aboutMyHealth.getVersionNumber());
			versionNumberLine.setVisible(true);
			hideLogoutButton();

		});

		homeButton.setOnAction(event -> {
			mainMessage.setVisible(true);
			welcomeLine.setVisible(true);
			welcomeLine.setText(userCollections.getUserFirstName() + "!");
			userMessage.setText("Choose an Action from the Menu");
			welcomeMessage();
			hideVersionNumberLine();
			hideLogoutButton();

		});

		accountButton.setOnAction(event -> {
			userMessage.setText("Log Out");
			logoutMessage();
			hideWelcomeLine();
			hideVersionNumberLine();
			logOutButton.setVisible(true);

		});

		logOutButton.setOnAction(event -> {
			userCollections.logOut();
			try {
				goToScene.moveToLogInScene(event);
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

	/**
	 * Sets the welcome label to invisible on the screen.
	 */
	public void hideWelcomeLine() {
		welcomeLine.setText("");

	}

	/**
	 * Sets the logout button to invisible on the screen.
	 */
	public void hideLogoutButton() {
		logOutButton.setVisible(false);

	}

	/**
	 * Sets the version label to invisible on the screen.
	 */
	public void hideVersionNumberLine() {
		versionNumberLine.setVisible(false);
	}

	/**
	 * Sets the main message label text to a welcome line.
	 */
	public void welcomeMessage() {
		mainMessage.setText("Welcome to your MyHealth Application, ");
	}

	/**
	 * Sets the main message label text to a log out line.
	 */
	public void logoutMessage() {
		mainMessage.setText("Would you like to log out?");

	}

	/**
	 * Sets the main message label text to invisible on the screen.
	 */
	public void hideMainMessageLine() {
		mainMessage.setText("");
	}

}