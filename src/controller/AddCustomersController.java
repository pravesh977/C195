package controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.FirstLevelDivisions;
import utils.DBConnections;

public class AddCustomersController {

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


    /** Initializes the opening form and sets ComboBox with database values*/
    @FXML
    public void initialize() {

        divisionComboBox.setItems(DBFirstLevelDivision.getAllFirstLevelDivisions());
        divisionComboBox.setPromptText("Choose Division");
        divisionComboBox.setVisibleRowCount(5);

        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setPromptText("Choose Country");
        countryComboBox.setVisibleRowCount(5);

    }

    /** Handles the save button and sends data to to the database.*/
    @FXML
    public void saveNewCustomerClicked() {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postal = postalTextField.getText();
        String phone = phoneTextField.getText();
        //System.out.println("text field name is " + name);

    }

    /** Tracks the change in Country ComboBox values and filters divisions values in divisions ComboBox*/
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
    }

    /**
     * Cancels the save and closes the save customer form.
     */
    @FXML
    public void cancelSaveButtonClicked(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
