package models.Bank;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAOs.AccountDao;
import DAOs.CustomerDao;
import exceptions.CustomerIdNotValidException;
import models.Account.Account;
import models.Account.EAccountType;
import models.Account.SavingAccount;
import models.Customer.DigitalCustomer;
import services.CheckIdService;
import services.JsonFileService;

public class DigitalBank extends Bank {
    private static DigitalBank instance;

    // Private constructor to enforce singleton pattern
    private DigitalBank() {
        super();
    }

    // Singleton pattern for DigitalBank
    public static DigitalBank getDigitalBankInstance() {
        if (instance == null) {
            instance = new DigitalBank();
        }
        return instance;
    }

    public void showCustomers() {
        List<DigitalCustomer> customers = CustomerDao.getList();

        if (customers.size() == 0) {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        }

        customers.forEach(customer -> customer.displayInformation());
    }

    public void addCustomers(Scanner scanner) {
        System.out.println("Nhập đường dẫn đến tệp:");
        String importJsonFilePath = scanner.next();

        List<DigitalCustomer> listCustomers = CustomerDao.getList();

        List<DigitalCustomer> newCustomers = JsonFileService.readJsonFile(importJsonFilePath,
                DigitalCustomer.class);

        if (newCustomers == null) {
            return;
        }

        // Check valid & exist
        newCustomers.forEach(customer -> {
            try {
                CheckIdService.getInstance().checkCustomerId(customer.getCustomerId());

                if (listCustomers.stream()
                        .anyMatch(oldCustomer -> customer.getCustomerId().equals(oldCustomer.getCustomerId()))) {
                    System.out.println(customer.getCustomerId() + " is already existed!");
                } else {
                    listCustomers.add(customer);
                    System.out.println(customer.getCustomerId() + " is added!");
                }

            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
            }
        });

        CustomerDao.save(listCustomers);

    }

    public void addSavingAccount(Scanner scanner) {
        // Read customers list
        List<DigitalCustomer> listCustomer = CustomerDao.getList();

        // Read accounts list
        List<Account> listAccount = AccountDao.getList();

        // Local variables
        String validCustomerId = "";
        Account newAccount = new SavingAccount();
        Double balance = 0.0;

        // Check customerId
        while (true) {
            System.out.print("Nhập mã số khách hàng:");
            String inputCustomerId = scanner.next();

            DigitalCustomer foundCustomer = listCustomer.stream()
                    .filter(customer -> customer.getCustomerId().equals(inputCustomerId)).findFirst()
                    .orElse(null);

            if (foundCustomer == null) {
                System.out.println(
                        "Không tìm thấy khách hàng" + inputCustomerId + "tác vụ không thành công!");
                continue;
            }

            validCustomerId = inputCustomerId;
            break;
        }

        while (true) {
            System.out.print("Nhập số tài khoản gồm 6 chữ số:");
            String accountNumber = scanner.next();

            boolean isValidFormat = newAccount.setAccountNumber(accountNumber);

            if (!isValidFormat) {
                System.out.println("Số tài khoản không đúng định dạng, vui lòng nhập lại!");
                continue;
            }

            if (listAccount.stream()
                    .anyMatch(account -> account.getAccountNumber().equals(accountNumber))) {
                System.out.println("Số tài khoản đã tồn tại, vui lòng nhập lại!");
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Nhập số dư: ");
            try {
                balance = scanner.nextDouble();

                boolean isSetBalanceSuccess = newAccount.setBalance(balance);

                if (!isSetBalanceSuccess) {
                    System.out.println(
                            "Số dư của tài khoản không được nhỏ hơn 50,000 VNĐ, vui lòng nhập lại");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Số dư là số, vui lòng nhập lại!");
                scanner.nextLine();
                continue;
            }
        }

        newAccount.setAccountType(EAccountType.SAVING);
        newAccount.setCustomerId(validCustomerId);

        listAccount.add(newAccount);
        AccountDao.save(listAccount);

        System.out.println("Tạo tài khoản thành công");
    }

    public void withdraw(Scanner scanner) {
        List<DigitalCustomer> listCustomer = CustomerDao.getList();

        DigitalCustomer foundCustomer = new DigitalCustomer();

        // Check customerId
        while (true) {
            System.out.print("Nhập mã số khách hàng:");
            String inputCustomerId = scanner.next();

            foundCustomer = getCustomerByCustomerID(listCustomer, inputCustomerId);

            if (foundCustomer == null) {
                System.out.println(
                        "Không tìm thấy khách hàng" + inputCustomerId + "tác vụ không thành công!");
                return;
            }

            break;
        }

        foundCustomer.displayInformation();
        Account foundAccount = new Account();

        while (true) {
            System.out.print("Nhập số tài khoản rút tiền là 1 trong các tài khoản trên:");
            String accountNumber = scanner.next();

            foundAccount = foundCustomer.getAccountByAccountNumber(foundCustomer.getAccounts(), accountNumber);

            if (foundAccount == null) {
                System.out.println("Số tài khoản không tồn tại, vui lòng thử lại!");
                continue;
            }

            break;
        }

        while (true) {
            try {
                System.out.print("Nhập số tiền muốn rút:");
                Double amount = scanner.nextDouble();

                SavingAccount savingAccount = (SavingAccount) foundAccount;
                savingAccount.withdraw(amount);

                break;
            } catch (InputMismatchException e) {
                System.out.println("Số tiền là số. Vui lòng nhập lại!");
                scanner.next();
                continue;
            }
        }

        return;
    }

    public DigitalCustomer getCustomerByCustomerID(List<DigitalCustomer> listCustomer, String id) {
        return listCustomer.stream()
                .filter(c -> c.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public void tranfers(Scanner scanner) {
        List<DigitalCustomer> listCustomer = CustomerDao.getList();

        List<Account> listAccount = AccountDao.getList();

        DigitalCustomer foundCustomer = new DigitalCustomer();

        // Send customerId
        while (true) {
            System.out.print("Nhập mã số khách hàng:");
            String inputCustomerId = scanner.next();

            foundCustomer = getCustomerByCustomerID(listCustomer, inputCustomerId);

            if (foundCustomer == null) {
                System.out.println(
                        "Không tìm thấy khách hàng" + inputCustomerId + "tác vụ không thành công!");
                return;
            }

            break;
        }

        // Send Account
        foundCustomer.displayInformation();
        Account sentAccount = new Account();
        while (true) {
            System.out.print("Nhập số tài khoản gửi tiền đi là 1 trong các tài khoản trên hoặc \"exit\" để thoát:");
            String accountNumber = scanner.next();

            if (accountNumber.equals("exit"))
                return;

            sentAccount = foundCustomer.getAccountByAccountNumber(foundCustomer.getAccounts(), accountNumber);

            if (sentAccount == null) {
                System.out.println("Số tài khoản không tồn tại, vui lòng thử lại!");
                continue;
            }

            break;
        }

        // Receive Account
        Account receiveAccount = new Account();
        while (true) {
            System.out.print("Nhập số tài khoản nhận chuyển tiền hoặc \"exit\" để thoát:");
            String depositAccountNumber = scanner.next();

            if (depositAccountNumber.equals("exit"))
                return;

            if (depositAccountNumber.equals(sentAccount.getAccountNumber())) {
                System.out.println("Tài khoản nhận không thể trùng với tài khoản nhận, vui lòng thử lại!");
                continue;
            }

            receiveAccount = listAccount.stream()
                    .filter(account -> account.getAccountNumber().equals(depositAccountNumber)).findFirst()
                    .orElse(null);

            if (receiveAccount == null) {
                System.out.println("Không tìm thấy tài khoản, vui lòng thử lại!");
                continue;
            }

            break;
        }

        Double amount = 0.0;

        // Transfer
        while (true) {
            try {
                System.out.print("Nhập số tiền muốn chuyển:");
                amount = scanner.nextDouble();

                break;

            } catch (InputMismatchException e) {
                System.out.println("Số tiền là số. Vui lòng nhập lại!");
                scanner.next();
                continue;
            }
        }

        // Confirm
        System.out.printf("Xác nhận thực hiện chuyển %sđ từ tài khoản [%s] đến tài khoản [%s] (y/N): ",
                NumberFormat.getNumberInstance().format(amount),
                sentAccount.getAccountNumber(),
                receiveAccount.getAccountNumber());

        String isConfirm = scanner.next();

        if ("y".equals(isConfirm)) {
            SavingAccount sentSavingAccount = (SavingAccount) sentAccount;
            sentSavingAccount.transfers(amount, receiveAccount);
        } else {
            System.out.println("Giao dịch đã bị hủy bỏ.");
        }

        return;
    }

    public void showTransactionsCustomer(Scanner scanner) {
        List<DigitalCustomer> listCustomer = CustomerDao.getList();

        while (true) {
            System.out.print("Nhập mã số khách hàng:");
            String inputCustomerId = scanner.next();

            DigitalCustomer foundCustomer = getCustomerByCustomerID(listCustomer, inputCustomerId);

            if (foundCustomer == null) {
                System.out.println(
                        "Không tìm thấy khách hàng" + inputCustomerId + "tác vụ không thành công!");
                return;
            }

            foundCustomer.displayTransactionInformation();
            break;
        }

    }
}
