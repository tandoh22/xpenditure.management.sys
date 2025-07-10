import java.io.*;
import java.util.*;

public class MainSystem {
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, Expendituree> expenditureMap = new HashMap<>();
    static LinkedList<Expendituree> expenditureList = new LinkedList<>();
    static HashMap<String, BankAccount> bankAccounts = new HashMap<>();
    static Category categoryManager = new Category();
    static Receipt receiptQueue = new Receipt();
    static Review reviewStack = new Review();
    static MinHeap minHeap = new MinHeap(20);

    static final String EXP_FILE = "expenditures.txt";
    static final String BANK_FILE = "accounts.txt";
    static final String CAT_FILE = "categories.txt";

    public static void addExpendituree() {
        System.out.print("Enter code: ");
        String code = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter phase: ");
        String phase = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter bank account ID: ");
        String bankAccountId = scanner.nextLine();

        Expendituree expenditure = new Expendituree(code, amount, date, phase, category, bankAccountId);
        expenditureMap.put(code, expenditure);
        expenditureList.add(expenditure);
        bankAccounts.get(bankAccountId).addExpendituree(expenditure);
        minHeap.insert(bankAccounts.get(bankAccountId));
        System.out.println("Expenditure added successfully.\n");
        
        if (bankAccounts.containsKey(bankAccountId)) {
            bankAccounts.get(bankAccountId).addExpendituree(expenditure);
        } else {
            System.out.println("Bank account not found.");
        }

        if (!categoryManager.exists(category)) {
            System.out.println("Category does not exist. Add category first.");
        }
    }

    public static void saveExpenditures() {
        try (PrintWriter writer = new PrintWriter(EXP_FILE)) {
            for (Expendituree expenditure : expenditureList) {
                writer.println(expenditure.code + "," + expenditure.amount + "," + expenditure.date + "," +
                        expenditure.phase + "," + expenditure.category + "," + expenditure.bankAccountId);
            }
        } catch (IOException e) {
            System.out.println("Error saving expenditures: " + e.getMessage());
        }
    }

    public static void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(BANK_FILE)) {
            for (BankAccount account : bankAccounts.values()) {
                writer.println(account.accountId + "," + account.bankName + "," + account.balance);
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static void saveCategories() {
        try (PrintWriter writer = new PrintWriter(CAT_FILE)) {
            for (String cat : categoryManager.categories()) {
                writer.println(cat);
            }
        } catch (IOException e) {
            System.out.println("Error saving categories: " + e.getMessage());
        }
    }
}
