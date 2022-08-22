package Model.ReportModels;

import java.time.LocalDateTime;

public class Report2 {
    private int AppointmentID;
    private String Title;
    private String Type;
    private String Description;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int CustomerID;

    public Report2(int AppointmentID, String Title, String Type, String Description, LocalDateTime Start, LocalDateTime End, int CustomerID) {
        this.AppointmentID = AppointmentID;
        this.Title = Title;
        this.Type = Type;
        this.Description = Description;
        this.Start = Start;
        this.End = End;
        this.CustomerID = CustomerID;
    }

    public int getAppointmentID() { return AppointmentID; }
    public String getTitle() { return Title; }
    public String getType() { return Type; }
    public String getDescription() { return Description; }
    public LocalDateTime getStart() { return Start; }
    public LocalDateTime getEnd() { return End; }
    public int getCustomerID() { return CustomerID; }

}
