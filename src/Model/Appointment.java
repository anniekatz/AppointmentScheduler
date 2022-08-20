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
    private LocalDateTime CreateDate;
    private String CreatedBy;
    private LocalDateTime LastUpdate;
    private String LastUpdateBy;
    private int CustomerID;
    private int UserID;
    private int ContactID;

    // Appointment constructor
    public Appointment(int AppointmentID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, LocalDateTime CreateDate, String CreatedBy, LocalDateTime LastUpdate, String LastUpdateBy, int CustomerID, int UserID, int ContactID) {
        this.AppointmentID = AppointmentID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.CreateDate = CreateDate;
        this.CreatedBy = CreatedBy;
        this.LastUpdate = LastUpdate;
        this.LastUpdateBy = LastUpdateBy;
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
    public LocalDateTime getCreateDate() { return CreateDate; }
    public String getCreatedBy() { return CreatedBy; }
    public LocalDateTime getLastUpdate() { return LastUpdate; }
    public String getLastUpdateBy() { return LastUpdateBy; }
    public int getCustomerID() { return CustomerID; }
    public int getUserID() { return UserID; }
    public int getContactID() { return ContactID; }
}