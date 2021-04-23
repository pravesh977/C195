package controller;

import DBAccess.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginWelcomeScreenController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label loginZoneLabel;


    @FXML
    public void initialize() {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        loginZoneLabel.setText(String.valueOf(localZoneId));
        ResourceBundle resBundle = ResourceBundle.getBundle("lng", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")) {
            usernameLabel.setText(resBundle.getString("username"));
            passwordLabel.setText(resBundle.getString("password"));
            loginButton.setText(resBundle.getString("login"));
            exitButton.setText(resBundle.getString("exit"));
        }
    }

    /**
     * Handles the login button and switches the window to main page.
     */
    @FXML
    public void loginClicked(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error case sensitive fix

        String userNameString = userNameTextField.getText().trim();
        String passwordString = userPasswordField.getText().trim();

        //Creating a text file to log login activities
        String fileName = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

        Users loggedUser = DBUsers.loginUser(userNameString, passwordString);
        if (loggedUser == null) {
            AlertMessageController.failedLoginError();
            outputFile.println("Unsuccessful login attempted by username: " + userNameString);
            outputFile.println("Activity on: " + Instant.now() + " UTC.");
            outputFile.println("999FAIL");
            userPasswordField.clear();
        } else {
            System.out.println(loggedUser.getUserId() + " is the id" + loggedUser.getUserName() + " is the username");
            Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome");
            stage.setScene(scene);
            stage.show();
            outputFile.println("Successful login by username: " + userNameString);
            outputFile.println("Activity on: " + Instant.now() + " UTC.");
            outputFile.println("111SUCCESS");
        }
        outputFile.println("------------------------------------------------------");
        outputFile.close();

//        System.out.println(userNameString + "without trim");
//        System.out.println(userNameString.trim() + "with trim");

    }

    @FXML
    public void exitApplication() {
        System.exit(0);
    }

}
