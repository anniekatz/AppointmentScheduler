package Database.QueryTables;

import Database.QueryUtils;
import Model.Appointment;
import Model.ReportModels.Report1;
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
                int CustomerID = RS.getInt("Customer_ID");
                int UserID = RS.getInt("User_ID");
                int ContactID = RS.getInt("Contact_ID");

                Appointment NewAppointment = new Appointment(AppointmentID, Title, Description, Location, Type, Start, End, CustomerID, UserID, ContactID);
                AppointmentList.add(NewAppointment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return AppointmentList;
    }

    public static void AddAppointment(String Title, String Description, String Location, String Type, String Start, String End, int CustomerID, int UserID, int ContactID) throws SQLException {
        String Query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES ('" + Title + "', '" + Description + "', '" + Location + "', '" + Type + "', '" + Start + "', '" + End + "', '" + CustomerID + "', '" + UserID + "', '" + ContactID + "');";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
    }

    public static void UpdateAppointment(int AppointmentID, String Title, String Description, String Location, String Type, String Start, String End, int CustomerID, int UserID, int ContactID) throws SQLException {
        // Update Appointment in appointments table where Appointment_ID = AppointmentID
        String Query = "UPDATE appointments SET Title = '" + Title + "', Description = '" + Description + "', Location = '" + Location + "', Type = '" + Type + "', Start = '" + Start + "', End = '" + End + "', Customer_ID = '" + CustomerID + "', User_ID = '" + UserID + "', Contact_ID = '" + ContactID + "' WHERE Appointment_ID = '" + AppointmentID + "';";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
    }

    public static void DeleteAppointment(int AppointmentID) {
        try {
            String Query = "DELETE FROM appointments WHERE Appointment_ID = ?;";
            QueryUtils.SetPS(Query);
            PreparedStatement PS = QueryUtils.GetPS();
            PS.setInt(1, AppointmentID);
            PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<Report1> GetReport1(String Month) {
        ObservableList<Report1> Report1List = FXCollections.observableArrayList();
        try {
            String Query = "SELECT Type, COUNT(*) AS Total FROM appointments WHERE MONTHNAME(Start) = '" + Month + "' GROUP BY Type;";
            if (Month.equals("All Months")) {
                Query = "SELECT Type, COUNT(*) AS Total FROM appointments GROUP BY Type;";
            }
            QueryUtils.SetPS(Query);
            PreparedStatement PS = QueryUtils.GetPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            while (RS.next()) {
                String Type = RS.getString("Type");
                int Total = RS.getInt("Total");
                Report1 NewReport = new Report1(Type, Total);
                Report1List.add(NewReport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Report1List;

    }



}
