package controller;

import DBAccess.DBAppointments;
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
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpdateAppointmentsController {

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
    private ComboBox<LocalTime> startComboBox;

    @FXML
    private ComboBox<LocalTime> endComboBox;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private ComboBox<Users> userComboBox;

    @FXML
    private Label appointmentIdLabel;


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

        //for appointment time ComboBox
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(17,0);

        while(start.isBefore(end.plusSeconds(1))) {
            startComboBox.getItems().add(start);
            endComboBox.getItems().add(start);
            start = start.plusMinutes(30);
        }
    }

    @FXML
    public void populateAppointmentForm(Appointments passedAppointment) {
        //System.out.println(passedAppointment.getTitle());
        appointmentTitleTextField.setText(passedAppointment.getTitle());
        appointmentDescriptionTextArea.setText(passedAppointment.getDescription());
        appointmentLocationTextField.setText(passedAppointment.getLocation());
        appointmentTypeTextField.setText(passedAppointment.getType());
        appointmentDatePicker.setValue(passedAppointment.getStartTime().toLocalDate());
        startComboBox.setValue(passedAppointment.getStartTime().toLocalTime());
        endComboBox.setValue(passedAppointment.getEndTime().toLocalTime());
        appointmentIdLabel.setText(String.valueOf(passedAppointment.getAppointmentId()));
        int passedContactId = passedAppointment.getContactId();
        int passedCustomerId = passedAppointment.getCustomerId();
        int passedUserId = passedAppointment.getUserId();

        for(Contacts element : contactComboBox.getItems()) {
            if(passedContactId == element.getContactId()) {
                contactComboBox.getSelectionModel().select(element);
            }
        }

        for(Customers element : customerComboBox.getItems()) {
            if(passedCustomerId == element.getCustomerId()) {
                customerComboBox.getSelectionModel().select(element);
            }
        }

        for(Users element : userComboBox.getItems()) {
            if(passedUserId == element.getUserId()) {
                userComboBox.getSelectionModel().select(element);
            }
        }
    }

    /** Updates the changes to the selected appointment.*/
    @FXML
    public void updateChangesToAppointment(MouseEvent event) throws IOException {
        int id = Integer.parseInt(appointmentIdLabel.getText());
        String title = appointmentTitleTextField.getText();
        String description = appointmentDescriptionTextArea.getText();
        String location = appointmentLocationTextField.getText();
        String type = appointmentTypeTextField.getText();
        LocalTime startTime = startComboBox.getValue();
        LocalTime endTime = endComboBox.getValue();
        LocalDate appointmentDate = appointmentDatePicker.getValue();
        LocalDateTime appointmentStartDateAndTime = LocalDateTime.of(appointmentDate, startTime);
        LocalDateTime appointmentEndDateAndTime = LocalDateTime.of(appointmentDate, endTime);
        int customerId = customerComboBox.getValue().getCustomerId();
        String customerName = customerComboBox.getValue().getCustomerName();
        int userId = userComboBox.getValue().getUserId();
        String userName = userComboBox.getValue().getUserName();
        int contactId = contactComboBox.getValue().getContactId();
        String contactName = contactComboBox.getValue().getContactName();
        Appointments updatedAppointmentObject = new Appointments(id, title, description, location, type, appointmentStartDateAndTime, appointmentEndDateAndTime, customerId, customerName, userId, userName, contactId, contactName);
        DBAppointments.updateSelectedAppointment(updatedAppointmentObject);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Cancels the update and returns users to the main appointment screen.*/
    @FXML
    public void cancelUpdateAppointment(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
