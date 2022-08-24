package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller Class HomePage shows HomePage view to represent a home page upon successful user login.
 * It contains methods to navigate to other pages.
 */
public class HomePage {

    @FXML
    public Button AppointmentsButton;
    @FXML
    public Button CustomersButton;
    @FXML
    public Button ReportsButton;

    /**
     * This method navigates to appointment page using ControllerUtils method.
     * @param event ActionEvent is triggered when appointments button is clicked
     */
    @FXML
    void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavToAppointments(event);
    }

    /**
     * This method navigates to customer page using ControllerUtils method.
     * @param event ActionEvent is triggered when customers button is clicked
     */
    @FXML
    void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavToCustomers(event);
    }

    /**
     * This method navigates to report page using ControllerUtils method.
     * @param event ActionEvent is triggered when reports button is clicked
     */
    @FXML
    void NavToReports(ActionEvent event) {
        ControllerUtils.NavToReports(event);
    }

}
