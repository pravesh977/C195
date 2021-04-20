package controller;

import javafx.scene.control.Alert;

public class AlertMessageController {

    public static void beforeBusinessHoursError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Before Business Hours");
        errorAlert.setContentText("Cannot set up appointment before 8:00 EST");
        errorAlert.showAndWait();
    }

    public static void afterBusinessHoursError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("After Business Hours");
        errorAlert.setContentText("Cannot set up appointment after 22:00 EST");
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

    public static void failedLoginError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Login Failed");
        errorAlert.setContentText("Please make sure the Username and Password are correct");
        errorAlert.showAndWait();
    }

}
