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

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
//        LocalTime newTime = LocalTime.now();
//        System.out.println(newTime);
//        System.out.println(LocalDateTime.now());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime().toLocalDateTime() + " converted");
//System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime());

        //System.out.println(DBCountries.getAllCountries().get(2).getCountryName());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getDescription());
//        LocalDateTime newlocaldatetime = DBAppointments.getAllAppointments().get(0).getStartTime().toLocalDateTime();
//        System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime().toLocalDateTime());
//        System.out.println(DBContacts.getAllContacts().get(1).getContactEmail());
        launch(args);
        DBConnections.closeConnection();
    }
}
