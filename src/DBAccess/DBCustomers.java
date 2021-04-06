package DBAccess;

import com.mysql.cj.jdbc.exceptions.SQLError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import utils.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    private static ObservableList<Customers> customersList = FXCollections.observableArrayList();

    /** Method to look up customers using customer Id.*/
    public static ObservableList<Customers> lookupCustomers(int customerId) {
//        for(Customers element : customersList) {
//            if (element.getCustomerId() == customerId) {
//                return element;
//            }
//        }
//        return null;

        ObservableList<Customers> searchedSingleCustomer = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Customers singleFoundCustomer = new Customers(id, customerName, customerAddress, customerPostalCode, customerPhone, divisionId, divisionName);
                //System.out.println(singleFoundCustomer.getCustomerName() + " is the name");
                //return singleFoundCustomer;
                searchedSingleCustomer.add(singleFoundCustomer);
                return searchedSingleCustomer;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Customers> lookupCustomers(String customerName) {
        ObservableList<Customers> matchedCustomers = FXCollections.observableArrayList();

//        for (Customers element : customersList) {
//            if(element.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
//                matchedCustomers.add(element);
//            }
//        }

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID WHERE Customer_Name LIKE ?";
            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
            ps.setString(1, customerName + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Customer_ID");
                String foundCustomerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Customers customer = new Customers(id, foundCustomerName, customerAddress, customerPostalCode, customerPhone, divisionId, divisionName);
                matchedCustomers.add(customer);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return matchedCustomers;

    }

    /** Method to return a list of all Customers from the database*/
//    public static ObservableList<Customers> getAllCustomers() {
//
//        try {
//            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID";
//            PreparedStatement ps = DBConnections.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()) {
//                int customerId = rs.getInt("Customer_ID");
//                String customerName = rs.getString("Customer_Name");
//                String customerAddress = rs.getString("Address");
//                String customerPostalCode = rs.getString("Postal_Code");
//                String customerPhone = rs.getString("Phone");
//                int divisionId = rs.getInt("Division_ID");
//                String divisionName = rs.getString("Division");
//                Customers customer = new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhone, divisionId, divisionName);
//                customersList.add(customer);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return customersList;
//    }
}
