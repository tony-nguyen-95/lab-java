package interfaces;

import models.Account.Account;

public interface ITranfer {
    boolean transfer(Account receiveAccount, double amount);
}
