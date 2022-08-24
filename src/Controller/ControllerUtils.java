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

/**
 * ControllerUtils class contains methods controllers share
 */
public class ControllerUtils {

    /**
     * This method navigates to a different window.
     * @param event ActionEvent clicking a navigation button
     * @param PageResource String path to the FXML page to be loaded
     * @param PageTitle String title of the page to be loaded, displays title.
     */
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

    /**
     * This method navigates to appointments page.
     * @param event ActionEvent clicking the appointments button
     */
    @FXML
    public static void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/AppointmentPage.fxml", "Appointments");
    }

    /**
     * This method navigates to customers page.
     * @param event ActionEvent clicking the customers button
     */
    @FXML
    public static void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/CustomerPage.fxml", "Customers");
    }

    /**
     * This method navigates to reports page.
     * @param event ActionEvent clicking the reports button
     */
    @FXML
    public static void NavToReports(ActionEvent event) {
        ControllerUtils.NavigateToWindow(event, "/View/ReportPage.fxml", "Reports");
    }

    /**
     * This method gets a new time for a time zone for an existing time in another time zone.
     * @param OtherTimeZone String time zone of the previous time zone
     * @param FinalTimeZone String time zone of the final time zone
     * @param hour int hour of the previous time zone's time
     * @param minute int minute of the previous time zone's time
     * @return OffsetTime as the time in the final time zone
     */
    public static OffsetTime GetNewTime(ZoneId OtherTimeZone, ZoneId FinalTimeZone, int hour, int minute) {
        // Get System offset and compare to Other zone offset
        OffsetTime OtherZoneOffset = OffsetTime.now(OtherTimeZone);
        OffsetTime FinalOffsetTime = OffsetTime.now(FinalTimeZone);

        int hoursDiff = FinalOffsetTime.getHour() - OtherZoneOffset.getHour();
        OffsetTime OldTime = OffsetTime.of(hour, minute, 0, 0, OtherZoneOffset.getOffset());
        OffsetTime NewTime = OldTime.plusHours(hoursDiff);

        return NewTime;
    }

    /**
     * This method converts time-based data to UTC and format it as a string for database storage.
     * @param LocalDateTime time in system time zone to be converted
     * @return String time in UTC, formatted for database storage
     */
    public static String ConvertToUTC(LocalDateTime LocalDateTime) {
        ZonedDateTime SystemDateTime = LocalDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime UTCDateTime = SystemDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        // Convert UTCDateTime to formatted String to store in database
        return UTCDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
