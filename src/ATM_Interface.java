import java.io.*;
import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private int password;

    // Constructor to initialize account balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to set up or validate password
    public void setupPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<<=========== Welcome to the ATM Interface =============>>");
        System.out.println(" ");
        System.out.println("Did you created PIN code of your ATM, If yes then type 'yes' otherwise 'no' := ");
        boolean created = scanner.next().equalsIgnoreCase("yes");
        if (password == 0 && !created) { // If no password has been created yet
            System.out.print("Please create by entering new 4-digit password: ");
            password = scanner.nextInt();
            System.out.println("Password created successfully!");
            logTransaction("Password created.");
        } else {
            System.out.println("Password already set.");
        }
    }

    // Method to validate the entered password
    public boolean validatePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your 4-digit password: ");
        int enteredPassword = scanner.nextInt();
        if (enteredPassword == password) {
            return true;
        } else {
            System.out.println("Incorrect password. Access denied.");
            return false;
        }
    }

    // Method to deposit amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
            logTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw amount
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: $" + amount);
            logTransaction("Withdrawn: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }

    // Method to log transactions to a file
    private void logTransaction(String transaction) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            writer.write(transaction + "\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to connect ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display ATM options
    public void displayMenu() {
        System.out.println("\n<<========= ATM Menu =========>>");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transactions");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to process user choices
    public void processUserChoice() {
        Scanner scanner = new Scanner(System.in);
        account.setupPassword(); // Ensure password is set up

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 4 && !account.validatePassword()) {
                continue; // If password is invalid, skip to the next iteration
            }

            switch (choice) {
                case 1: // Withdraw
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2: // Deposit
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3: // Check Balance
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 4: // View Transactions
                    viewTransactions();
                    break;
                case 5: // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to display transaction history
    private void viewTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            System.out.println("\n<<========= Transaction History ==========>>");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions found.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
}

// Main class
public class ATM_Interface {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(500.00);

        // Create an ATM and connect it to the bank account
        ATM atm = new ATM(account);

        // Start the ATM interface
        atm.processUserChoice();
    }
}

