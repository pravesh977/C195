package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Countries;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointments {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointmentsList = FXCollections.observableArrayList();
        try{
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID";
        PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointments appointment = new Appointments(appointmentId, title, description, location, type, start, end,customerId, customerName, userId, userName, contactId, contactName);
            allAppointmentsList.add(appointment);
            }
        } catch (
    SQLException e) {
        e.printStackTrace();
    }

        return allAppointmentsList;
    }

}
