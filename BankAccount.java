import java.util.LinkedList;
public class BankAccount {
    public String accountId;
    public String bankName;
    public double balance;
    public LinkedList<Xpenditure> expenditureCodes;

    public BankAccount(String accountId, String bankName, double balance) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.balance = balance;
        this.expenditureCodes = new ArrayList<>();
    }

}