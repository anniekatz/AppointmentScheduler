package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.time.LocalDateTime;

public class AppointmentPage {

    // Toggle filters for appointment table
    @FXML
    private ToggleGroup FilterApptTG;
    @FXML
    private RadioButton AllFilterRadioButton;
    @FXML
    private RadioButton MonthFilterRadioButton;
    @FXML
    private RadioButton WeekFilterRadioButton;

    // Tableview variables
    @FXML
    private TableView<Appointment> ApptTable;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableAppointmentIDColumn;
    @FXML
    private TableColumn<?,?> ApptTableContactColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableCustomerIDColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableDescriptionColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> ApptTableEndColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableLocationColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> ApptTableStartColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableTitleColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableTypeColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableUserIDColumn;

    // Form variables
    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private ComboBox<?> ContactComboBox;
    @FXML
    private ComboBox<?> CustIDComboBox;
    @FXML
    private Button CustomersButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private TextField DescriptionTextField;
    @FXML
    private DatePicker EndDatePicker;
    @FXML
    private ComboBox<?> EndTimeComboBox;
    @FXML
    private Button AddUpdateButton;
    @FXML
    private ComboBox<?> LocationComboBox;
    @FXML
    private Button ReportsButton;
    @FXML
    private DatePicker StartDatePicker;
    @FXML
    private ComboBox<?> StartTimeComboBox;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField TypeTextField;
    @FXML
    private ComboBox<?> UserIDComboBox;




    @FXML
    void FilterApptView(ActionEvent event) {

    }

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

