package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import Interfaces.InterfaceAppointmentForContacts;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Contacts;

import java.time.LocalDateTime;

/**
 * Controller class that handles schedule_contacts.fxml file.
 */
public class ScheduleContactsController {

    @FXML
    private TableView<Appointments> contactsScheduleTable;

    @FXML
    private TableColumn<Appointments, Integer> aptIdCol;

    @FXML
    private TableColumn<Appointments, String> aptTitleCol;

    @FXML
    private TableColumn<Appointments, String> aptDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> aptTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> aptStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> aptEndCol;

    @FXML
    private TableColumn<Appointments, Integer> aptCustomerIdCol;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    /**
     * Initializes the ComboBox with all the contacts available from the database.
     */
    @FXML
    public void initialize() {
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setPromptText("Choose Contact");
        contactComboBox.setVisibleRowCount(5);
    }


    /** The lambda expression provided here is in isolation from other classes. In future case, we can just use this expression
     * to get the ObservableList of contactScheduleReport without having to define the method again.*/
    InterfaceAppointmentForContacts appointmentsForContacts = (int selectedContact) -> {
        ObservableList<Appointments> contactScheduleReport = DBAppointments.getAppointmentScheduleForContact(selectedContact);
        return contactScheduleReport;
    };

    /**
     * Handles the change in ComboBox change and loads all the appointments for the chosen contact.
     */
    @FXML
    public void contactComboBoxChangeAction() {
        int selectedContactId = contactComboBox.getValue().getContactId();
        appointmentsForContacts.appointments(selectedContactId);
        contactsScheduleTable.setItems(appointmentsForContacts.appointments(selectedContactId));
        aptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        aptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        aptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aptStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        aptEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        aptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactsScheduleTable.getSortOrder().add(aptStartCol);
    }

}
