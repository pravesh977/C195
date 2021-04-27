package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointments;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlertMessageController {

    /** Displays an alert saying there is an appointment starting for the user within 15 minutes of login time.*/
    public static Optional<ButtonType> userHasAppointmentsSoonAlert(String name, int numberOfAppointments) {
        Alert appointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        appointmentAlert.setTitle("Upcoming Appointments");
        appointmentAlert.setContentText("Hello " + name + ", you have " + numberOfAppointments + " upcoming appointment/s within the next 15 minutes. Would you like to view them?");
        Optional<ButtonType> result = appointmentAlert.showAndWait();
        return result;
    }

    /** Displays an alert saying there are no appointments starting for the user within 15 minutes of login time.*/
    public static void userHasNoAppointmentsSoonAlert(String name) {
        Alert noAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);
        noAppointmentAlert.setTitle("No Upcoming Appointments");
        noAppointmentAlert.setContentText("Hello " + name + ", you don't have any upcoming appointments within the next 15 minutes.");
        noAppointmentAlert.showAndWait();
    }

    /** Gets appointments that are within 15 minutes and shows an alert with details of them*/
    public static void displaySoonAppointments(Appointments passedAppointment) {
        Alert comingAppointment = new Alert(Alert.AlertType.INFORMATION);
        comingAppointment.setTitle(passedAppointment.getTitle());
        Long timeDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(), passedAppointment.getStartTime());
        comingAppointment.setContentText("Appointment ID : " + passedAppointment.getAppointmentId() + "\nTitle : " + passedAppointment.getTitle() + "\nDescription : " + passedAppointment.getDescription() + "\nStarting in " + timeDifference + " minutes");
        comingAppointment.showAndWait();
    }

    /** Displays an error alert in french or english depending on user's OS language when login fails*/
    public static void failedLoginError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle resBundle = ResourceBundle.getBundle("lng", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")) {
            errorAlert.setTitle(resBundle.getString("loginfailed"));
            errorAlert.setContentText(resBundle.getString("checkcredentials"));
        } else {
            errorAlert.setTitle("Login Failed");
            errorAlert.setContentText("Please make sure the Username and Password are correct");
        }
        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> customerHasAppointmentsError() {
        Alert deleteCustomersAppointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteCustomersAppointmentAlert.setTitle("Customer has appointments");
        deleteCustomersAppointmentAlert.setContentText("This customer has appointments and cannot be deleted. Would you like to delete all their appointments now?");
        Optional<ButtonType> result = deleteCustomersAppointmentAlert.showAndWait();
        return result;
    }

    public static Optional<ButtonType> deleteWarning() {
        Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteAlert.setTitle("Confirm Delete");
        confirmDeleteAlert.setContentText("Are you sure you want to delete this?");
        Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
        return result;
    }

    /** Non selection error when trying to update or delete appointments or customers*/
    public static void deleteSuccessfulWithoutAppointment(int deletedCustomerId) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Delete Successful");
        errorAlert.setContentText("The customer with Id " + deletedCustomerId +" has been deleted");
        errorAlert.showAndWait();
    }

    /** Non selection error when trying to update or delete appointments or customers*/
    public static void appointmentDeleteConfirmation(int deletedAppointmentId) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Delete Successful");
        errorAlert.setContentText("The appointment with Id " + deletedAppointmentId +" has been deleted");
        errorAlert.showAndWait();
    }

    /** Non selection error when trying to update or delete appointments or customers*/
    public static void deleteAppointmentSuccessfulNowDeleteCustomer(int deletedCustomerId) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Delete Successful");
        errorAlert.setContentText("All appointments for customer with id " + deletedCustomerId +" have been deleted. Please delete the customer now");
        errorAlert.showAndWait();
    }

    /** Non selection error when trying to update or delete appointments or customers*/
    public static void nonSelectionErrorUpdate() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select an item to update");
        errorAlert.showAndWait();
    }

    /** Non selection error when trying to update or delete appointments or customers*/
    public static void nonSelectionErrorDelete() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select an item to delete");
        errorAlert.showAndWait();
    }

    public static void businessClosedError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Business Closed");
        errorAlert.setContentText("Business is not open at this scheduled time. Please schedule between 08:00 and 22:00 EST.");
        errorAlert.showAndWait();
    }

    public static void endTimeBeforeStartTimeError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("End Time before Start Time");
        errorAlert.setContentText("End Time of Appointment cannot be set before the Start Time");
        errorAlert.showAndWait();
    }

    /** Error caused by overlapping appointment times for customer*/
    public static void appointmentForCustomersOverlap(Appointments passedAppointment) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Customer Appointment Overlap");
        errorAlert.setContentText("Customer " + passedAppointment.getCustomerName() +  " already has another appointment on that timeslot." + " \n Title : " + passedAppointment.getTitle() + "\n Description : " + passedAppointment.getDescription() + " \n Start Time : " + passedAppointment.getStartTime() + " \n End Time : " + passedAppointment.getEndTime());
        errorAlert.showAndWait();
    }

    public static void errorAddingAppointment() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error adding appointment");
        errorAlert.setContentText("Please make sure the fields are not empty and properly filled");
        errorAlert.showAndWait();
    }

    public static void nullValueEntry() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Null Entered");
        errorAlert.setContentText("Field cannot be empty");
        errorAlert.showAndWait();
    }

}
