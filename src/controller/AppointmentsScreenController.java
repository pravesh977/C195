package controller;

import DBAccess.DBAppointments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller class that handles appointments_screen.fxml file.
 */
public class AppointmentsScreenController {

    @FXML
    private TableView<Appointments> fullAppointmentTable;

    @FXML
    private TableColumn<Appointments, Integer> appIdCol;

    @FXML
    private TableColumn<Appointments, String> appTitleCol;

    @FXML
    private TableColumn<Appointments, String> appDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> appLocationCol;

    @FXML
    private TableColumn<Appointments, String> appContactCol;

    @FXML
    private TableColumn<Appointments, String> appTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appEndCol;

    @FXML
    private TableColumn<Appointments, String> appCustomerCol;

    @FXML
    private TableColumn<Appointments, Integer> appCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> appUserCol;

    @FXML
    private TableView<Appointments> monthlyAppointmentTable;

    @FXML
    private TableColumn<Appointments, Integer> monthlyAppIdCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppTitleCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppLocationCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppContactCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> monthlyAppStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> monthlyAppEndCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppCustomerCol;

    @FXML
    private TableColumn<Appointments, Integer> monthlyAppCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> monthlyAppUserCol;

    @FXML
    private TableView<Appointments> weeklyAppointmentTable;

    @FXML
    private TableColumn<Appointments, Integer> weeklyAppIdCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppTitleCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppLocationCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppContactCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> weeklyAppStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> weeklyAppEndCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppCustomerCol;

    @FXML
    private TableColumn<Appointments, Integer> weeklyAppCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> weeklyAppUserCol;

    /**
     * Initializing all three tables with all, monthly, and weekly appointment data.
     */
    @FXML
    public void initialize() {

        //for full appointment table
        fullAppointmentTable.setItems(DBAppointments.getAllAppointments());
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        appCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        fullAppointmentTable.getSortOrder().add(appIdCol);

        //for monthly appointment table
        monthlyAppointmentTable.setItems(DBAppointments.getCurrentMonthAppointments());
        monthlyAppIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyAppTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyAppDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyAppLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyAppContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        monthlyAppStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        monthlyAppEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        monthlyAppCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        monthlyAppCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyAppUserCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        monthlyAppointmentTable.getSortOrder().add(monthlyAppStartCol);

        //for weekly appointment table
        weeklyAppointmentTable.setItems(DBAppointments.getCurrentWeekAppointments());
        weeklyAppIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyAppTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyAppDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyAppLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyAppContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        weeklyAppStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        weeklyAppEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        weeklyAppCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        weeklyAppCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyAppUserCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        weeklyAppointmentTable.getSortOrder().add(weeklyAppStartCol);
    }


    /**
     * Handles the Home button and sends user to the main screen.
     */
    @FXML
    public void navigateToMainScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void tab1selected() {
//        if (tab1.isSelected()) {
//            tab1label.setText(String.valueOf(tab1value));
//            System.out.println(tab1value);
//            tab1value++;
//        }

    }

    @FXML
    public void tab2selected() {

    }

    @FXML
    public void tab3selected() {

    }

    /**
     * Opens a new form to let users create a new appointment.
     */
    public void openAddAppointmentForm(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/add_appointments_screen.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * opens a form to let users update selected appointment by passing selected appointment to updateAppointmentController.
     */
    public void openUpdateAppointmentForm(MouseEvent event) throws IOException {
        if (fullAppointmentTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/update_appointments_screen.fxml"));
            loader.load();

            UpdateAppointmentsController updateAppointmentCont = loader.getController();

            Appointments selectedAppointment = fullAppointmentTable.getSelectionModel().getSelectedItem();
            updateAppointmentCont.populateAppointmentForm(selectedAppointment);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            AlertMessageController.nonSelectionErrorUpdate();
        }
    }

    /**
     * Deletes the selected appointment from the appointment table by passing it to DBAppointments.
     */
    public void deleteAppointment() {
        if (fullAppointmentTable.getSelectionModel().getSelectedItem() != null) {
            Optional<ButtonType> answer = AlertMessageController.deleteWarning();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                Appointments selectedAppointment = fullAppointmentTable.getSelectionModel().getSelectedItem();
                DBAppointments.deleteSelectedAppointment(selectedAppointment.getAppointmentId());
                initialize();
                AlertMessageController.appointmentDeleteConfirmation(selectedAppointment.getAppointmentId());
            }
        } else {
            AlertMessageController.nonSelectionErrorDelete();
        }
    }

    /**
     * Handles the exit button and closes the application.
     */
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
