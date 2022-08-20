package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Class contains methods that controllers share
public class ControllerUtils {

    // Navigate to another window upon button click
    public static void NavigateToWindow(ActionEvent event, String PageResource, String PageTitle) throws IOException {

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(ControllerUtils.class.getResource(PageResource)));
            stage.setTitle(PageTitle);
            stage.setScene(new Scene(scene));
            stage.show();

    }
    // Navigation button methods for all pages
    @FXML
    public static void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavigateToWindow(event, "/View/AppointmentPage.fxml", "Appointments");
    }

    @FXML
    public static void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavigateToWindow(event, "/View/CustomerPage.fxml", "Customers");
    }

    @FXML
    public static void NavToReports(ActionEvent event) throws IOException {
        ControllerUtils.NavigateToWindow(event, "/View/ReportPage.fxml", "Reports");
    }



}
