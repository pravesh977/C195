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
import utils.TimeZoneConversion;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;

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


//        System.out.println(Locale.getDefault());
        //System.out.println(Instant.now() + "CURRENT UtC TIME Is");
        //TimeZoneConversion.utcToLocal();
    //    TimeZoneConversion.displayUtc();
//        DBAppointments.getStartUTC();
//        System.out.println(LocalDateTime.now() + " is the localdatetime");
//        System.out.println(ZonedDateTime.now() + " is the zonedatetime");
////
//        ZoneId karachi = ZoneId.of("Asia/Karachi");
//        System.out.println(ZonedDateTime.now(karachi) + " is the karachi");
//        System.out.println(ZoneId.getAvailableZoneIds().size());

//        for(String element:ZoneId.getAvailableZoneIds()){
//            System.out.println(element);
//        }

//        LocalTime newTime = LocalTime.now();
//        System.out.println(newTime);
//        System.out.println(LocalDateTime.now());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime().toLocalDateTime() + " converted");
//System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime());

        //System.out.println(DBCountries.getAllCountries().get(2).getCountryName());
//        System.out.println(DBAppointments.getAllAppointments().get(0).getDescription());
//        LocalDateTime newlocaldatetime = DBAppointments.getAllAppointments().get(0).getStartTime();
//        System.out.println(newlocaldatetime);
//        System.out.println(newlocaldatetime.toLocalDate());
        //System.out.println(DBAppointments.getAllAppointments().get(0).getStartTime());
//        System.out.println(DBContacts.getAllContacts().get(1).getContactEmail());
        launch(args);
        DBConnections.closeConnection();
    }
}
