package Model.ReportModels;


public class Report3 {
    private int CustomerID;
    private int TotalFuture;
    private int TotalPast;


    public Report3(int CustomerID, int TotalFuture, int TotalPast) {
        this.CustomerID = CustomerID;
        this.TotalFuture = TotalFuture;
        this.TotalPast = TotalPast;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getTotalFuture() {
        return TotalFuture;
    }
    public int getTotalPast() {
        return TotalPast;
    }
}