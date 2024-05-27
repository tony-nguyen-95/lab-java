package DAOs;

import java.util.List;

import models.Transaction.Transaction;
import services.BinaryFileService;

public class TransactionDao {
    private final static long serialVersionUID = 1;

    private final static String FILE_PATH = "stores/transactions.dat";

    public static List<Transaction> getList() {
        return BinaryFileService.readBinaryFile(FILE_PATH);
    }

    public static void save(List<Transaction> transactions) {
        BinaryFileService.writeBinaryFile(transactions, FILE_PATH);
    }

    public static void updateSingle(Transaction transaction) {
        // TODO
    }

}
