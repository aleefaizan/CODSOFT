package org.example.atmmachine;

public class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > 0 && amount <= userAccount.getBalance()) {
            userAccount.setBalance(userAccount.getBalance() - amount);
            System.out.println("Amount withdrawn successfully.");
        } else {
            throw new InsufficientFundsException();
        }
    }
    public void deposit(double amount) {
        if (amount > 0) {
            userAccount.setBalance(userAccount.getBalance() + amount);
            System.out.println("Amount added successfully.");
        } else {
            System.out.println("Please insert a valid amount.");
        }
    }
    public double checkBalance() {
        return userAccount.getBalance();
    }
}
