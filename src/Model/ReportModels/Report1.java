package Model.ReportModels;

// Model class for Report 1
public class Report1 {

    // Report 1 variables
    private String type;
    private int total;

    // Report 1 constructor
    public Report1(String type, int total) {
        this.type = type;
        this.total = total;
    }

    // Get Report 1 information methods
    public String getType() {
        return type;
    }
    public int getTotal() {
        return total;
    }
}
