package controller;

import DBAccess.DBAppointments;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;

public class MonthTypeReportScreenController {

    @FXML
    private TableView<Appointments> monthTypeReportTable;

    @FXML
    private TableColumn<Appointments, String> monthColReport;

    @FXML
    private TableColumn<Appointments, String> typeColReport;

    @FXML
    private TableColumn<Appointments, Integer> occurrencesColReport;

    @FXML
    public void initialize() {
        monthTypeReportTable.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        monthColReport.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeColReport.setCellValueFactory(new PropertyValueFactory<>("type"));
        occurrencesColReport.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
    }
}
