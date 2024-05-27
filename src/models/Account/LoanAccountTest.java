package models.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LoanAccountTest {

	private LoanAccount loanAccount;

	@Before
	public void setup() {
		this.loanAccount = new LoanAccount();
	}

	@Test
	public void shouldIsAcceptedLoanAccount() {
		double amount = 49000;

		boolean actualValue = loanAccount.isAccepted(amount);

		assertFalse("Loan account should not accept positive amounts", actualValue);
	}

	@Test
	public void shouldNotAcceptNegativeWithdrawalLoanAccount() {
		double amount = -500.9;

		boolean actualValue = loanAccount.withdraw(amount);

		assertFalse("Loan account should not accept negative withdrawal amounts", actualValue);
	}

	// @Test
	// public void shouldCalculateCorrectFee() {
	// double withdrawalAmount = 110000;

	// boolean withdrawalResult = loanAccount.withdraw(withdrawalAmount);

	// assertTrue("Withdrawal from loan account should be successful",
	// withdrawalResult);

	// // Max balance for initialization, premium is default -> 0.01 fee rate
	// double expectedFee = withdrawalAmount * 0.01;

	// assertEquals("Loan account should calculate the fee correctly", expectedFee,
	// loanAccount.getFeeAmount(), 0.00);
	// }
}
