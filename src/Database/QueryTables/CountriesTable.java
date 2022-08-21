package Database.QueryTables;

import Database.QueryUtils;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CountriesTable {
    public static ObservableList<Country> GetCountries() {
        ObservableList<Country> CountryList = FXCollections.observableArrayList();
        try {
            String Query = "SELECT * FROM countries;";
            QueryUtils.SetPS(Query);
            PreparedStatement PS = QueryUtils.GetPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            while (RS.next()) {
                int CountryID = RS.getInt("Country_ID");
                String Country = RS.getString("Country");

                Country NewCountry = new Country(CountryID, Country);
                CountryList.add(NewCountry);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CountryList;
    }
}

