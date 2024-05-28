import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

import models.FruitShop.FruitShop;
import models.Bill.Bill;
import models.Fruit.Fruit;
import services.OptionsService;
import services.WriteLogService;
import utils.PrintDividerUtil;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String APP_NAME = "FRUITS_SHOP_FX18838";
    private static final FruitShop shop = FruitShop.getFruitShopInstance();

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

                /* Add fruit list */
                if (mainOptions.getSelectedOption() == 1) {
                    // Show existing list
                    shop.showFruitsInShop();
                    PrintDividerUtil.printLineShort();

                    while (true) {
                        // Create new variables
                        String nameNewFruit = "";
                        String originNewFruit = "";
                        Double priceNewFruit = 0.0;
                        Integer quantityNewFruit = 0;

                        // Change variables from inputs
                        System.out.print("Nhập tên trái cây mới: ");
                        nameNewFruit = scanner.nextLine();
                        WriteLogService.logInput(nameNewFruit);

                        System.out.print("Nhập xuất xứ trái cây mới: ");
                        originNewFruit = scanner.nextLine();
                        WriteLogService.logInput(originNewFruit);

                        System.out.print("Nhập giá trái cây mới: ");
                        priceNewFruit = scanner.nextDouble();
                        WriteLogService.logInput(priceNewFruit.toString());
                        scanner.nextLine(); // Consume the newline character

                        System.out.print("Nhập số lượng trái cây mới: ");
                        quantityNewFruit = scanner.nextInt();
                        WriteLogService.logInput(quantityNewFruit.toString());
                        scanner.nextLine(); // Consume the newline character

                        shop.addFruit(nameNewFruit, originNewFruit, priceNewFruit, quantityNewFruit);

                        System.out.println("Thêm thành công, bạn có muốn tiếp tục (Y/N)?");
                        String isNext = scanner.next().toLowerCase();
                        WriteLogService.logInput(isNext);
                        scanner.nextLine(); // Consume the newline character

                        if (isNext.equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            shop.showFruitsInShop();
                            break;
                        }
                    }

                    continue;
                }

                /* Show bills with customer name */
                if (mainOptions.getSelectedOption() == 2) {
                    shop.displayNameBillListMap();
                    PrintDividerUtil.printLine();
                    continue;
                }

                /* Add a bill */
                if (mainOptions.getSelectedOption() == 3) {
                    // Create a new Bill
                    Bill newBill = new Bill();

                    while (true) {
                        // Show the list of fruits in the shop
                        shop.showFruitsInShop();
                        PrintDividerUtil.printLineShort();

                        // Input fruit ID and quantity to buy
                        System.out.print("Nhập ID trái cây muốn mua: ");
                        int fruitId = scanner.nextInt();
                        WriteLogService.logInput(String.valueOf(fruitId));

                        System.out.print("Nhập số lượng trái cây muốn mua: ");
                        int quantityToBuy = scanner.nextInt();
                        WriteLogService.logInput(String.valueOf(quantityToBuy));
                        scanner.nextLine(); // Consume the newline character

                        // Add fruit to bill
                        newBill.addFruitToBill(fruitId, quantityToBuy);

                        System.out.println("Bạn có muốn đặt hàng ngay bây giờ (Y/N)?");
                        String isNext = scanner.next().toLowerCase();
                        WriteLogService.logInput(isNext);
                        scanner.nextLine(); // Consume the newline character

                        if (isNext.equalsIgnoreCase("y")) {
                            break;
                        } else {
                            System.out.println("Tiếp tục mua hàng");
                        }
                    }

                    // Input name of customer
                    System.out.print("Nhập tên khách hàng: ");
                    String customerName = scanner.nextLine();
                    newBill.setNameOfCustomer(customerName);
                    WriteLogService.logInput(customerName);

                    // Process the bill
                    if (shop.processBill(newBill)) {
                        System.out.println("Đơn hàng đã được xử lý thành công.");
                    } else {
                        System.out.println("Đơn hàng không thể được xử lý do không đủ hàng.");
                    }

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

    public static String getAppName() {
        return APP_NAME;
    }
}
