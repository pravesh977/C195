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
    private TableColumn<Appointments, Timestamp> appStartCol;

    @FXML
    private TableColumn<Appointments, Timestamp> appEndCol;

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

    public void addNewAppointment(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/add_appointments_screen.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
