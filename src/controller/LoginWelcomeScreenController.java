package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWelcomeScreenController {

    /** Handles the login button and switches the window to main page. */
    @FXML
    public void loginClicked(MouseEvent event) throws IOException {
        //FIXME: handle user name and password entered, show error
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_menu.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitApplication() {
        System.exit(0);
    }

}
