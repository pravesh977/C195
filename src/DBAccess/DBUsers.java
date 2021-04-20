package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import model.Contacts;
import model.Customers;
import model.Users;
import utils.DBConnections;

import java.awt.*;
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

    public static Users loginUser(String passedUserName, String passedUserPassword) {

        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setString(1, passedUserName);
            ps.setString(2, passedUserPassword);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users matchedUser = new Users(userId, userName, userPassword);
                return matchedUser;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
