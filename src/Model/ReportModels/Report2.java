package Model.ReportModels;
import java.time.LocalDateTime;

/**
 * This class represents the model for Report 2: Contact Schedule.
 */
public class Report2 {

    private int appointmentID;
    private String title;
    private String type;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;

    /**
     * This is the constructor for Report 2 - contact schedule.
     * @param appointmentID Int value of appointment ID
     * @param title String value of appointment title
     * @param type String value of appointment type
     * @param description String value of appointment description
     * @param start LocalDateTime value of appointment start time
     * @param end LocalDateTime value of appointment end time
     * @param customerID Int value of customer ID
     */
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
