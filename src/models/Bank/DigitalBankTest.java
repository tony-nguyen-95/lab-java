package models.Bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DAOs.AccountDao;
import DAOs.CustomerDao;
import models.Account.Account;
import models.Customer.Customer;
import models.Customer.DigitalCustomer;

public class DigitalBankTest {

	private DigitalBank digitalBank;

	@Before
	public void setup() {
		this.digitalBank = DigitalBank.getDigitalBankInstance();
	}

	@Test
	public void shouldGetCustomerByCustomerID() {
		List<DigitalCustomer> allCustomers = CustomerDao.getList();

		String customerId = "036023aaaaaa";

		Customer customer = digitalBank.getCustomerByCustomerID(allCustomers, customerId);

		assertEquals(null, customer);
	}

	@Test
	public void shouldIsNotCustomerExisted() {
		String customerId = "000023000000";

		assertFalse(digitalBank.isCustomerExisted(customerId));
	}

	@Test
	public void shouldGetAccountByAccountNumber() {
		Customer customer = new Customer();

		List<Account> allAccounts = AccountDao.getList();

		String accountNumber = "ht77777";

		assertEquals(null,
				customer.getAccountByAccountNumber(allAccounts, accountNumber));
	}
}
