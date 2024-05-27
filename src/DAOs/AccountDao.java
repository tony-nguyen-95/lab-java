package DAOs;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.Account.Account;
import services.BinaryFileService;

public class AccountDao {

    private final static int MAX_THREAD = 4;
    private final static String FILE_PATH = "stores/accounts.dat";

    public static List<Account> getList() {
        return BinaryFileService.readBinaryFile(FILE_PATH);
    }

    public static void save(List<Account> accounts) {
        BinaryFileService.writeBinaryFile(accounts, FILE_PATH);
    }

    public static void updateSingle(Account updateAccount) {
        List<Account> listAccount = new CopyOnWriteArrayList<>(getList());

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

        // Use a Runnable to remove accounts with the same account number concurrently
        for (Account account : listAccount) {
            executorService.execute(() -> {
                if (account.getAccountNumber().equals(updateAccount.getAccountNumber())) {
                    listAccount.remove(account);
                }
            });
        }

        executorService.shutdown();

        listAccount.add(updateAccount);
        save(listAccount);

    }
}
