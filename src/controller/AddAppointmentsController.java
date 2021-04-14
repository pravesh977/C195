package controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.sql.Timestamp;

public class AddAppointmentsController {

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextArea appointmentDescriptionTextArea;

    @FXML
    private TextField appointmentLocationTextField;

    @FXML
    private TextField appointmentTypeTextField;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<Timestamp> startComboBox;

    @FXML
    private ComboBox<Timestamp> endComboBox;

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

    public void saveNewAppointment() {
        int id = 0;
        String title = appointmentTitleTextField.getText();
        String description = appointmentDescriptionTextArea.getText();
//        String location = appointmentLocationTextField.getText();
//        String type = appointmentTypeTextField.getText();
//        Timestamp startTime = startComboBox.getValue();
//        Timestamp endTime = startComboBox.getValue();
//        int customerId = customerComboBox.getValue().getCustomerId();
//        int userId = userComboBox.getValue().getUserId();
//        int contactId = contactComboBox.getValue().getContactId();

        System.out.println("title is " + title);
        System.out.println("description is " + description);
        //System.out.println("Start time is " + startTime);

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
