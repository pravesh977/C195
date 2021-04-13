package controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;

public class AddAppointmentsController {

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private ComboBox<Users> userComboBox;


    /** Initializing the ComboBoxes in the appointment add form.*/
    @FXML
    public void initialize() {
        //for contacts ComboBox
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setPromptText("Choose Contact");
        contactComboBox.setVisibleRowCount(5);

        //for customers ComboBox
        customerComboBox.setItems(DBCustomers.getAllCustomers());
        customerComboBox.setPromptText("Choose Customer");
        customerComboBox.setVisibleRowCount(5);

        //for users ComboBox
        userComboBox.setItems(DBUsers.getAllUsers());
        userComboBox.setPromptText("Choose User");
        userComboBox.setVisibleRowCount(5);
    }

    /** Handles the cancel button and returns to the appointment screen.*/
    @FXML
    public void cancelAddAppointment(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }
}
