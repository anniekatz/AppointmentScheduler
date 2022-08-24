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

    /**
     * This method gets customer ID
     * @return Int value of customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method gets total future appointments
     * @return Int value of total number of future appointments for customer
     */
    public int getTotalFuture() {
        return totalFuture;
    }

    /**
     * This method gets total past appointments
     * @return Int value of total number of past appointments for customer
     */
    public int getTotalPast() {
        return totalPast;
    }
}