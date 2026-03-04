package models;

public final class Account {
    private static double balance = 0;
    private Account() {}

    public static double getBalance() {
        return balance;
    }
    public static void setBalance(double balance) {
        Account.balance = balance;
    }
    public static void addBalance(double balance) {
        Account.balance += balance;
    }
    public static void deductBalance(double balance) {
        Account.balance -= balance;
    }
}
