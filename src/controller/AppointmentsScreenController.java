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
    private TableColumn<Appointments, String> appUserCol;

    @FXML
    public void initialize() {
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
        appUserCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    @FXML
    public void navigateToMainScreen(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
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

    /** Opens a new form to let users create a new appointment*/
    public void openAddAppointmentForm(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/add_appointments_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }

    /** opens a form to let users update selected appointment by passing selected appointment to updateAppointmentController*/
    public void openUpdateAppointmentForm(MouseEvent event) throws IOException {
        //FIX Me add nullpointer exception catcher for updating without selecting
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/update_appointments_screen.fxml"));
        loader.load();

        UpdateAppointmentsController updateAppointmentCont = loader.getController();

        Appointments selectedAppointment = fullAppointmentTable.getSelectionModel().getSelectedItem();
        updateAppointmentCont.populateAppointmentForm(selectedAppointment);

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Deletes the selected appointment from the appointment table by passing it to DBAppointments*/
    public void deleteAppointment() {
        Appointments selectedAppointment = fullAppointmentTable.getSelectionModel().getSelectedItem();
        DBAppointments.deleteSelectedAppointment(selectedAppointment.getAppointmentId());
        initialize();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
