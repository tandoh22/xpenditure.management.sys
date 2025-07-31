public class BankAccount {
    public String accountId;
    public String bankName;
    public double balance;
    public LinkedList<Expendituree> expenditureCodes;

    public BankAccount(String accountId, String bankName, double balance) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.balance = balance;
        this.expenditureCodes = new LinkedList<>();
    }

    public void addExpendituree(Expendituree expenditure) {
        expenditureCodes.add(expenditure);
        balance -= expenditure.amount;
    }

    public void display() {
        System.out.println("Account ID: " + accountId);
        System.out.println("Bank: " + bankName);
        System.out.println("Balance: " + String.format("%.2f", balance));
    }
   
}