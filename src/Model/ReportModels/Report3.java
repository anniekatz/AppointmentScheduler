package Model.ReportModels;

// Model class for Report 3
public class Report3 {

    // Report 3 variables
    private int customerID;
    private int totalFuture;
    private int totalPast;

    // Report3 constructor
    public Report3(int customerID, int totalFuture, int totalPast) {
        this.customerID = customerID;
        this.totalFuture = totalFuture;
        this.totalPast = totalPast;
    }

    // Get Report 3 information methods
    public int getCustomerID() {
        return customerID;
    }
    public int getTotalFuture() {
        return totalFuture;
    }
    public int getTotalPast() {
        return totalPast;
    }
}