package controller;

import DBAccess.DBCustomers;
import javafx.beans.binding.Bindings;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Customers;
import utils.DBConnections;

import java.io.IOException;
import java.util.Optional;

public class CustomersScreenController {
    Stage stage;
    Parent scene;

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
//        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
//        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setTitle("Main Menu");
//        stage.setScene(scene);
//        stage.show();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void initialize() {
        customersTable.setItems(DBCustomers.getAllCustomers());
        customersColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customersColumnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersColumnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customersColumnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customersColumnFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
    }


    public void loadCustomerTable() {
        customersTable.setItems(DBCustomers.getAllCustomers());
        customersColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customersColumnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersColumnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customersColumnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customersColumnFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        System.out.println("loaded bud");
    }


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
            System.out.println("search called");
        }
        searchCustomerTextField.clear();
    }

    /***/
    @FXML
    public void openAddForm(MouseEvent event) throws IOException {
        //this opens a new window for a form, i will use a modal instead below
//        Parent root = FXMLLoader.load(getClass().getResource("../view/add_customers_screen.fxml"));
//        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setTitle("Add customer");
//        stage.setScene(scene);
//        stage.show();


        //Using a modal form instead
        Parent root = FXMLLoader.load(getClass().getResource("../view/add_customers_screen.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Customer Form");
        stage.setScene(new Scene(root));
        stage.show();

//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("../view/add_customers_screen.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }

    @FXML
    public void updateCustomerButtonClicked(MouseEvent event) throws IOException {
        //FIX Me add nullpointer exception catcher for updating without selecting
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/update_customers_screen.fxml"));
        loader.load();

        UpdateCustomersController updateCustomerCont = loader.getController();

        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        updateCustomerCont.populateUpdateForm(selectedCustomer);

//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        Parent scene = loader.getRoot();
//        stage.setScene(new Scene(scene));
//        stage.show();


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void deleteSelectedCustomer() {
        Customers selectedCustomerForDeletion = customersTable.getSelectionModel().getSelectedItem();
        DBCustomers.deleteCustomer(selectedCustomerForDeletion.getCustomerId());
        loadCustomerTable();
    }

    /** Handles the exit button*/
    @FXML
    public void handleExit() {
        System.exit(0);
    }

}
