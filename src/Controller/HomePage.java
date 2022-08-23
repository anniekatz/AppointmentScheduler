package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

// Controller Class used by HomePage view to represent a home page upon successful user login
public class HomePage {

    // Navigation button variables
    @FXML
    public Button AppointmentsButton;
    @FXML
    public Button CustomersButton;
    @FXML
    public Button ReportsButton;

    // Navigation button methods to navigate to each page
    @FXML
    void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavToCustomers(event);
    }
    @FXML
    void NavToReports(ActionEvent event) {
        ControllerUtils.NavToReports(event);
    }

}
