package Model.ReportModels;

public class Report1 {
    private String Type;
    private int Total;

    public Report1(String Type, int Total) {
        this.Type = Type;
        this.Total = Total;
    }

    public String getType() {
        return Type;
    }

    public int getTotal() {
        return Total;
    }
}
