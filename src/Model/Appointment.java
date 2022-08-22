package Model;

import java.time.LocalDateTime;

public class Appointment {
    private int AppointmentID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int CustomerID;
    private int UserID;
    private int ContactID;

    // Appointment constructor
    public Appointment(int AppointmentID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int CustomerID, int UserID, int ContactID) {
        this.AppointmentID = AppointmentID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.CustomerID = CustomerID;
        this.UserID = UserID;
        this.ContactID = ContactID;
    }

    // Get methods
    public int getAppointmentID() { return AppointmentID; }
    public String getTitle() { return Title; }
    public String getDescription() { return Description; }
    public String getLocation() { return Location; }
    public String getType() { return Type; }
    public LocalDateTime getStart() { return Start; }
    public LocalDateTime getEnd() { return End; }
    public int getCustomerID() { return CustomerID; }
    public int getUserID() { return UserID; }
    public int getContactID() { return ContactID; }

}