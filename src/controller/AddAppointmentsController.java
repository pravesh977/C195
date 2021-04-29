package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
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
import model.Contacts;
import model.Customers;
import model.Users;
import utils.TimeZoneConversion;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;

public class AddAppointmentsController {

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextArea appointmentDescriptionTextArea;

    @FXML
    private TextField appointmentLocationTextField;

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
    private ToggleGroup appointTypeToggleGroup;

    @FXML
    private RadioButton phoneMeetingRadioButton;

    @FXML
    private RadioButton videoConferenceRadioButton;

    @FXML
    private RadioButton inPersonRadioButton;


    /**
     * Initializing the ComboBoxes in the appointment add form.
     */
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

        //for start appointment time
        LocalTime start = LocalTime.of(00, 0);
        LocalTime end = LocalTime.of(23, 30);

        while (start.isBefore(end.plusSeconds(1))) {
            startComboBox.getItems().add(start);
            endComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }

    public void saveNewAppointment(MouseEvent event) throws IOException {
        try {
            int id = 0;
            String title = appointmentTitleTextField.getText();
            String description = appointmentDescriptionTextArea.getText();
            String location = appointmentLocationTextField.getText();
            String type = "";
            if (phoneMeetingRadioButton.isSelected()) {
                type = "Phone Meeting";
            }
            if (videoConferenceRadioButton.isSelected()) {
                type = "Video Conference";
            }
            if (inPersonRadioButton.isSelected()) {
                type = "In-Person Meeting";
            }
            LocalTime startTime = startComboBox.getValue();
            LocalTime endTime = endComboBox.getValue();
            LocalDate appointmentDate = appointmentDatePicker.getValue();
            LocalDateTime appointmentStartDateAndTime = LocalDateTime.of(appointmentDate, startTime);
            LocalDateTime appointmentEndDateAndTime = LocalDateTime.of(appointmentDate, endTime);

            //not needed auto converted?
//        LocalDateTime utcStartDateAndTime = TimeZoneConversion.localToUtcConversion(appointmentStartDateAndTime);
//        LocalDateTime utcEndDateAndTime = TimeZoneConversion.localToUtcConversion(appointmentEndDateAndTime);


            int customerId = customerComboBox.getValue().getCustomerId();
            String customerName = customerComboBox.getValue().getCustomerName();
            int userId = userComboBox.getValue().getUserId();
            String userName = userComboBox.getValue().getUserName();
            int contactId = contactComboBox.getValue().getContactId();
            String contactName = contactComboBox.getValue().getContactName();

            //Creating an observable list to see if it returns null or values
            ObservableList<Appointments> customersWithOverlappingAppointments = DBAppointments.getAppointmentsForCustomers(appointmentStartDateAndTime, appointmentEndDateAndTime, customerId);

            int conversionResult = TimeZoneConversion.estConversion(appointmentStartDateAndTime, appointmentEndDateAndTime);

            if (conversionResult == 1) {
                AlertMessageController.businessClosedError();
            } else if (appointmentEndDateAndTime.isBefore(appointmentStartDateAndTime)) {
                AlertMessageController.endTimeBeforeStartTimeError();
            } else if ((title.trim().isEmpty()) || (description.trim().isEmpty()) || (location.trim().isEmpty()) || (type.trim().isEmpty())) {
                AlertMessageController.nullValueEntry();
            } else if (customersWithOverlappingAppointments.size() != 0) {


                customersWithOverlappingAppointments.forEach((element) -> {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Customer Appointment Overlap");
                    errorAlert.setContentText("Customer " + element.getCustomerName() +  " already has another appointment on that timeslot." + " \n Title : " + element.getTitle() + "\n Description : " + element.getDescription() + " \n Start Time : " + element.getStartTime() + " \n End Time : " + element.getEndTime());
                    errorAlert.showAndWait();

                });

            } else {
                Appointments newAppointment = new Appointments(id, title, description, location, type, appointmentStartDateAndTime, appointmentEndDateAndTime, customerId, customerName, userId, userName, contactId, contactName);
                DBAppointments.addNewAppointment(newAppointment);

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NullPointerException e) {
            AlertMessageController.errorAddingAppointment();
        }
    }


//        System.out.println("title is " + title);
//        System.out.println("description is " + description);
//        System.out.println(startComboBox.getValue());
//        System.out.println(endComboBox.getValue());
//        System.out.println("Start time is " + startTime);
//        System.out.println(appointmentDate + " is the date");
//        System.out.println(appointmentStartDateAndTime + " is the start localdatetime");
//        System.out.println(appointmentEndDateAndTime + " is the end localdatetime");
//        System.out.println(startTimestamp + " is the start timestamp");
//        System.out.println(endTimestamp + " is the end timestamp");


    /**
     * Handles the cancel button and returns to the appointment screen.
     */
    @FXML
    public void cancelAddAppointment(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/appointments_screen.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }


}

// replaced by the lam exp
//                for(Appointments element : customersWithOverlappingAppointments) {
//                    AlertMessageController.appointmentForCustomersOverlap(element);
//                }


