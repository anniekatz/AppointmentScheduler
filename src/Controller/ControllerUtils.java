package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Class contains methods that controllers share
public class ControllerUtils {

    // Method to navigate to another window upon button click
    public static void NavigateToWindow(ActionEvent event, String PageResource, String PageTitle) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(ControllerUtils.class.getResource(PageResource)));
            stage.setTitle(PageTitle);
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Navigation button methods for all navigation buttons throughout application
    @FXML
    public static void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/AppointmentPage.fxml", "Appointments");
    }

    @FXML
    public static void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/CustomerPage.fxml", "Customers");
    }

    @FXML
    public static void NavToReports(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/ReportPage.fxml", "Reports");
    }

    // Method to convert time-based data between time zones
    public static OffsetTime GetNewTime(ZoneId OtherTimeZone, ZoneId FinalTimeZone, int hour, int minute) {
        // Get System offset and compare to Other zone offset
        OffsetTime OtherZoneOffset = OffsetTime.now(OtherTimeZone);
        OffsetTime FinalOffsetTime = OffsetTime.now(FinalTimeZone);

        int hoursDiff = FinalOffsetTime.getHour() - OtherZoneOffset.getHour();
        OffsetTime OldTime = OffsetTime.of(hour, minute, 0, 0, OtherZoneOffset.getOffset());
        OffsetTime NewTime = OldTime.plusHours(hoursDiff);

        return NewTime;
    }

    // Method to convert time-based data to UTC and format it as a string for database storage
    public static String ConvertToUTC(LocalDateTime LocalDateTime) {
        ZonedDateTime SystemDateTime = LocalDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime UTCDateTime = SystemDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        // Convert UTCDateTime to formatted String to store in database
        return UTCDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
