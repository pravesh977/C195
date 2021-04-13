package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {
    @FXML
    public void navigateToMainScreen(ActionEvent event) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();
        } catch(NullPointerException e) {

        }
    }

    /** Handles the Customers button and switches the window to Customers Screen. */
    @FXML
    public void navigateToCustomers(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error
        Parent root = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /** Handles the Appointments button and switches to Appointments screen */
    @FXML
    public void navigateToAppointments(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error
        Parent root = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }


    /** Handles the Reports button and switches to Reports screen */
    @FXML
    public void navigateToReports(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error
        Parent root = FXMLLoader.load(getClass().getResource("../view/reports_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }

}
