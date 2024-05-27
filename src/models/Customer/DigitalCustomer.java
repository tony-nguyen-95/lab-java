package models.Customer;

import java.text.NumberFormat;

import models.Account.Account;

public class DigitalCustomer extends Customer {

    public void updateTypeAccount(Account oldAccount, Account newAccount) {
        int index = accounts.indexOf(oldAccount);
        if (index != -1) {
            accounts.set(index, newAccount);
        }
    }

    @Override
    public void displayInformation() {

        String normalOrPremium = isPremiumCustomer() ? "Premium" : "Normal";

        // Print customer info
        System.out.printf("%-15s | %-15s | %-10s | %sđ%n",
                this.getCustomerId(),
                this.getName(),
                normalOrPremium,
                NumberFormat.getNumberInstance().format(getBalance()));

        // Print accounts list
        int i = 0;
        for (Account account : this.getAccounts()) {
            System.out.printf("%-5d %-9s | %10s | %28sđ%n", (i + 1),
                    account.getAccountNumber(),
                    account.getAccountType(),
                    NumberFormat.getNumberInstance().format(account.getBalance()));
            i++;
        }
        System.out.println();
    }

    public void displayTransactionInformation() {
        displayInformation();
        System.out.println();
        getAccounts().forEach(account -> account.displayTransactionsList());
    }

}
