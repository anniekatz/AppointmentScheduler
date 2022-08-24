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

    // Get appointment methods
    public int getAppointmentID() { return appointmentID; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public int getCustomerID() { return customerID; }
    public int getUserID() { return userID; }
    public int getContactID() { return contactID; }

}