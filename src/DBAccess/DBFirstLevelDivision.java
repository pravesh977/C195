package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.FirstLevelDivisions;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivision {

//    private static ObservableList<FirstLevelDivisions> allFirstLevelDivisions = FXCollections.observableArrayList();
//    private static ObservableList<FirstLevelDivisions> filteredFirstLevelDivisions = FXCollections.observableArrayList();

    //private static ComboBox<FirstLevelDivisions> allFirstLevelDivisions = new ComboBox();

    /** Returns all first level divisions.*/
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        //public static ComboBox<FirstLevelDivisions> getAllFirstLevelDivisions() {

        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                FirstLevelDivisions firstLevelDiv = new FirstLevelDivisions(id, name, countryId);
                allFirstLevelDivisions.add(firstLevelDiv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFirstLevelDivisions;
    }

    /** Returns selected first level divisions by using the Country Id.*/
    public static ObservableList<FirstLevelDivisions> getFilteredDivisions(int countryComboBoxValue) {
        ObservableList<FirstLevelDivisions> filteredFirstLevelDivisions = FXCollections.observableArrayList();

        //System.out.println("passed country id is : " + countryComboBoxValue);
        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, countryComboBoxValue);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                FirstLevelDivisions firstLevelDiv = new FirstLevelDivisions(id, name, countryId);
                filteredFirstLevelDivisions.add(firstLevelDiv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        for(FirstLevelDivisions element:filteredFirstLevelDivisions) {
//            System.out.println("filtered: " + element.getDivisionName());
//        }
//        filteredFirstLevelDivisions.get(0);
//        filteredFirstLevelDivisions.get(1);

        return filteredFirstLevelDivisions;
    }

}
