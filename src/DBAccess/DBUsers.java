package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that interacts with the database with regards to query with Users.
 */
public class DBUsers {

    /**
     * Returns all users from the database.
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users user = new Users(userId, userName, userPassword);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }


    /**
     * Returns the user object for the user logged in.
     */
    public static Users loginUser(String passedUserName, String passedUserPassword) {

        try {
            String sql = "SELECT * FROM users WHERE BINARY User_Name = ? AND BINARY Password = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setString(1, passedUserName);
            ps.setString(2, passedUserPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users matchedUser = new Users(userId, userName, userPassword);
                return matchedUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//
//    InterfaceLoginUser loginUser = (userName, passWord) -> {
//        try {
//            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
//            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
//            ps.setString(1, userName);
//            ps.setString(2, passWord);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()) {
//                int userId = rs.getInt("User_ID");
//                String uName = rs.getString("User_Name");
//                String uPassword = rs.getString("Password");
//                Users matchedUser = new Users(userId, uName, uPassword);
//                return matchedUser;
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    };


}
