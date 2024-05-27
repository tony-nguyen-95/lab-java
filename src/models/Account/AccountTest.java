package models.Account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private Account account;

    @Before
    public void setup() {
        this.account = new Account();
    }

    @Test
    public void shouldSetAccountNumber() {
        String accountNumberWithChars = "a0009s";

        String accountNumberSevenLength = "1234567";

        String accountNumberValid = "123456";

        assertFalse("Account number can not contain alphabet characters",
                account.setAccountNumber(accountNumberWithChars));

        assertFalse("Account number can not be more 6 characters",
                account.setAccountNumber(accountNumberSevenLength));

        assertTrue("Account number shoule be 6 numberic characters", account.setAccountNumber(accountNumberValid));

    }

    @Test
    public void shouldIsAccountPremium() {
        double initialBalance = 10000000;
        account.setBalance(initialBalance);

        assertTrue("Account has its balance more than or equal 10,000,000 is a premium account",
                account.isPremiumAccount());
    }

}