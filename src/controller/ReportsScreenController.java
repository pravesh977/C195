package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ReportsScreenController {


    @FXML
    public void openmainpage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("../view/customers_screen.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        //stage.setTitle("New Window");
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    public void navigateToMainScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
