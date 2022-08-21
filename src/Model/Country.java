package Model;

import java.time.LocalDateTime;

public class Country {
    private int CountryID;
    private String Country;

    // Country constructor
    public Country(int CountryID, String Country) {
        this.CountryID = CountryID;
        this.Country = Country;
    }

    // Get methods
    public int getCountryID() {
        return CountryID;
    }
    public String getCountry() {
        return Country;
    }
}

