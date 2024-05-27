package models.Customer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptions.CustomerIdNotValidException;
import models.Account.SavingAccount;
import models.Bank.Bank;

public class CustomerTest {

	private Customer customer;

	@Before
	public void setup() {
		this.customer = new Customer();
	}

	@Test
	public void shouldNotIsPremiumCustomer() throws CustomerIdNotValidException {
		String customerId = "037032000000";
		customer.setCustomerId(customerId);
		Bank.getBankInstance().addCustomer(customer);

		// Add a test account
		SavingAccount testAccount = new SavingAccount();
		String testAccountNumString = "234567";
		testAccount.setAccountNumber(testAccountNumString);
		double testAccountBalance = 1000000;
		testAccount.setBalance(testAccountBalance);

		Bank.getBankInstance().addAccount(customerId, testAccount);

		// Test
		boolean actualValue = customer.isPremiumCustomer();
		assertFalse(actualValue);
	}

	@Test
	public void shouldIsAccountExist() {
		Customer customer = new Customer();
		String accountNumber = "1234ddd";

		// Test
		assertFalse(customer.isAccountExist(accountNumber));

	}

	@Test
	public void validateCustomerId() throws CustomerIdNotValidException {
		String testCustomerId = "037032000000";
		assertTrue(customer.setCustomerId(testCustomerId));

	}
}
