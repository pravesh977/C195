package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;
import utils.DBConnections;

import java.io.IOException;

/**
 * Controller class that handles the add_customers_screen.fxml file.
 */
public class AddCustomersController {

    Stage stage;
    Parent scene;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField postalTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox<FirstLevelDivisions> divisionComboBox;

    @FXML
    private ComboBox<Countries> countryComboBox;


    /**
     * Initializes the add customer form and sets ComboBox with database values (Country and Division).
     */
    @FXML
    public void initialize() {
        divisionComboBox.getItems().clear();
        divisionComboBox.setItems(DBFirstLevelDivision.getAllFirstLevelDivisions());
        divisionComboBox.setPromptText("Choose Division");
        divisionComboBox.setVisibleRowCount(5);

        countryComboBox.getItems().clear();
        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setPromptText("Choose Country");
        countryComboBox.setVisibleRowCount(5);

    }

    /**
     * Handles the save button and sends data to to the database. After clicking save button, a new
     * Customers object is created which is then sent to DBCustomers.addNewCustomer method where it is then saved
     * to the database.
     */
    @FXML
    public void saveNewCustomerClicked(MouseEvent event) throws IOException {
        try {
            int id = 0;
            String name = nameTextField.getText().trim();
            String address = addressTextField.getText().trim();
            String postal = postalTextField.getText().trim();
            String phone = phoneTextField.getText().trim();
            //System.out.println("text field name is " + name);
            int divisionId = divisionComboBox.getValue().getDivisionId();
            String divisionName = divisionComboBox.getValue().getDivisionName();
            Customers newCustomer = new Customers(id, name, address, postal, phone, divisionId, divisionName);
            DBCustomers.addNewCustomer(newCustomer);

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/customers_screen.fxml"));
//        loader.load();
//
//        CustomersScreenController custCont = loader.getController();
//        custCont.loadCustomerTable();

            //use this to close if not modal
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException exp) {
            AlertMessageController.nullValueEntry();
        }

        //use this to close the modal when using modal
//        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
//        stage.close();


        //add alerter saying add successful and button to add appointment in the same alerter
        // how to refresh table to show new customer? call function from another file how?

//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("../view/customers_screen.fxml"));
//            loader.load();
//
//            CustomersScreenController customerUpdate = loader.getController();
//            customerUpdate.searchCustomer();
//        }
//        catch(RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
    }

    /**
     * Tracks the change in Country ComboBox values and filters divisions values in divisions ComboBox.
     */
    public void countryComboBoxValueChange() {
        divisionComboBox.getItems().clear();
//        System.out.println(countryComboBox.getValue());
//        System.out.println(countryComboBox.getValue().getCountryId());
        int selectedCountryId = countryComboBox.getValue().getCountryId();
        ObservableList<FirstLevelDivisions> filteredDivision = DBFirstLevelDivision.getFilteredDivisions(selectedCountryId);
//        for(FirstLevelDivisions element:filteredDivision) {
//            System.out.println("divisions are : " + element.getDivisionName());
//        }
        divisionComboBox.setItems(filteredDivision);
        divisionComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Cancels the save and closes the save customer form.
     */
    @FXML
    public void cancelSaveButtonClicked(MouseEvent event) throws IOException {
//        use this if add form is a modal to close the modal on cancel
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        stage.close();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
