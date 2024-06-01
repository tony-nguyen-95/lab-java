import java.util.Scanner;

import services.OptionsService;
import services.WriteLogService;
import utils.PrintDividerUtil;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String APP_NAME = "CONVERT_DEC_BIN_HEX_FX18838";

    public static void main(String[] args) {
        try {
            WriteLogService.startLogging(APP_NAME);

            /* App title */
            PrintDividerUtil.printLine();
            System.out.println("| " + APP_NAME);
            PrintDividerUtil.printLine();

            /* Main features */
            while (true) {
                OptionsService mainOptions = new OptionsService("Chức năng chính",
                        new String[] {
                                "Thoát",
                                "Tạo/Thêm trái cây",
                                "Xem đơn đặt hàng",
                                "Mua sắm",
                        },
                        0);

                WriteLogService.logInput(String.valueOf(mainOptions.getSelectedOption()));
                PrintDividerUtil.printLine();

                /* Exit */
                if (mainOptions.getSelectedOption() == 0) {
                    System.out.println("End!");
                    break;
                }

                break;
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAppName() {
        return APP_NAME;
    }
}
