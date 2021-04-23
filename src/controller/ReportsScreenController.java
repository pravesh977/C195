package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointments;

import java.awt.*;
import java.io.IOException;

public class ReportsScreenController {

    @FXML
    public void appointmentsReportButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/month_type_report_screen.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report by Month and Type");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void scheduleForContactsReportClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/schedule_contacts.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Schedule report for Contacts");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loginInformationButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/add_customers_screen.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Customer Form");
        stage.setScene(new Scene(root));
        stage.show();
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
