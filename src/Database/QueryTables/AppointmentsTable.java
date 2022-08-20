package Database.QueryTables;

import Database.QueryUtils;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentsTable {

    public static ObservableList<Appointment> GetAppointments() {
        ObservableList<Appointment> AppointmentList = FXCollections.observableArrayList();

        try {
        String Query = "SELECT * FROM appointments;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        // Get appointments from database
        while (RS.next()) {
            int AppointmentID = RS.getInt("Appointment_ID");
            String Title = RS.getString("Title");
            String Description = RS.getString("Description");
            String Location = RS.getString("Location");
            String Type = RS.getString("Type");
            LocalDateTime Start = RS.getTimestamp("Start").toLocalDateTime();
            LocalDateTime End = RS.getTimestamp("End").toLocalDateTime();
            LocalDateTime CreateDate = RS.getTimestamp("Create_Date").toLocalDateTime();
            String CreatedBy = RS.getString("Created_By");
            LocalDateTime LastUpdate = RS.getTimestamp("Last_Update").toLocalDateTime();
            String LastUpdatedBy = RS.getString("Last_Updated_By");
            int CustomerID = RS.getInt("Customer_ID");
            int UserID = RS.getInt("User_ID");
            int ContactID = RS.getInt("Contact_ID");

            Appointment NewAppointment = new Appointment(AppointmentID, Title, Description, Location, Type, Start, End, CreateDate, CreatedBy, LastUpdate, LastUpdatedBy, CustomerID, UserID, ContactID);
            AppointmentList.add(NewAppointment);
        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return AppointmentList;
    }

    // Get appointments for current user
    public static int GetUpcomingAppointments(int UserID) throws SQLException {
        // Get the exact time and convert to UTC time zone
        LocalDateTime CurrentTime = LocalDateTime.now();
        // Get the exact time plus 15 minutes
        LocalDateTime CurrentTimePlus15 = CurrentTime.plusMinutes(15);

        // Grab appointments from database with the logged in user's ID
        String Query = "SELECT * FROM appointments WHERE User_ID =  '" + UserID + "';";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        // Count number of appointments for current user
        int Count = 0;
        while (RS.next()) {
            LocalDateTime ApptTime = RS.getTimestamp("Start").toLocalDateTime();
            if (ApptTime.isAfter(CurrentTime) && ApptTime.isBefore(CurrentTimePlus15)) {
                Count++;
            }
        }
        return Count;
    }
}
