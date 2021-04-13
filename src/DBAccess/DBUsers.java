package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Users;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users user = new Users(userId, userName, userPassword);
                allUsers.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }
}
