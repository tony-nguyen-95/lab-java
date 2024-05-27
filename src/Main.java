
import java.util.Scanner;

import models.Bank.DigitalBank;
import services.OptionsService;
import utils.PrintDividerUtil;

public class Main {
    private static DigitalBank digitalBank = DigitalBank.getDigitalBankInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            /* App title */
            PrintDividerUtil.printLine();
            System.out.println("| NGAN HANG SO | FX18838@4.0.0");
            PrintDividerUtil.printLine();

            /* Add initial data */

            /* Main features */
            while (true) {
                OptionsService mainOptions = new OptionsService("Chức năng chính",
                        new String[] {
                                "Thoát",
                                "Xem danh sách khách hàng",
                                "Nhập danh sách khách hàng",
                                "Thêm tài khoản ATM",
                                "Chuyển tiền",
                                "Rút tiền",
                                "Tra cứu lịch sử giao dịch"
                        },
                        0);
                PrintDividerUtil.printLine();

                /* Exit */
                if (mainOptions.getSelectedOption() == 0) {
                    break;
                }

                /* Show info customer */
                if (mainOptions.getSelectedOption() == 1) {
                    digitalBank.showCustomers();
                    PrintDividerUtil.printLine();
                    continue;
                }

                /* Import customer data */
                if (mainOptions.getSelectedOption() == 2) {
                    digitalBank.addCustomers(scanner);
                    PrintDividerUtil.printLine();
                    continue;
                }

                /* Add a saving account */
                if (mainOptions.getSelectedOption() == 3) {
                    digitalBank.addSavingAccount(scanner);
                    PrintDividerUtil.printLine();
                    continue;
                }

                /* Transfer */
                if (mainOptions.getSelectedOption() == 4) {
                    digitalBank.tranfers(scanner);
                    PrintDividerUtil.printLine();
                    continue;
                }

                /* Withdraw */
                if (mainOptions.getSelectedOption() == 5) {
                    digitalBank.withdraw(scanner);
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 6) {
                    digitalBank.showTransactionsCustomer(scanner);
                    PrintDividerUtil.printLine();
                    continue;
                }

                break;

            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
