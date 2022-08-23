package Database.QueryTables;

import Database.QueryUtils;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class to query countries table in database
public class CountriesTable {
    // Get full countries table
    public static ObservableList<Country> getCountries() {
        // Initialize empty Observable List
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            // Create query to get all countries
            String query = "SELECT * FROM countries;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through records to get data for all countries
            while (RS.next()) {
                int countryID = RS.getInt("Country_ID");
                String countryName = RS.getString("Country");
                // Add each country to Observable List
                Country newCountry = new Country(countryID, countryName);
                countryList.add(newCountry);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countryList;
    }
}

