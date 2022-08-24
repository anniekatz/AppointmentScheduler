package Model;

import java.time.LocalDateTime;

/**
 * This class represents the model for an Appointment.
 */
public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * This is the constructor for an appointment.
     * @param appointmentID Int value of appointment ID
     * @param title String value of appointment title
     * @param description String value of appointment description
     * @param location String value of appointment location
     * @param type String value of appointment type
     * @param start LocalDateTime value of appointment start time
     * @param end LocalDateTime value of appointment end time
     * @param customerID Int value of customer ID
     * @param userID Int value of user ID
     * @param contactID Int value of contact ID
     */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * This method gets appointment ID
     * @return int appointment ID
     */
    public int getAppointmentID() { return appointmentID; }

    /**
     * This method gets title
     * @return String value of appointment title
     */
    public String getTitle() { return title; }

    /**
     * This method gets description
     * @return String value of appointment description
     */
    public String getDescription() { return description; }

    /**
     * This method gets location
     * @return String value of appointment location
     */
    public String getLocation() { return location; }

    /**
     * This method gets type
     * @return String value of appointment type
     */
    public String getType() { return type; }

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
     * @return int value of customer ID
     */
    public int getCustomerID() { return customerID; }

    /**
     * This method gets user ID
     * @return int value of user ID
     */
    public int getUserID() { return userID; }

    /**
     * This method gets contact ID
     * @return int value of contact ID
     */
    public int getContactID() { return contactID; }

}