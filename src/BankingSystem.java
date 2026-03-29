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

class SavingsAccount extends Account {
       
   public SavingsAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }


     @Override
    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new RuntimeException("Invalid deposit amount!");
            }
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Amount Deposited Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
      public void withdraw(double amount) {
        try {
            if (amount > balance) {
                throw new RuntimeException("Insufficient Balance!");
            }
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Amount Withdrawn Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

    


class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();
    private int accCounter = 1001;

    public void createAccount(String name, double initialBalance) {
        Account acc = new SavingsAccount(accCounter, name, initialBalance);
        accounts.put(accCounter, acc);
        System.out.println("Account Created Successfully!");
        System.out.println("Your Account Number: " + accCounter);
        accCounter++;
    }

    public Account getAccount(int accNo) {
        if (!accounts.containsKey(accNo)) {
            throw new RuntimeException("Account not found!");
        }
        return accounts.get(accNo);
    }

    public void transfer(int fromAcc, int toAcc, double amount) {
        try {
            Account sender = getAccount(fromAcc);
            Account receiver = getAccount(toAcc);

            if (sender.balance < amount) {
                throw new RuntimeException("Insufficient Balance for Transfer!");
            }

            sender.balance -= amount;
            receiver.balance += amount;

            sender.transactionHistory.add("Transferred " + amount + " to " + toAcc);
            receiver.transactionHistory.add("Received " + amount + " from " + fromAcc);

            System.out.println("Transfer Successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n1.Create Account\n2.Deposit\n3.Withdraw\n4.Check Balance\n5.Transfer\n6.Exit");
            int choice = sc.nextInt();

                            try {
        
                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Initial Balance: ");
                        double bal = sc.nextDouble();
                        bank.createAccount(name, bal);
                        break;

                    case 2:
                        System.out.print("Enter Account No: ");
                        int accNo = sc.nextInt();
                        System.out.print("Enter Amount: ");
                        double amt = sc.nextDouble();
                        bank.getAccount(accNo).deposit(amt);
                        break;

                    case 3:
                        System.out.print("Enter Account No: ");
                        accNo = sc.nextInt();
                        System.out.print("Enter Amount: ");
                        amt = sc.nextDouble();
                        bank.getAccount(accNo).withdraw(amt);
                        break;

                    case 4:
                        System.out.print("Enter Account No: ");
                        accNo = sc.nextInt();
                        bank.getAccount(accNo).checkBalance();
                        break;
                        case 5:
                        System.out.print("From Account: ");
                        int from = sc.nextInt();
                        System.out.print("To Account: ");
                        int to = sc.nextInt();
                        System.out.print("Amount: ");
                        amt = sc.nextDouble();
                        bank.transfer(from, to, amt);
                        break;

                    case 6:
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

