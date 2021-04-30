package Interfaces;


import javafx.collections.ObservableList;
import model.Appointments;

public interface InterfaceAppointmentForContacts {

    /**
     * Declaration that returns an ObservableList of appointments and takes in an integer as parameter
     */
    ObservableList<Appointments> appointments(int contactID);
}
