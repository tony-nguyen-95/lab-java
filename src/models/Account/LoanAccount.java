package models.Account;

import java.text.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import DAOs.AccountDao;
import interfaces.IReportService;
import interfaces.IWithdrawService;
import models.Transaction.ETransactionType;
import models.Transaction.Transaction;
import utils.GetCurrentDateTime;
import utils.PrintDividerUtil;

public class LoanAccount extends Account implements IReportService, IWithdrawService {
    public final static double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    public final static double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    public final static double LOAN_ACCOUNT_MAX_WITHDRAW = 100000000;
    public final static double LOAN_ACCOUNT_MAX_BALANCE = 150000000;
    public final static double LOAN_ACCOUNT_MIN_BALANCE = 50000;
    public final static double LOAN_ACCOUNT_WITHDRAW_MULTIPLES = 10000;

    @JsonIgnore
    private double feeAmount;

    public LoanAccount() {
        super.setAccountType(EAccountType.LOAN);
    }

    private String getTitle() {
        return "BIEN LAI GIAO DICH " + getAccountType();
    }

    private void setFeeAmount(double withdrawAmount) {
        double feeRate = isPremiumAccount() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;

        this.feeAmount = feeRate * withdrawAmount;
    }

    @Override
    public void log(double amount, String timeWithdraw, ETransactionType type, String receiveAccount) {

        PrintDividerUtil.printLine();
        System.out.printf("%30s%n", getTitle());
        System.out.printf("NGAY G/D: %28s%n", timeWithdraw);
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", NumberFormat.getNumberInstance().format(amount) + "đ");
        System.out.printf("SO DU: %31s%n", NumberFormat.getNumberInstance().format(getBalance()) + "đ");
        System.out.printf("PHI + VAT: %27s%n", NumberFormat.getNumberInstance().format(feeAmount) + "đ");
        PrintDividerUtil.printLine();

    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount > LOAN_ACCOUNT_MAX_BALANCE || amount < 0 || (amount % LOAN_ACCOUNT_WITHDRAW_MULTIPLES != 0)) {
            System.out.println(
                    "Số tiền rút không hợp lệ, số tiền rút phải trong khoảng 0đ đến 100,000,000đ và là bội số của 10,000đ");
            return false;
        }

        setFeeAmount(amount);
        double leftBalance = getBalance() - (amount + this.feeAmount);

        if (leftBalance < LOAN_ACCOUNT_MIN_BALANCE) {
            System.out.println("Số dư còn lại không đủ, số dư còn lại không nhỏ hơn 50,000đ");
            return false;
        }

        return true;
    }

    @Override
    public boolean withdraw(double amount) {

        if (isAccepted(amount)) {

            double newBalance = this.getBalance() - (amount + this.feeAmount);
            setBalance(newBalance);

            String timeWithdraw = GetCurrentDateTime.getDateTimeFormatted();

            this.createTransaction(amount, timeWithdraw, true,
                    ETransactionType.WITHDRAW);

            System.out.println("Rút tiền thành công!");
            log(amount, timeWithdraw, ETransactionType.WITHDRAW, "");
            AccountDao.updateSingle((Account) this);

            return true;
        }

        System.out.println("Rút tiền không thành công!");
        return false;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

}
