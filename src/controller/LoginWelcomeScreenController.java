package controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Controller class that handles login_welcome_screen.fxml.
 */
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


    /**
     * Initializes the login form with the current zone/location of the user, loads resource bundle based on the user's OS
     * language setting.
     */
    @FXML
    public void initialize() {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        loginZoneLabel.setText(String.valueOf(localZoneId));
        ResourceBundle resBundle = ResourceBundle.getBundle("lng", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            usernameLabel.setText(resBundle.getString("username"));
            passwordLabel.setText(resBundle.getString("password"));
            loginButton.setText(resBundle.getString("login"));
            exitButton.setText(resBundle.getString("exit"));
        }
    }

    /**
     * Handles the login button and switches the window to main page. Also checks for login fails, and records/logs to
     * the login_activity.txt.
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
            //System.out.println(loggedUser.getUserId() + " is the id" + loggedUser.getUserName() + " is the username");
            Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome");
            stage.setScene(scene);
            stage.show();
            outputFile.println("Successful login by username: " + userNameString);
            outputFile.println("Activity on: " + Instant.now() + " UTC.");
            outputFile.println("111SUCCESS");

            ObservableList<Appointments> appointmentsForLoggedInUserSoon = DBAppointments.getAppointmentsForLoggedInUser(loggedUser.getUserId());
            if (appointmentsForLoggedInUserSoon.size() == 0) {
                //System.out.println("no appointments");
                AlertMessageController.userHasNoAppointmentsSoonAlert(loggedUser.getUserName());
            } else {
                //System.out.println("you got appointments");
                Optional<ButtonType> answer = AlertMessageController.userHasAppointmentsSoonAlert(loggedUser.getUserName(), appointmentsForLoggedInUserSoon.size());
                if (answer.isPresent() && answer.get() == ButtonType.OK) {

                    for (Appointments element : appointmentsForLoggedInUserSoon) {
                        AlertMessageController.displaySoonAppointments(element);
                    }

                    /* Showing user appointments in new modal not working, table not loading
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/logged_in_user_appointments.fxml"));
                    loader.load();

                    LoggedInUserAppointmentsController loggedInCont = loader.getController();
                    loggedInCont.initialize(loggedUser.getUserId());
                    loggedInCont.passUserId(loggedUser.getUserId());

                    System.out.println(appointmentsForLoggedInUserSoon.get(0).getDescription() + " DES YO");

                    Parent root1 = FXMLLoader.load(getClass().getResource("../view/logged_in_user_appointments.fxml"));
                    Stage stage1 = new Stage();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    stage1.setTitle("Upcoming Appointments for " + loggedUser.getUserName());
                    stage1.setScene(new Scene(root1));
                    stage1.show();

                */

                }
            }

        }
        outputFile.println("------------------------------------------------------");
        outputFile.close();

//        System.out.println(userNameString + "without trim");
//        System.out.println(userNameString.trim() + "with trim");

    }

    /**
     * Handles the exit button and quits the application.
     */
    @FXML
    public void exitApplication() {
        System.exit(0);
    }

}





