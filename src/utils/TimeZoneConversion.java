package utils;

import controller.AlertMessageController;

import java.sql.Time;
import java.time.*;
import java.util.TimeZone;

public class TimeZoneConversion {

    public static void utcToLocalConversion(LocalDateTime passedDateTime) {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZoneId utcZoneId = ZoneId.of("UTC");
        LocalTime passedLocalTime = passedDateTime.toLocalTime();
        LocalDate passedLocalDate = passedDateTime.toLocalDate();

        //utcPassedZDT has the passed zoneddatetime values stored as database's zoneddatetime values
        ZonedDateTime utcPassedZDT = ZonedDateTime.of(passedLocalDate, passedLocalTime, localZoneId);
        System.out.println(utcPassedZDT + " start time");
    }

    /**
     * Method to convert form's time to UTC and returns the utc for database entry for start appointment
     */
    public static LocalDateTime localToUtcConversion(LocalDateTime passedDateTime) {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZoneId utcZoneId = ZoneId.of("UTC");
        LocalTime passedLocalTime = passedDateTime.toLocalTime();
        LocalDate passedLocalDate = passedDateTime.toLocalDate();

        //localPassedZDT has the passed zoneddatetime values stored as local machine's zoneddatetime values
        ZonedDateTime localPassedZDT = ZonedDateTime.of(passedLocalDate, passedLocalTime, localZoneId);

        //UTC instant: passed value is converted to UTC for database
        Instant localToUtcInstant = localPassedZDT.toInstant();

        //converting Instant to ZoneDateTime Version
        ZonedDateTime utcEquivalent = localToUtcInstant.atZone(utcZoneId);

        //Converting everything to LocalDateTime For Appointment Object
        LocalDate localToUtcDate = utcEquivalent.toLocalDate();
        LocalTime localToUtcTime = utcEquivalent.toLocalTime();
        LocalDateTime convertedFinalUtc = LocalDateTime.of(localToUtcDate, localToUtcTime);

        return convertedFinalUtc;

    }

    public static int estConversion(LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime) {

        ZoneId estZoneId = ZoneId.of("America/New_York");

        //Business Hours Declaration for EST
        LocalTime businessStartingTime = LocalTime.of(8, 00);
        LocalTime businessClosingTime = LocalTime.of(22, 00);
        LocalDate businessDate = appointmentStartTime.toLocalDate();
        ZonedDateTime estOfficeStartZDT = ZonedDateTime.of(businessDate, businessStartingTime, estZoneId);
        ZonedDateTime estOfficeEndZDT = ZonedDateTime.of(businessDate, businessClosingTime, estZoneId);

//        System.out.println(estOfficeStartZDT + " this is the starting hours in EST");
//
//        System.out.println(estOfficeEndZDT + " this is the closing hours in EST");

        //getting the passed date/time and setting it up as ZoneDateTime
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        LocalDate localStartDate = appointmentStartTime.toLocalDate();
        LocalTime localStartTime = appointmentStartTime.toLocalTime();
        ZonedDateTime appointmentStartLocalZDT = ZonedDateTime.of(localStartDate, localStartTime, localZoneId);
        //System.out.println(appointmentStartLocalZDT + " local zone date for appointment start time is" );
        LocalDate localEndDate = appointmentEndTime.toLocalDate();
        LocalTime localEndTime = appointmentEndTime.toLocalTime();
        ZonedDateTime appointmentEndLocalZDT = ZonedDateTime.of(localEndDate, localEndTime, localZoneId);
        //System.out.println(appointmentEndLocalZDT + " local zone date for appointment end time is" );


//        //Unused?
//        ZonedDateTime localToEst = localZDT.withZoneSameLocal(estZoneId);
//        System.out.println(localToEst + " local to est");

        //converting ZonedDateTimes to LocalDateTime
//        LocalDate estDate = localToEst.toLocalDate();
//        LocalTime estTime = localToEst.toLocalTime();
//        LocalDateTime estLocalDateTime = LocalDateTime.of(estDate, estTime);
//        System.out.println(estLocalDateTime  + " est converted to LocalDateTime");
        //localDT.isBefore()
        if(appointmentStartLocalZDT.isBefore(estOfficeStartZDT) || appointmentEndLocalZDT.isBefore(estOfficeStartZDT) || appointmentEndLocalZDT.isAfter(estOfficeEndZDT) || appointmentEndLocalZDT.isAfter(estOfficeEndZDT)) {
            //System.out.println("business is closed");
            return 1;
        }
        else {
            //System.out.println("business is open");
            return 2;
        }

    }

}
