package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.AboutMyHealth;
import model.RecordCollections;
import model.UserCollections;

public class HomeScreenController {

	private AboutMyHealth aboutMyHealth;

	private GoToScene goToScene;

	private UserCollections userCollections = UserCollections.getInstance();
	
	private RecordCollections recordCollections = RecordCollections.getInstance();

	  @FXML
	    private AnchorPane InnerSplitPaneBottom;

	    @FXML
	    private Button aboutButton;

	    @FXML
	    private Button accountButton;

	    @FXML
	    private Button exportButton;

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
	public void initialize() {
//		UserCollections userCollections = UserCollections.getInstance();

		AboutMyHealth aboutMyHealth = new AboutMyHealth();

		goToScene = new GoToScene();

		System.out.println("INSIDE home screen CONTROLLER");
		welcomeLine.setText(userCollections.getUserFirstName() + "!");
		welcomeMessage();
		hideLogoutButton();

		recordsButton.setOnAction(event -> {
			System.out.println("Records button PRESSED");
			try {
//				recordCollections.getRecords();
				goToScene.moveToRecordScene(event);
			} catch (IOException e) {
				System.out.println("something went wrong");
				e.printStackTrace();
			}
		});

		profileButton.setOnAction(event -> {
			System.out.println("Records button PRESSED");
			try {
				goToScene.moveToProfileScene(event);
			} catch (IOException e) {
				System.out.println("something went wrong");
				e.printStackTrace();
			}
		});

		aboutButton.setOnAction(event -> {
			System.out.println("about button PRESSED");
			hideMainMessageLine();
			hideWelcomeLine();
			versionNumberLine.setText(aboutMyHealth.getVersionNumber());
			versionNumberLine.setVisible(true);
			hideLogoutButton();

		});

		homeButton.setOnAction(event -> {
			System.out.println("home button PRESSED");
			mainMessage.setVisible(true);
			welcomeLine.setVisible(true);
			welcomeLine.setText(userCollections.getUserFirstName() + "!");
			welcomeMessage();
			hideVersionNumberLine();
			hideLogoutButton();

		});

		accountButton.setOnAction(event -> {
			System.out.println("account button PRESSED");
			logoutMessage();
			hideWelcomeLine();
			hideVersionNumberLine();
			logOutButton.setVisible(true);

		});

		logOutButton.setOnAction(event -> {
			System.out.println("logout button PRESSED");
			userCollections.logOut();
			try {
				goToScene.moveToLogInScene(event);
			} catch (IOException e) {
				System.out.println("DIDN'T WORK");
				e.printStackTrace();
			}

		});

	}

	public void hideWelcomeLine() {
		welcomeLine.setText("");

	}

	public void hideLogoutButton() {
		logOutButton.setVisible(false);

	}

	public void hideVersionNumberLine() {
		versionNumberLine.setVisible(false);
	}
	
	public void welcomeMessage() {
		mainMessage.setText("Welcome to your MyHealth Application, ");
	}
	
	public void logoutMessage() {
		mainMessage.setText("Would you like to log out?");

	}
	
	public void hideMainMessageLine() {
		mainMessage.setText("");
	}

}