package Model;

/**
 * This class represents the model for a country.
 */
public class Country {

    private int countryID;
    private String countryName;

    /**
     * This is the constructor for a country.
     * @param countryID Int value of country ID
     * @param countryName String value of country name
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    // Get country methods
    public int getCountryID() {
        return countryID;
    }
    public String getCountry() {
        return countryName;
    }
}

