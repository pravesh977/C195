package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Countries;
import utils.DBConnections;
import utils.TimeZoneConversion;

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
            //converting timestamp to LocalDateTime data type
            LocalDateTime startLocalDateTime = start.toLocalDateTime();

//            //delete
//            TimeZoneConversion.utcToLocalConversion(startLocalDateTime);

            Timestamp end = rs.getTimestamp("End");
            //converting timestamp to LocalDateTime data type
            LocalDateTime endLocalDateTime = end.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime,customerId, customerName, userId, userName, contactId, contactName);
            allAppointmentsList.add(appointment);
            }
        } catch (
    SQLException e) {
        e.printStackTrace();
    }

        return allAppointmentsList;
    }

    public static void addNewAppointment(Appointments newAppointment) {
        try {
            String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setString(1, newAppointment.getTitle());
            ps.setString(2, newAppointment.getDescription());
            ps.setString(3, newAppointment.getLocation());
            ps.setString(4, newAppointment.getType());
            //converting LocalDateTime datatype to Timestamp datatype for sql entry
            Timestamp startTimestamp = Timestamp.valueOf(newAppointment.getStartTime());
            ps.setTimestamp(5, startTimestamp);
            Timestamp endTimestamp = Timestamp.valueOf(newAppointment.getEndTime());
            ps.setTimestamp(6, endTimestamp);
            ps.setInt(7, newAppointment.getCustomerId());
            ps.setInt(8, newAppointment.getUserId());
            ps.setInt(9, newAppointment.getContactId());

            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSelectedAppointment(Appointments passedAppointment) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setString(1, passedAppointment.getTitle());
            ps.setString(2, passedAppointment.getDescription());
            ps.setString(3, passedAppointment.getLocation());
            ps.setString(4, passedAppointment.getType());
            //converting LocalDateTime datatype to Timestamp datatype for sql entry
            Timestamp startTimestamp = Timestamp.valueOf(passedAppointment.getStartTime());
            ps.setTimestamp(5, startTimestamp);
            Timestamp endTimestamp = Timestamp.valueOf(passedAppointment.getEndTime());
            ps.setTimestamp(6, endTimestamp);
            ps.setInt(7, passedAppointment.getCustomerId());
            ps.setInt(8, passedAppointment.getUserId());
            ps.setInt(9, passedAppointment.getContactId());
            ps.setInt(10, passedAppointment.getAppointmentId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(passedAppointment.getAppointmentId() + " is the id");
//        System.out.println(passedAppointment.getDescription() + " is the desc");
    }

    public static void deleteSelectedAppointment(int appointmentToDeleteId) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentToDeleteId);

            ps.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getStartUTC() {
        try {
            String sql = "SELECT Start from appointments";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp startUTC = rs.getTimestamp("Start");
                System.out.println(startUTC + " UTC value");
                LocalDateTime startLocalDateTime = startUTC.toLocalDateTime();
                System.out.println(startLocalDateTime + " Local Value");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}