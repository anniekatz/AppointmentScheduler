package Database.QueryTables;

import Database.QueryUtils;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DivisionsTable {
    public static ObservableList<Division> GetDivisions() throws SQLException, SQLException {
        ObservableList<Division> DivisionList = FXCollections.observableArrayList();

        String Query = "SELECT * FROM divisions;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            int DivisionID = RS.getInt("Division_ID");
            String DivisionName = RS.getString("Division");
            LocalDateTime CreateDate = RS.getTimestamp("Create_Date").toLocalDateTime();
            String CreatedBy = RS.getString("Created_By");
            LocalDateTime LastUpdate = RS.getTimestamp("Last_Update").toLocalDateTime();
            String LastUpdatedBy = RS.getString("Last_Updated_By");
            int CountryID = RS.getInt("Country_ID");

            Division NewDivision = new Division(DivisionID, DivisionName, CreateDate, CreatedBy, LastUpdate, LastUpdatedBy, CountryID);
            DivisionList.add(NewDivision);
        }
        return DivisionList;
    }
}