package models.Transaction;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.UUID;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idTransaction;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status = false;
    private ETransactionType typeTransaction;

    public Transaction(String accountNumber, double amount, String time, boolean status, ETransactionType type) {
        this.idTransaction = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.typeTransaction = type;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public ETransactionType getTypeTransaction() {
        return typeTransaction;
    }

    // @Override
    // public String toString() {
    // return String.format("%-20s%15s%30s%40s%10s",
    // getAccountNumber(),
    // NumberFormat.getNumberInstance().format(getAmount()),
    // getTime(),
    // getIdTransaction(),
    // getTypeTransaction());
    // }

    @Override
    public String toString() {
        String formattedAmount = NumberFormat.getNumberInstance().format(getAmount());

        return String.format("[GD] %-8s | %-9s | %15s ₫ | %24s",
                getAccountNumber(),
                getTypeTransaction(),
                formattedAmount,
                getTime());
    }
}