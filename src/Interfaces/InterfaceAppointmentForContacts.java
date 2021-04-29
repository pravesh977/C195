package Interfaces;


import javafx.collections.ObservableList;
import model.Appointments;

public interface InterfaceAppointmentForContacts {
    ObservableList<Appointments> appointments(int contactID);
}
