package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomersScreenController {

    /** Handles the Main Menu button and switches the window to Main Screen. */
    @FXML
    public void navigateToMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
        System.out.println("hello");

    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }

//    public void navigateToMainScreen(javafx.event.ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
//        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setTitle("Customers");
//        stage.setScene(scene);
//        stage.show();
//        System.out.println("hello");
//    }
}
