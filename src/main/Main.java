package main;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCountries;
import com.mysql.cj.result.LocalDateValueFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import utils.DBConnections;
import utils.TimeZoneConversion;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {

    /**
     * Loads the main starting page of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/login_welcome_screen.fxml"));
        primaryStage.setTitle("Appointment Software");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
    }


    /**
     * Starting point of the application. As soon as the application starts, start a connection with the database. Close
     * the connection after the app closes.
     */
    public static void main(String[] args) {
        DBConnections.startConnection();
//
//        for(Appointments element : DBAppointments.getAppointmentsForLoggedInUser(2)) {
//            System.out.println(element.getDescription());
//            System.out.println(element.getStartTime());
//        }
        //System.out.println(DBAppointments.getAppointmentsForLoggedInUser(2).get(1).getDescription());

        //System.out.println(DBAppointments.getAppointmentsByTypeAndMonth().get(5).getMonth());

//        LocalDate localD = LocalDate.now();
//        LocalTime zeroHoursAndMinutes = LocalTime.of(0, 00);
//        LocalDateTime zeroLocalDT = LocalDateTime.of(localD, zeroHoursAndMinutes);
//        LocalDateTime firstDayOfMonth = zeroLocalDT.with(TemporalAdjusters.firstDayOfMonth());
//        LocalDateTime lastDayOfMonth = zeroLocalDT.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(firstDayOfMonth + "first date of month");
//        System.out.println(lastDayOfMonth + "last date of month");

//        LocalDateTime localDT = LocalDateTime.now();
//        System.out.println(localDT.with(TemporalAdjusters.firstDayOfMonth()) + " first date of current month");
//        System.out.println(localDT.with(TemporalAdjusters.lastDayOfMonth()) + " last day of month");
//
//        LocalDate localD = LocalDate.now();
//        LocalTime zeroHoursAndMins = LocalTime.of(0, 00);
//        LocalDateTime zeroLocalDT = LocalDateTime.of(localD, zeroHoursAndMins);
//        System.out.println(zeroLocalDT + " with zero hours");

        //getting starting week and end days of the week
//        LocalDateTime now = LocalDateTime.now();
//        TemporalField fieldUS = WeekFields.of(Locale.getDefault()).dayOfWeek();
//        System.out.println(now.with(fieldUS, 1)); // 2015-02-08 (Sunday)
//        System.out.println(now.with(fieldUS, 7)); // 2015-02-08 (Monday)


//        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
//        LocalDate firstDayOfThisWeek = LocalDate.now ( localZoneId ).with ( DayOfWeek.MONDAY );
//
//        ZonedDateTime thisWeekStart = firstDayOfThisWeek.atStartOfDay ( localZoneId );
//        System.out.println(firstDayOfThisWeek);
//        System.out.println(thisWeekStart);

//        System.out.println(currentLDT);
//        System.out.println(currentLDT.getDayOfMonth());
//        System.out.println(currentLDT.getDayOfWeek());
//        System.out.println(currentLDT.with(DayOfWeek.MONDAY));
//        System.out.println(currentLDT.with(DayOfWeek.SUNDAY));
        //System.out.println(currentLDT.get(WeekFields.of(locale).weekOfYear())

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


//        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
//        System.out.println(localZoneId);


        launch(args);
        DBConnections.closeConnection();
    }
}
