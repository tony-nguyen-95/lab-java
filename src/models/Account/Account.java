package models.Account;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import DAOs.AccountDao;
import DAOs.TransactionDao;
import models.Transaction.ETransactionType;
import models.Transaction.Transaction;
import utils.GetCurrentDateTime;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("accountType")
    private EAccountType accountType;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("customerId")
    private String customerId;

    @JsonIgnore
    private final double LIMIT_PREMIUM_BALANCE = 10000000;

    public Account() {
    }

    public List<Transaction> getTransactions() {
        List<Transaction> listTransactions = TransactionDao.getList();
        return listTransactions.stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .toList();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public EAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(EAccountType accountType) {
        this.accountType = accountType;
    }

    public boolean setAccountNumber(String accountNumber) {
        if (accountNumber.toCharArray().length != 6 || !accountNumber.matches("\\d+")) {
            return false;
        }
        this.accountNumber = accountNumber;

        return true;
    }

    public double getBalance() {
        return balance;
    }

    public boolean setBalance(double balance) {
        if (balance < 50000)
            return false;

        this.balance = balance;

        return true;
    }

    public boolean isPremiumAccount() {
        return balance >= LIMIT_PREMIUM_BALANCE;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void displayTransactionsList() {
        getTransactions().forEach(transaction -> System.out.println(transaction));
    }

    public void createTransaction(double amount, String time, boolean status, ETransactionType type) {

        List<Transaction> listTransaction = TransactionDao.getList();

        Transaction newTransaction = new Transaction(getAccountNumber(), amount, time, status, type);

        listTransaction.add(newTransaction);

        TransactionDao.save(listTransaction);
    }

    public void deposit(double amount) {
        double newBalance = this.getBalance() + amount;
        setBalance(newBalance);

        String timeTransfer = GetCurrentDateTime.getDateTimeFormatted();

        this.createTransaction(amount, timeTransfer, true,
                ETransactionType.DEPOSIT);

        AccountDao.updateSingle((Account) this);

    }

    public void input(Scanner scanner) {
        List<Account> listAccount = AccountDao.getList();
        Account foundAccount = new Account();

        while (true) {
            System.out.println("Nhập số tài khoản nhận tiền:");
            String depositAccountNumber = scanner.next();

            if (depositAccountNumber.equals(getAccountNumber())) {
                System.out.println("Tài khoản nhận không thể trùng với tài khoản nhận, vui lòng thử lại!");
                continue;
            }

            foundAccount = listAccount.stream()
                    .filter(account -> account.getAccountNumber().equals(depositAccountNumber)).findFirst()
                    .orElse(null);

            if (foundAccount == null) {
                System.out.println("Không tìm thấy tài khoản, vui lòng thử lại!");
                continue;
            }

            break;
        }

        while (true) {
            try {
                System.out.println("Nhập số tiền chuyển:");
                Double amount = scanner.nextDouble();

                String timeDeposite = GetCurrentDateTime.getDateTimeFormatted();

                foundAccount.createTransaction(amount, timeDeposite, true, ETransactionType.DEPOSIT);
                foundAccount.setBalance(foundAccount.getBalance() + amount);
                AccountDao.updateSingle(foundAccount);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Số tiền chuyển phải là số, vui lòng thử lại!");
                scanner.nextLine();
                continue;
            }
        }

    }

    @Override
    public String toString() {
        return accountNumber + " |" + "\t\t\t" + NumberFormat.getNumberInstance().format(balance) + "đ";
    }

}
