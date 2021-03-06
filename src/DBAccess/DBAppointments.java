package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Contacts;
import model.Countries;
import utils.DBConnections;
import utils.TimeZoneConversion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Class that handles all the requests for communication with the database for the Appointments.
 */
public class DBAppointments {

    /**
     * Method to get all appointments from the database and return result in an ObservableList.
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointmentsList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                allAppointmentsList.add(appointment);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAppointmentsList;
    }

    /**
     * Method to get current Month's appointments from the database and return result in an ObservableList.
     * Utilizes TemporalAdjusters to get the first day and last day of the month for the current ZoneDateTime.
     */
    public static ObservableList<Appointments> getCurrentMonthAppointments() {
        ObservableList<Appointments> currentMonthAppointmentsList = FXCollections.observableArrayList();
        try {
            LocalDate localD = LocalDate.now();
            LocalTime zeroHoursAndMinutes = LocalTime.of(0, 00);
            LocalDateTime zeroLocalDT = LocalDateTime.of(localD, zeroHoursAndMinutes);
            LocalDateTime finalHourDT = LocalDateTime.of(localD, LocalTime.of(23,59));
            LocalDateTime firstDayOfMonth = zeroLocalDT.with(TemporalAdjusters.firstDayOfMonth());
            LocalDateTime lastDayOfMonth = finalHourDT.with(TemporalAdjusters.lastDayOfMonth());

            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE Start BETWEEN ? AND ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(firstDayOfMonth));
            ps.setTimestamp(2, Timestamp.valueOf(lastDayOfMonth));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime startLocalDateTime = start.toLocalDateTime();

                Timestamp end = rs.getTimestamp("End");
                LocalDateTime endLocalDateTime = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                currentMonthAppointmentsList.add(appointment);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return currentMonthAppointmentsList;
    }

    /**
     * Method to get current Week's appointments from the database and return result in an ObservableList.
     * Utilizes TemporalAdjusters to get the first day and last day of the week for the current ZoneDateTime.
     */
    public static ObservableList<Appointments> getCurrentWeekAppointments() {
        ObservableList<Appointments> currentWeekAppointmentsList = FXCollections.observableArrayList();
        try {
            LocalDate localD = LocalDate.now();
            LocalTime zeroHoursAndMinutes = LocalTime.of(0, 00);
            LocalDateTime finalHourDT = LocalDateTime.of(localD, LocalTime.of(23,59));
            LocalDateTime zeroLocalDT = LocalDateTime.of(localD, zeroHoursAndMinutes);
            TemporalField fieldLOCAL = WeekFields.of(Locale.getDefault()).dayOfWeek();
            LocalDateTime firstDayOfWeek = zeroLocalDT.with(fieldLOCAL, 1); // Current week's first day
            //LocalDateTime lastDayPlusOne = zeroLocalDT.with(fieldLOCAL, 7).plusDays(1); // Current week's last day
            LocalDateTime lastDayOfWeek = finalHourDT.with(fieldLOCAL, 7); // Current week's last day


            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE Start BETWEEN ? AND ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(firstDayOfWeek));
            ps.setTimestamp(2, Timestamp.valueOf(lastDayOfWeek));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                currentWeekAppointmentsList.add(appointment);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return currentWeekAppointmentsList;
    }

    /**
     * Gets Appointment object passed from other controllers and saves it to the database.
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Appointment object passed from other controllers and updates it on the database based on the passed object's Id.
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(passedAppointment.getAppointmentId() + " is the id");
//        System.out.println(passedAppointment.getDescription() + " is the desc");
    }

    /**
     * Deletes appointment fom the database based on the passed appointment Id.
     */
    public static void deleteSelectedAppointment(int appointmentToDeleteId) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentToDeleteId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns month and type occurrences from appointments using group by sql syntax.
     */
    public static ObservableList<Appointments> getAppointmentsByTypeAndMonth() {
        ObservableList<Appointments> appointmentsByTypeAndMonth = FXCollections.observableArrayList();
        try {
            String sql = "SELECT MONTHNAME(Start) AS Month, Type, COUNT(*) AS Occurrences FROM appointments GROUP BY Type, MONTHNAME(Start) ORDER BY MONTHNAME(Start)";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String month = rs.getString("Month");
                String type = rs.getString("Type");
                int occurrences = rs.getInt("Occurrences");
                Appointments appointment = new Appointments(month, type, occurrences);
                appointmentsByTypeAndMonth.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsByTypeAndMonth;
    }

    /**
     * Method returns ObservableList of appointments of contacts for the schedule report.
     */
    public static ObservableList<Appointments> getAppointmentScheduleForContact(int passedContactId) {
        ObservableList<Appointments> appointmentsForContact = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Customer_ID FROM appointments WHERE Contact_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, passedContactId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                //converting timestamp to LocalDateTime data type
                LocalDateTime startLocalDateTime = start.toLocalDateTime();

                Timestamp end = rs.getTimestamp("End");
                //converting timestamp to LocalDateTime data type
                LocalDateTime endLocalDateTime = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                Appointments appointment = new Appointments(appointmentId, title, description, type, startLocalDateTime, endLocalDateTime, customerId);
                appointmentsForContact.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsForContact;
    }

    /**
     * Method returns ObservableList of appointments for the logged in user whose appointments are within next 15 minutes of logging in.
     */
    public static ObservableList<Appointments> getAppointmentsForLoggedInUser(int passedUserId) {
        ObservableList<Appointments> appointmentsForUser = FXCollections.observableArrayList();
        try {
            LocalDateTime nowLocalDT = LocalDateTime.now();
            LocalDateTime nowPlusFifteenMinutes = LocalDateTime.now().plusMinutes(15);

            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE users.User_ID = ? AND Start BETWEEN ? AND ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, passedUserId);
            ps.setTimestamp(2, Timestamp.valueOf(nowLocalDT));
            ps.setTimestamp(3, Timestamp.valueOf(nowPlusFifteenMinutes));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                appointmentsForUser.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsForUser;
    }

    /**
     * This method has input parameters that gets start and end appointment times. If, in the database, there are any appointments(Start and End) that fall
     * between the new appointment attempting to be created, return an observable list of those overlapping appointments. (Then notify/display
     * to the user saying customer already has appointments at this time).
     */
    public static ObservableList<Appointments> getAppointmentsForCustomers(LocalDateTime passedStartDT, LocalDateTime passedEndDT, int passedCustomerId) {
        ObservableList<Appointments> appointmentsForCustomers = FXCollections.observableArrayList();
        try {
            //String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?))";
            //String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start > ? AND Start < ?) OR (End > ? AND End < ?))";
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start >= ? AND Start < ?) OR (End > ? AND End <= ?))";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, passedCustomerId);
            ps.setTimestamp(2, Timestamp.valueOf(passedStartDT));
            ps.setTimestamp(3, Timestamp.valueOf(passedEndDT));
            ps.setTimestamp(4, Timestamp.valueOf(passedStartDT));
            ps.setTimestamp(5, Timestamp.valueOf(passedEndDT));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime startLocalDateTime = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime endLocalDateTime = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                appointmentsForCustomers.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsForCustomers;
    }

    /**
     * This method has input parameters that gets start and end appointment times. If, in the database, there are any appointments(Start and End) that fall
     * between the new appointment attempting to be created, return an observable list of those overlapping appointments. (Then notify/display
     * to the user saying customer already has appointments at this time).
     */
    public static ObservableList<Appointments> getOverlappingAppointmentsForCustomersMinusSelectedAppointment(LocalDateTime passedStartDT, LocalDateTime passedEndDT, int passedCustomerId, int passedAppointmentId) {
        ObservableList<Appointments> appointmentsForCustomers = FXCollections.observableArrayList();
        try {
            //String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?))";
            //String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start > ? AND Start < ?) OR (End > ? AND End < ?))";
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Contact_Name, User_Name, Customer_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID INNER JOIN users ON appointments.User_ID = users.User_ID INNER JOIN customers on appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID = ? AND ((Start >= ? AND Start < ?) OR (End > ? AND End <= ?)) AND NOT Appointment_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, passedCustomerId);
            ps.setTimestamp(2, Timestamp.valueOf(passedStartDT));
            ps.setTimestamp(3, Timestamp.valueOf(passedEndDT));
            ps.setTimestamp(4, Timestamp.valueOf(passedStartDT));
            ps.setTimestamp(5, Timestamp.valueOf(passedEndDT));
            ps.setInt(6, passedAppointmentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime startLocalDateTime = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime endLocalDateTime = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Appointments appointment = new Appointments(appointmentId, title, description, location, type, startLocalDateTime, endLocalDateTime, customerId, customerName, userId, userName, contactId, contactName);
                appointmentsForCustomers.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsForCustomers;
    }

}
