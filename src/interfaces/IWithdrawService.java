package interfaces;

public interface IWithdrawService {
    boolean isAccepted(double amount);

    boolean withdraw(double amount);
}
