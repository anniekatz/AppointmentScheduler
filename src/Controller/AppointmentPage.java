package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AppointmentPage {
    @FXML
    private Button ReportsButton;
    @FXML
    private Button CustomersButton;

    // Navigation button methods
    @FXML
    void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavToCustomers(event);
    }
    @FXML
    void NavToReports(ActionEvent event) throws IOException {
            ControllerUtils.NavToReports(event);
    }
}
