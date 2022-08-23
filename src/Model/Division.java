package Model;

// Model to represent a division
public class Division {

    // Division variables
    private int divisionID;
    private String divisionName;
    private int countryID;

    // Division constructor
    public Division(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    // Get division information methods
    public int getDivisionID() {
        return divisionID;
    }
    public String getDivisionName() {
        return divisionName;
    }
    public int getCountryID() {
        return countryID;
    }
}

