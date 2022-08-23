package Model.ReportModels;
import java.time.LocalDateTime;

// Model class for Report 2
public class Report2 {

    // Report 2 variables
    private int appointmentID;
    private String title;
    private String type;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;

    // Report2 constructor
    public Report2(int appointmentID, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
    }

    // Get Report 2 data methods
    public int getAppointmentID() { return appointmentID; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public int getCustomerID() { return customerID; }

}
