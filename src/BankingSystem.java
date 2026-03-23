
abstract class BankAccount {
    protected String name;
    protected int accNo;
    protected double balance;

    BankAccount(String name, int accNo, double balance) {
        this.name = name;
        this.accNo = accNo;
        this.balance = balance;
    }

 
    abstract void calculateInterest();


    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient Balance!");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Account No: " + accNo);
        System.out.println("Balance: " + balance);
    }
}


class SavingsAccount extends BankAccount {
    SavingsAccount(String name, int accNo, double balance) {
        super(name, accNo, balance);
    }

    void calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings Interest: " + interest);
    }
}


class CurrentAccount extends BankAccount {
    CurrentAccount(String name, int accNo, double balance) {
        super(name, accNo, balance);
    }

    void calculateInterest() {
        System.out.println("No interest for Current Account");
    }
}
public class BankingSystem {
    public static void main(String[] args) {
        try {
            SavingsAccount s = new SavingsAccount("Manish", 101, 5000);
            CurrentAccount c = new CurrentAccount("Rahul", 102, 3000);

            System.out.println("---- Savings Account ----");
            s.display();
            s.deposit(1000);
            s.withdraw(2000);
            s.calculateInterest();

            System.out.println("\n---- Current Account ----");
            c.display();
            c.deposit(500);
            c.withdraw(4000); // Will throw exception
            c.calculateInterest();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}