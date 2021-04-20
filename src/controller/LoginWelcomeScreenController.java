package controller;

import DBAccess.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

public class LoginWelcomeScreenController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField userPasswordField;


    /**
     * Handles the login button and switches the window to main page.
     */
    @FXML
    public void loginClicked(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error

        String userNameString = userNameTextField.getText();
        String passwordString = userPasswordField.getText();

        //Creating a text file to log login activities

        String fileName = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

        Users loggedUser = DBUsers.loginUser(userNameString, passwordString);
        if (loggedUser == null) {
            outputFile.println("Unsuccessful login attempted by username: " + userNameString);
            outputFile.println("Activity on: " + Instant.now() + " UTC.");
            outputFile.println("999FAIL");
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


    }

    @FXML
    public void exitApplication() {
        System.exit(0);
    }

}
