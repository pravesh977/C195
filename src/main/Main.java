package main;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Countries;
import utils.DBConnections;

import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login_welcome_screen.fxml"));
        primaryStage.setTitle("Appointment Software");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
    }


    public static void main(String[] args) {
        DBConnections.startConnection();

        //System.out.println(DBCountries.getAllCountries().get(2).getCountryName());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getDescription());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime());
//        System.out.println(DBContacts.getAllContacts().get(0).getContactEmail());
        launch(args);
        DBConnections.closeConnection();
    }
}
