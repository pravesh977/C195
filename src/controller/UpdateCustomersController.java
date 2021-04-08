package controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivision;
import javafx.collections.ObservableList;
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

import java.io.IOException;

public class UpdateCustomersController {

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
        divisionComboBox.setItems(DBFirstLevelDivision.getAllFirstLevelDivisions());
        divisionComboBox.setPromptText("Choose Division");
        divisionComboBox.setVisibleRowCount(5);

        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setPromptText("Choose Country");
        countryComboBox.setVisibleRowCount(5);
    }

    @FXML
    public void populateUpdateForm(Customers passedCustomer) {
        nameTextField.setText(passedCustomer.getCustomerName());
        addressTextField.setText(passedCustomer.getCustomerAddress());
        postalTextField.setText(passedCustomer.getCustomerPostalCode());
        phoneTextField.setText(passedCustomer.getCustomerPhone());
        int passedDivisionId = passedCustomer.getDivisionId();
        //System.out.println(passedDivisionId);
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

    /** Tracks the change in Country ComboBox values and filters divisions values in divisions ComboBox*/
    public void countryComboBoxValueChange() {
        divisionComboBox.getItems().clear();
        int selectedCountryId = countryComboBox.getValue().getCountryId();
        ObservableList<FirstLevelDivisions> filteredDivision = DBFirstLevelDivision.getFilteredDivisions(selectedCountryId);
        divisionComboBox.setItems(filteredDivision);
    }

    @FXML
    public void cancelUpdateButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/customers_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
