package Database.QueryTables;

import Database.QueryUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentsTable {

    // Get appointments for current user
    public static int GetAppointments(int UserID) throws SQLException {
        // Get the exact time and convert to UTC time zone
        LocalDateTime CurrentTime = LocalDateTime.now();
        // Get the exact time plus 15 minutes
        LocalDateTime CurrentTimePlus15 = CurrentTime.plusMinutes(15);

        String Query = "SELECT * FROM appointments WHERE User_ID =  '" + UserID + "' AND ;";

        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        // Count number of appointments for current user
        int Count = 0;
        while (RS.next()) {
            Count++;

        }
        return Count;
    }
}
