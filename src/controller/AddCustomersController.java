package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCustomersController {

    @FXML
    private

    /** Cancels the save and closes the save customer form.*/
    @FXML
    public void cancelSaveButtonClicked(MouseEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
