package Database.QueryTables;

import Database.QueryUtils;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to query the first_level_divisions table in database.
 */
public class DivisionsTable {

    /**
     * This method is used to get all divisions from the database.
     * @return returns Observable List containing divisions and their information.
     */
    public static ObservableList<Division> getDivisions() {
        // Initialize empty Observable List
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        try {
            // Create query to get all divisions
            String query = "SELECT * FROM first_level_divisions;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through results to get data for all divisions
            while (RS.next()) {
                int divisionID = RS.getInt("Division_ID");
                String divisionName = RS.getString("Division");
                int countryID = RS.getInt("Country_ID");
            // Populate entire division list
            Division newDivision = new Division(divisionID, divisionName, countryID);
            divisionList.add(newDivision);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return divisionList;
    }
}