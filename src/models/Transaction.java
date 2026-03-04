package models;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {
    public enum TYPE {
        DEPOSIT, WITHDRAW
    }

    private final UUID id;
    private final UUID userId;
    private final TYPE type;
    private final double amount;
    private ZonedDateTime timestamp;

    public Transaction(UUID id, UUID userId, double amount, Transaction.TYPE type, ZonedDateTime timestamp) {
        this.amount = amount;
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "Transaction | ID: %s | User: %s | Type: %s | Amount: %.2f | Time: %s",
                id, userId, type, amount, timestamp
        );
    }
}
