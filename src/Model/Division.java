package Model;

/**
 * This class represents the model for a first level division.
 */
public class Division {

    // Division variables
    private int divisionID;
    private String divisionName;
    private int countryID;

    /**
     * This is the constructor for a division.
     * @param divisionID Int value of division ID
     * @param divisionName String value of division name
     * @param countryID Int value of division's country ID
     */
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

