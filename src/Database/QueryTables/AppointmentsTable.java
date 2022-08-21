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
}
