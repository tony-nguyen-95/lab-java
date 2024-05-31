import java.util.Scanner;

import services.OptionsService;
import services.WriteLogService;
import utils.PrintDividerUtil;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String APP_NAME = "TEXT_NORMALIZATION_FX18838";

    public static void main(String[] args) {
        try {
            WriteLogService.startLogging(APP_NAME);

            /* App title */
            PrintDividerUtil.printLine();
            System.out.println("| " + APP_NAME);
            PrintDividerUtil.printLine();

            StringBuilder a = new StringBuilder();

            test(a);

            System.out.println(a.toString());

            /* Main features */
            while (true) {
                OptionsService mainOptions = new OptionsService("Chức năng chính",
                        new String[] {
                                "Thoát",
                                "Chuẩn hoá văn bản",
                        },
                        0);

                WriteLogService.logInput(String.valueOf(mainOptions.getSelectedOption()));
                PrintDividerUtil.printLine();

                /* Exit */
                if (mainOptions.getSelectedOption() == 0) {
                    System.out.println("End!");
                    break;
                }

                /* Add fruit list */
                if (mainOptions.getSelectedOption() == 1) {
                    // // Show existing list
                    // shop.showFruitsInShop();
                    // PrintDividerUtil.printLineShort();

                    // while (true) {
                    // // Create new variables
                    // String nameNewFruit = "";
                    // String originNewFruit = "";
                    // Double priceNewFruit = 0.0;
                    // Integer quantityNewFruit = 0;

                    // // Change variables from inputs
                    // System.out.print("Nhập tên trái cây mới: ");
                    // nameNewFruit = scanner.nextLine();
                    // WriteLogService.logInput(nameNewFruit);

                    // System.out.print("Nhập xuất xứ trái cây mới: ");
                    // originNewFruit = scanner.nextLine();
                    // WriteLogService.logInput(originNewFruit);

                    // System.out.print("Nhập giá trái cây mới: ");
                    // priceNewFruit = scanner.nextDouble();
                    // WriteLogService.logInput(priceNewFruit.toString());
                    // scanner.nextLine(); // Consume the newline character

                    // System.out.print("Nhập số lượng trái cây mới: ");
                    // quantityNewFruit = scanner.nextInt();
                    // WriteLogService.logInput(quantityNewFruit.toString());
                    // scanner.nextLine(); // Consume the newline character

                    // shop.addFruit(nameNewFruit, originNewFruit, priceNewFruit, quantityNewFruit);

                    // System.out.println("Thêm thành công, bạn có muốn tiếp tục (Y/N)?");
                    // String isNext = scanner.next().toLowerCase();
                    // WriteLogService.logInput(isNext);
                    // scanner.nextLine(); // Consume the newline character

                    // if (isNext.equalsIgnoreCase("y")) {
                    // continue;
                    // } else {
                    // shop.showFruitsInShop();
                    // break;
                    // }
                    // }

                    continue;
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

    public static void test(StringBuilder b) {
        b.append("St");
    }
}
