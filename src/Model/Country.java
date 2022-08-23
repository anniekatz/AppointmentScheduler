package Model;

// Model to represent a country
public class Country {

    private int countryID;
    private String countryName;

    // Country constructor
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    // Get country information methods
    public int getCountryID() {
        return countryID;
    }
    public String getCountry() {
        return countryName;
    }
}

