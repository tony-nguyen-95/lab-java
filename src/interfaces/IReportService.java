package interfaces;

import models.Transaction.ETransactionType;

public interface IReportService {
    void log(double amount, String time, ETransactionType type, String receiveAccount);
}
