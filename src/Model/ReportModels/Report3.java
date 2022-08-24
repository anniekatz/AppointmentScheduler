package Model.ReportModels;

/**
 * This class represents the model for Report 3: Customer Appointment Totals for Future and Past.
 */
public class Report3 {

    private int customerID;
    private int totalFuture;
    private int totalPast;

    /**
     * This is the constructor for Report 3 - customer's total appointments
     * @param customerID Int value of customer ID
     * @param totalFuture Int value of total number of future appointments for customer
     * @param totalPast Int value of total number of past appointments for customer
     */
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