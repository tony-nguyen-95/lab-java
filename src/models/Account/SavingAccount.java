package models.Account;

import java.text.NumberFormat;
import DAOs.AccountDao;
import interfaces.IReportService;
import interfaces.IWithdrawService;
import models.Transaction.ETransactionType;
import utils.GetCurrentDateTime;
import utils.PrintDividerUtil;

public class SavingAccount extends Account implements IReportService, IWithdrawService {
    private final static double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    public final static double SAVINGS_ACCOUNT_MIN_BALANCE = 50000;
    public final static double SAVINGS_ACCOUNT_WITHDRAW_MULTIPLES = 10000;
    public final static double SAVINGS_ACCOUNT_MIN_WITHDRAW_AMOUNT = 50000;

    public SavingAccount() {
        super.setAccountType(EAccountType.SAVING);
    }

    private String getTitle() {
        return "BIEN LAI GIAO DICH " + getAccountType();
    }

    @Override
    public void log(double amount, String timeWithdraw, ETransactionType type, String receiveAccount) {

        PrintDividerUtil.printLine();
        System.out.printf("%30s%n", getTitle());
        System.out.printf("NGAY G/D: %28s%n", timeWithdraw);
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());

        if (type.equals(ETransactionType.TRANSFER)) {
            System.out.printf("SO TK NHAN: %26s%n", receiveAccount);
            System.out.printf("SO TIEN CHUYEN: %22s%n", NumberFormat.getNumberInstance().format(amount) + "đ");
        } else if (type.equals(ETransactionType.WITHDRAW)) {
            System.out.printf("SO TIEN RUT: %25s%n", NumberFormat.getNumberInstance().format(amount) + "đ");
        }

        System.out.printf("SO DU: %31s%n", NumberFormat.getNumberInstance().format(getBalance()) + "đ");
        System.out.printf("PHI + VAT: %27s%n", NumberFormat.getNumberInstance().format(0) + "đ");
        PrintDividerUtil.printLine();

    }

    @Override
    public boolean isAccepted(double amount) {
        if ((!isPremiumAccount() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW) || amount < 0
                || (amount % SAVINGS_ACCOUNT_WITHDRAW_MULTIPLES != 0)) {
            System.out.println(
                    "Số tiền rút không hợp lệ, số tiền rút phải trong khoảng 0đ đến 5,000,000đ đối với tài khoản thường và là bội số của 10,000đ");
            return false;
        }

        double leftBalance = getBalance() - amount;

        if (leftBalance < SAVINGS_ACCOUNT_MIN_BALANCE) {
            System.out.println("Số dư còn lại không đủ, số dư còn lại không nhỏ hơn 50,000đ");
            return false;
        }

        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            double newBalance = this.getBalance() - amount;
            setBalance(newBalance);

            String timeWithdraw = GetCurrentDateTime.getDateTimeFormatted();

            this.createTransaction(amount * -1, timeWithdraw, true,
                    ETransactionType.WITHDRAW);

            System.out.println("Rút tiền thành công!");
            log(amount, timeWithdraw, ETransactionType.WITHDRAW, "");
            AccountDao.updateSingle((Account) this);

            return true;
        }

        System.out.println("Rút tiền không thành công!");
        return false;
    }

    public boolean transfers(double amount, Account receiveAccount) {
        if (isAccepted(amount)) {
            double newBalance = this.getBalance() - amount;
            setBalance(newBalance);

            String timeTransfer = GetCurrentDateTime.getDateTimeFormatted();

            this.createTransaction(amount * -1, timeTransfer, true,
                    ETransactionType.TRANSFER);

            AccountDao.updateSingle((Account) this);
            receiveAccount.deposit(amount);

            System.out.println("Chuyển tiền thành công!");
            log(amount, timeTransfer, ETransactionType.TRANSFER, receiveAccount.getAccountNumber());

            return true;
        }

        System.out.println("Chuyển tiền thất bại!");
        return false;
    }

}