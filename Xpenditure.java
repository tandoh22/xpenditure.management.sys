public class Xpenditure {
    public String code;
    public double amount;
    public String date;
    public String phase;
    public String category;
    public String bankAccountId;
    public String receipt;

    public Xpenditure(String code, double amount, String date, String phase, String category, String bankAccountId, String receipt) {
        this.code = code;
        this.amount = amount;
        this.date = date;
        this.phase = phase;
        this.category = category;
        this.bankAccountId = bankAccountId;
        this.receipt = null;
    }
}