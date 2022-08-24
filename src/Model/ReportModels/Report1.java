package Model.ReportModels;

/**
 * This class represents the model for Report 1.
 */
public class Report1 {

    private String type;
    private int total;

    /**
     * This is the constructor for Report 1 - appointment types.
     * @param type String value of appointment type
     * @param total Int value of total number of appointments of that type
     */
    public Report1(String type, int total) {
        this.type = type;
        this.total = total;
    }

    // Get methods
    public String getType() {return type;}
    public int getTotal() {return total;}
}
