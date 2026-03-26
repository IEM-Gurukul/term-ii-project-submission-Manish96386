import java.util.*;


abstract class Account {
    protected int accountNumber;
    protected String name;
    protected double balance;
    protected List<String> transactionHistory = new ArrayList<>();

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History:");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }
}