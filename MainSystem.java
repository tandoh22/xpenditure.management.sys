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
    static final String REC_FILE = "receipts.txt";

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

        if (bankAccounts.get(bankAccountId).balance < amount) {
            System.out.println(" Failed: Insufficient balance in the selected bank account.");
            return;
        }
        Expendituree expenditure = new Expendituree(code, amount, date, phase, category, bankAccountId);
        expenditureMap.put(code, expenditure);
        expenditureList.add(expenditure);
        bankAccounts.get(bankAccountId).addExpendituree(expenditure);
        minHeap.insert(bankAccounts.get(bankAccountId));
        String receipt = "Receipt for expenditure" + code + ": \n"
                + "Amount: " + String.format("%.2f", amount) + "\n"
                + "Date: " + date + "\n"
                + "Phase: " + phase + "\n"
                + "Category: " + category + "\n"
                + "Bank Account ID: " + bankAccountId;
        receiptQueue.receiptQueue.add(receipt);
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
                writer.println("--------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error saving expenditures: " + e.getMessage());
        }
    }

    public static void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(BANK_FILE)) {
            for (BankAccount account : bankAccounts.values()) {
                writer.println(account.accountId + "," + account.bankName + "," + account.balance );
                writer.println("---------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static void saveCategories() {
        try (PrintWriter writer = new PrintWriter(CAT_FILE)) {
            for (String cat : categoryManager.getCategories()) {
                writer.println(cat);
            }
        } catch (IOException e) {
            System.out.println("Error saving categories: " + e.getMessage());
        }
    }

    public static void saveReceipts() {
        try (PrintWriter writer = new PrintWriter(REC_FILE)) {
        for (String receipt : receiptQueue.getReceipt()) {
            writer.println(receipt);
            writer.println("--------------------------------------");
        }
        } catch (IOException e) {
            System.out.println("Error saving receipts: " + e.getMessage());
        }
    }

    public static void loadExpenditures() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EXP_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Expendituree expenditure = new Expendituree(parts[0], Double.parseDouble(parts[1]),
                            parts[2], parts[3], parts[4], parts[5]);
                    expenditureList.add(expenditure);
                    expenditureMap.put(expenditure.code, expenditure);
                    if (bankAccounts.containsKey(expenditure.bankAccountId)) {
                        bankAccounts.get(expenditure.bankAccountId).addExpendituree(expenditure);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No expenditures to load or file not found");
        }
    }

    public static void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BANK_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    BankAccount account = new BankAccount(parts[0], parts[1], Double.parseDouble(parts[2]));
                    bankAccounts.put(parts[0], account);
                    minHeap.insert(account);
                }
            }
        } catch (IOException e) {
            System.out.println("No accounts to load or file not found");
        }
    }

    public static void loadCategories() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAT_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                categoryManager.addCategory(line);
            }
        } catch (IOException e) {
            System.out.println("No categories to load or file not found");
        }
    }

    public static void loadReceipts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REC_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                receiptQueue.receiptQueue.add(line);
            }
        } catch (IOException e) {
            System.out.println("No receipts to load or file not found");
        }
    }

    public static void saveAll() {
        saveExpenditures();
        saveAccounts();
        saveCategories();
        saveReceipts();
        System.out.println("All data saved successfully.");
    }

    public static void loadAll() {
        loadExpenditures();
        loadAccounts();
        loadCategories();
        loadReceipts();
        System.out.println("All data loaded successfully.");
    }

    public static void  menu() {
        System.out.println("\n====== Nkwa Real Estate Expenditure Management System ======");

        System.out.println("1. Add Expenditure");
        System.out.println("2. Add Category");
        System.out.println("3. Remove Category");
        System.out.println("4. Add Bank Account");
        System.out.println("5. View All Expenditures");
        System.out.println("6. View All Categories");
        System.out.println("7. View All Bank Accounts");
        System.out.println("8. Sort Expenditures by Category");
        System.out.println("9. Sort Expenditures by Date");
        System.out.println("10. Searh by Category");
        System.out.println("11. Search by Bank Account ID");
        System.out.println("12. Search by Cost Range");
        System.out.println("13. Search by Time Range");
        System.out.println("14. Save and Exit");
        System.out.println("---------------------------------------------------------");
        System.out.println("Enter your choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1": addExpendituree(); break;
            case "2": {
                System.out.print("Enter category name: ");
                String category = scanner.nextLine();
                if (categoryManager.addCategory(category)) {
                    System.out.println("Category added successfully.");
                } else {
                    System.out.println("Category already exists.");
                }
            } break;
            case "3": {
                System.out.print("Enter category name to remove: ");
                String toRemove = scanner.nextLine();
                if (categoryManager.removeCategory(toRemove)) {
                    System.out.println("Category removed successfully.");
                } else {
                    System.out.println("Category does not exist.");
                }
            } break;
            case "4": {
                System.out.print("Enter bank account ID: ");
                String accountId = scanner.nextLine();
                System.out.print("Enter bank name: ");
                String bankName = scanner.nextLine();
                System.out.print("Enter initial balance: ");
                double balance = Double.parseDouble(scanner.nextLine());
                BankAccount account = new BankAccount(accountId, bankName, balance);
                bankAccounts.put(accountId, account);
                minHeap.insert(account);
                System.out.println("Bank account added successfully.");
            } break;
            case "5": 
                for (Expendituree expenditure : expenditureList) {
                    expenditure.display();
                }
                break;
            case "6": categoryManager.displayAll(); break;
            case "7": 
                for (BankAccount account : bankAccounts.values()) {
                    account.display();
                }
                break;
            case "8": 
                expenditureList.sort(Comparator.comparing(e -> e.category));
                System.out.println("Expenditures sorted by category:");
                for (Expendituree expenditure : expenditureList) {
                    expenditure.display();
                }
                break;
            case "9": 
                expenditureList.sort(Comparator.comparing(e -> e.date));
                System.out.println("Expenditures sorted by date:");
                for (Expendituree expenditure : expenditureList) {
                     expenditure.display();
                }
                break;
            case "10": 
                System.out.print("Enter category to search: ");
                String searchCategory = scanner.nextLine();
                boolean found = false;
                for (Expendituree expenditure : expenditureList) {
                    if (expenditure.category.equals(searchCategory)) {
                        System.out.println(expenditure);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No expenditures found for category: " + searchCategory);
                }
                break;
            case "11": 
                System.out.print("Enter bank account ID to search: ");
                String searchAccountId = scanner.nextLine();
                BankAccount account = bankAccounts.get(searchAccountId);
                if (account != null) {
                    account.display();
                } else {
                    System.out.println("No bank account found with ID: " + searchAccountId);
                }
                break;
            case "12": 
                System.out.print("Enter minimum cost: ");
                double minCost = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter maximum cost: ");
                double maxCost = Double.parseDouble(scanner.nextLine());
                found = false;
                for (Expendituree expenditure : expenditureList) {
                    if (expenditure.amount >= minCost && expenditure.amount <= maxCost) {
                        System.out.println(expenditure);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No expenditures found in the specified cost range.");
                }
                break;
            case "13": 
                System.out.print("Enter start date (YYYY-MM-DD): ");
                String startDate = scanner.nextLine();
                System.out.print("Enter end date (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();
                found = false;
                for (Expendituree expenditure : expenditureList) {
                    if (expenditure.date.compareTo(startDate) >= 0 && expenditure.date.compareTo(endDate) <= 0) {
                        System.out.println(expenditure);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No expenditures found in the specified date range.");
                }
                break;
            case "14": saveAll(); System.out.println("Exiting the system. Goodbye!"); return;
            default: System.out.println("Invalid choice. Please try again.");

        }
    }

    public static void main(String[] args) {
        loadAll();
        while (true) {
            menu();
        }
    }
}
