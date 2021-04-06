package controller;

import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customers;
import utils.DBConnections;

import java.io.IOException;

public class CustomersScreenController {

    @FXML
    private TableView<Customers> customersTable;

    @FXML
    private TableColumn<Customers, Integer> customersColumnId;

    @FXML
    private TableColumn<Customers, String> customersColumnName;

    @FXML
    private TableColumn<Customers, String> customersColumnAddress;

    @FXML
    private TableColumn<Customers, String> customersColumnPostal;

    @FXML
    private TableColumn<Customers, String> customersColumnPhone;

    @FXML
    private TableColumn<Customers, String> customersColumnFirstLevelDivision;

    @FXML
    private TextField searchCustomerTextField;

    /** Handles the Main Menu button and switches the window to Main Screen. */
    @FXML
    public void navigateToMainScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


//    public void initialize() {
//        customersTable.setItems(DBCustomers.getAllCustomers());
//        customersColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        customersColumnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        customersColumnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
//        customersColumnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
//        customersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
//        customersColumnFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
//    }



    /** Handles the search button and the enter button for search customer.*/
    @FXML
    public void searchCustomer() {

        try {
            int searchedCustomerInteger = Integer.parseInt(searchCustomerTextField.getText());
            //Customers searchedCustomer = DBCustomers.lookupCustomers(searchedCustomerInteger);
            ObservableList<Customers> searchedCustomersList = DBCustomers.lookupCustomers(searchedCustomerInteger);
            if (searchedCustomersList == null) {
                System.out.println("customer not found alert message"); // FIX ME
            } else {
//                customersTable.getSelectionModel().select(searchedCustomer);
//                customersTable.scrollTo(searchedCustomer);
                customersTable.setItems(searchedCustomersList);
                customersColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
                customersColumnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
                customersColumnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
                customersColumnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
                customersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
                customersColumnFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

            }
        } catch (NumberFormatException e) {
            String searchedCustomerString = searchCustomerTextField.getText();
            ObservableList<Customers> searchedCustomersList = DBCustomers.lookupCustomers(searchedCustomerString);
            if (searchedCustomersList.size() == 0) {
                System.out.println("alert no customers found"); //FIX ME
            } else {
                customersTable.setItems(searchedCustomersList);
                customersColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
                customersColumnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
                customersColumnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
                customersColumnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
                customersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
                customersColumnFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
            }
        }
        searchCustomerTextField.clear();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }

}
