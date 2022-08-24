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

    /**
     * This method gets country ID
     * @return int country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method gets country name
     * @return String value of country name
     */
    public String getCountry() {
        return countryName;
    }
}

