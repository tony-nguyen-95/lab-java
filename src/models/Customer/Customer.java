package models.Customer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import DAOs.AccountDao;
import DAOs.CustomerDao;
import models.Account.Account;
import models.Account.EAccountType;
import models.Account.LoanAccount;
import models.Account.SavingAccount;
import models.Bank.Bank;

public class Customer extends User {
    @JsonProperty("accounts")
    protected List<Account> accounts = new ArrayList<>();

    public Customer() {
    }

    public List<Account> getAccounts() {
        List<Account> allAccounts = AccountDao.getList();
        List<Account> result = new ArrayList<>();
        allAccounts.stream()
                .filter(account -> account.getCustomerId().equals(this.getCustomerId()))
                .forEach(account -> {
                    if (account.getAccountType() == EAccountType.SAVING) {
                        SavingAccount savingAccount = new SavingAccount();
                        savingAccount.setAccountNumber(account.getAccountNumber());
                        savingAccount.setBalance(account.getBalance());
                        savingAccount.setCustomerId(account.getCustomerId());
                        result.add(savingAccount);
                    } else if (account.getAccountType() == EAccountType.LOAN) {
                        LoanAccount loanAccount = new LoanAccount();
                        loanAccount.setAccountNumber(account.getAccountNumber());
                        loanAccount.setBalance(account.getBalance());
                        loanAccount.setCustomerId(account.getCustomerId());
                        result.add(loanAccount);
                    }
                });
        return result;
    }

    public boolean isPremiumCustomer() {
        return getAccounts().stream().anyMatch(account -> account.isPremiumAccount());
    }

    public boolean isAccountExist(String accountId) {
        List<DigitalCustomer> customers = CustomerDao.getList();
        return customers.stream()
                .anyMatch(customer -> customer.getAccounts().stream()
                        .anyMatch(account -> account.getAccountNumber().equals(accountId)));
    }

    public Account addAccount(Account newAccount) {
        if (isAccountExist(newAccount.getAccountNumber())) {
            return null;
        }

        accounts.add(newAccount);
        return newAccount;

    }

    public double getBalance() {
        return getAccounts().stream().mapToDouble(account -> account.getBalance()).sum();
    }

    public void displayInformation() {
        String normalOrPremium = isPremiumCustomer() ? "Premium" : "Normal";

        // Print customer info
        System.out.println(this.getCustomerId() + " | " + "\t\t" + this.getName() + " | " + normalOrPremium + " | "
                + NumberFormat.getNumberInstance().format(getBalance()) + "đ");

        // Print accounts list
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + "\t" + accounts.get(i));
        }

    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst()
                .orElse(null);
    }

}
