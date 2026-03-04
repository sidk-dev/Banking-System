package models;

public final class Account {
    private static long balance = 0;
    private Account() {}

    public static long getBalance() {
        return balance;
    }
    public static void addBalance(long balance) {
        Account.balance += balance;
    }
}
