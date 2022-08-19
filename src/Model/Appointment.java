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
    public int GetAppointmentID() {
        return AppointmentID;
    }
    public String GetTitle() {
        return Title;
    }
    public String GetDescription() {
        return Description;
    }
    public String GetLocation() {
        return Location;
    }
    public String GetType() {
        return Type;
    }
    public LocalDateTime GetStart() {
        return Start;
    }
    public LocalDateTime GetEnd() {
        return End;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public String GetCreatedBy() {
        return CreatedBy;
    }
    public LocalDateTime GetLastUpdate() {
        return LastUpdate;
    }
    public String GetLastUpdateBy() {
        return LastUpdateBy;
    }
    public int GetCustomerID() {
        return CustomerID;
    }
    public int GetUserID() {
        return UserID;
    }
    public int GetContactID() {
        return ContactID;
    }

}