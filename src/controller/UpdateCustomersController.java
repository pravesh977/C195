package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivision;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

import java.io.IOException;

public class UpdateCustomersController {

    Stage stage;
    Parent scene;

    @FXML
    private Label customerIdLabel;

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

    @FXML
    public void populateUpdateForm(Customers passedCustomer) {
        customerIdLabel.setText(String.valueOf(passedCustomer.getCustomerId()));
        nameTextField.setText(passedCustomer.getCustomerName());
        addressTextField.setText(passedCustomer.getCustomerAddress());
        postalTextField.setText(passedCustomer.getCustomerPostalCode());
        phoneTextField.setText(passedCustomer.getCustomerPhone());
        int passedDivisionId = passedCustomer.getDivisionId();
        System.out.println("passed customers id is " + passedCustomer.getCustomerId());
        System.out.println(passedDivisionId);
        int countryId = 0;

        for(FirstLevelDivisions element : DBFirstLevelDivision.getAllFirstLevelDivisions()) {
            if (passedDivisionId == element.getDivisionId()) {
                //System.out.println("match found at " + element.getDivisionId());
                countryId = element.getCountryId();
                //divisionComboBox.setValue(DBFirstLevelDivision.getAllFirstLevelDivisions().get(1));
                divisionComboBox.getSelectionModel().select(element);
                break;
            }
        }

        for(Countries element : DBCountries.getAllCountries()) {
            if (countryId == element.getCountryId()) {
                countryComboBox.getSelectionModel().select(element);
                break;
            }
        }



//        int position = -1;
//        for(int i = 0; i < divisionComboBox.getItems().size(); i++) {
//            position++;
//            if (passedDivisionId == divisionComboBox.getItems().get(i) {
//                System.out.println("found at position " + position);
//                divisionComboBox.setValue(DBFirstLevelDivision.getAllFirstLevelDivisions().get(position));
//                break;
//
////                //divisionComboBox.getSelectionModel().select(i);
////                //divisionComboBox.setValue(passedCustomer.);
//            }
//        }
//        System.out.println(position);
    }

    public void updateCustomer(MouseEvent event) throws IOException {
        int id = 0;
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postal = postalTextField.getText();
        String phone = phoneTextField.getText();
        //System.out.println("text field name is " + name);
        int divisionId = divisionComboBox.getValue().getDivisionId();
        String divisionName = divisionComboBox.getValue().getDivisionName();
        Customers updateThisCustomer = new Customers(id, name, address, postal, phone, divisionId, divisionName);
        DBCustomers.updateCustomer(Integer.parseInt(customerIdLabel.getText()), updateThisCustomer);
        //System.out.println(customerIdLabel.getText() + " id passed?");

//        Parent root = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
//        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Tracks the change in Country ComboBox values and filters divisions values in divisions ComboBox*/
    public void countryComboBoxValueChange() {
        divisionComboBox.getItems().clear();
        int selectedCountryId = countryComboBox.getValue().getCountryId();
        ObservableList<FirstLevelDivisions> filteredDivision = DBFirstLevelDivision.getFilteredDivisions(selectedCountryId);
        divisionComboBox.setItems(filteredDivision);
        divisionComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void cancelUpdateButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
