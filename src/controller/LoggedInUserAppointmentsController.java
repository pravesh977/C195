package controller;

import DBAccess.DBAppointments;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Appointments;

import java.time.LocalDateTime;

public class LoggedInUserAppointmentsController {

    @FXML
    private TableView<Appointments> loggedInUserAppointmentsTable;

    @FXML
    private TableColumn<Appointments, Integer> loggedInUserIdCol;

    @FXML
    private TableColumn<Appointments, String> loggedInUserTitleCol;

    @FXML
    private TableColumn<Appointments, String> loggedInUserDescriptionCol;

//    @FXML
//    private TableColumn<Appointments, Integer> loggedInUserStartingInCol;

    @FXML
    private TableColumn<Appointments, String> loggedInUserCustomerCol;

    @FXML
    private TableColumn<Appointments, String> loggedInUserContactCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> loggedInUserStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> loggedInUserEndCol;

    @FXML
    private Label userIdLabel;

    @FXML
    public void initialize(int id) {
        userIdLabel.setText(String.valueOf(id));
        System.out.println(userIdLabel.getText() + " this was not set");
    }

    @FXML
    public void passUserId(int passedUserId) {
        userIdLabel.setText(String.valueOf(passedUserId));
        System.out.println(passedUserId  + " valuuuuu");
    }



//    @FXML
//    private void showUserAppointmentsOnClick(MouseEvent event, int userId) {
//        ObservableList<Appointments> allstuff = DBAppointments.getAppointmentsForLoggedInUser(userId);
//        loggedInUserAppointmentsTable.setItems(allstuff);
//        loggedInUserIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
//        loggedInUserTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//        loggedInUserDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//
//        loggedInUserCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        loggedInUserContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
//        loggedInUserStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        loggedInUserEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//    }

//    public void showUserAppointmentsOnClick(int passedId) {
//        ObservableList<Appointments> allstuff = DBAppointments.getAppointmentsForLoggedInUser(passedId);
//        loggedInUserAppointmentsTable.setItems(allstuff);
//        loggedInUserIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
//        loggedInUserTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//        loggedInUserDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//
//        loggedInUserCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        loggedInUserContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
//        loggedInUserStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        loggedInUserEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//    }


//    @FXML
//    public void initialize() {
//        ObservableList<Appointments> allstuff = DBAppointments.getAllAppointments();
//        loggedInUserAppointmentsTable.setItems(allstuff);
//        loggedInUserIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
//        loggedInUserTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//        loggedInUserDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//
//        loggedInUserCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        loggedInUserContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
//        loggedInUserStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        loggedInUserEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//    }





}
