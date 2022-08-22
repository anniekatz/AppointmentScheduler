package Controller;

import Database.QueryTables.AppointmentsTable;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportPage implements Initializable {

    @FXML
    private Tab AppointmentTotalsTab;
    @FXML
    private TableView<?> AppointmentTotalsTable;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableMonthColumn;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableTotalColumn;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableTypeColumn;
    @FXML
    private Button AppointmentsButton;
    @FXML
    private ComboBox<?> ChooseContactComboBox;
    @FXML
    private ComboBox<String> ChooseMonthComboBox;
    @FXML
    private TextField ContactIDTextField;
    @FXML
    private Tab ContactScheduleTab;
    @FXML
    private TableView<?> ContactScheduleTable;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableAppointmentIDColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableCustomerIDColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableDescriptionColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableEndColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableStartColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableTitleColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableTypeColumn;
    @FXML
    private Tab CustomerTotalsTab;
    @FXML
    private TableView<?> CustomerTotalsTable;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableCustomerIDColumn;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableCustomerNameColumn;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableTotalsColumn;
    @FXML
    private Button CustomersButton;
    @FXML
    void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavToCustomers(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Report 1 Initializables
        InitializeMonthComboBox();


        // Report 2 Initializables


        // Report 3 Initializables
    }

    // Report 1 Methods
    void InitializeMonthComboBox() {
        // Fill ChooseMonthComboBox with months
        ChooseMonthComboBox.getItems().addAll("All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        ChooseMonthComboBox.setValue("All");
    }
    void InitializeReport1Table() {
    }


    void PopulateReport1Table(ActionEvent event){
        //
    }

    // Report 2 Methods

    // Report 3 Methods
}

