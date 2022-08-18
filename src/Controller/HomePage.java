package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePage {
    public Button AppointmentsButton;
    public Button CustomersButton;
    public Button ReportsButton;


    // Navigation button methods
    @FXML
    void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavToCustomers(event);
    }
    @FXML
    void NavToReports(ActionEvent event) throws IOException {
        ControllerUtils.NavToReports(event);
    }


}
