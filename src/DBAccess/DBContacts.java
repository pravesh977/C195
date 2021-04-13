package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    /** Gets a list of all contacts from the database and creates an observables list of contact objects*/
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts contact = new Contacts(contactId, contactName, contactEmail);
                allContacts.add(contact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return allContacts;
    }
}
