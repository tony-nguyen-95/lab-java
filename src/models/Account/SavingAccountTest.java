package models.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import DAOs.AccountDao;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import models.Bank.DigitalBank;
import models.Customer.DigitalCustomer;
import mockit.Mock;

@RunWith(JMockit.class)
public class SavingAccountTest {

	private SavingAccount savingAccount;

	@Before
	public void setup() {
		this.savingAccount = new SavingAccount();
	}

	@Test
	public void shouldIsAccepted() {
		double amount = 109000; // Set an example amount for testing

		boolean actualValue = savingAccount.isAccepted(amount);

		assertFalse("Withdrawal amount should be a multiples of 10,000vnd", actualValue);
	}

	@Test
	public void shouldWithdraw() {
		double initialBalance = 150000;
		double withdrawalAmount = 110000;

		savingAccount.setBalance(initialBalance);

		boolean withdrawalResult = savingAccount.withdraw(withdrawalAmount);

		assertFalse("Withdrawal from saving account should be fail, the balace must be not less than 50,000vnd",
				withdrawalResult);
	}

	@Test
	public void testTransfers() {
		// Get sender accountID: 111111;
		String testSenderAccountNUmber = "111111";
		List<Account> listAccount = AccountDao.getList();
		DigitalCustomer dummyCustomer = new DigitalCustomer();

		SavingAccount senderAccount = (SavingAccount) dummyCustomer.getAccountByAccountNumber(listAccount,
				testSenderAccountNUmber);

		// Get receiveAccount ID: 222222;
		String testReceiveAccountNumber = "222222";
		SavingAccount receiveAccount = (SavingAccount) dummyCustomer.getAccountByAccountNumber(listAccount,
				testReceiveAccountNumber);

		// Amount to transfer
		double amountToTransfer = 500000;

		boolean result = senderAccount.transfers(amountToTransfer, receiveAccount);

		// Verify that the expected result is true
		assertTrue(result);
	}
}
