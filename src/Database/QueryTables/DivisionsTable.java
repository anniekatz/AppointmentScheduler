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
    public static ObservableList<Division> GetDivisions() {
        ObservableList<Division> DivisionList = FXCollections.observableArrayList();
try {
        String Query = "SELECT * FROM first_level_divisions;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            int DivisionID = RS.getInt("Division_ID");
            String DivisionName = RS.getString("Division");
            int CountryID = RS.getInt("Country_ID");

            // populate entire division list
            Division NewDivision = new Division(DivisionID, DivisionName, CountryID);
            DivisionList.add(NewDivision);


        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return DivisionList;
    }
}