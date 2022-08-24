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

    /**
     * This method gets appointment ID
     * @return Int value of appointment ID
     */
    public int getAppointmentID() { return appointmentID; }

    /**
     * This method gets title
     * @return String value of appointment title
     */
    public String getTitle() { return title; }

    /**
     * This method gets type
     * @return String value of appointment type
     */
    public String getType() { return type; }

    /**
     * This method gets description
     * @return String value of appointment description
     */
    public String getDescription() { return description; }

    /**
     * This method gets start time
     * @return LocalDateTime value of appointment start time
     */
    public LocalDateTime getStart() { return start; }

    /**
     * This method gets end time
     * @return LocalDateTime value of appointment end time
     */
    public LocalDateTime getEnd() { return end; }

    /**
     * This method gets customer ID
     * @return Int value of customer ID
     */
    public int getCustomerID() { return customerID; }

}
