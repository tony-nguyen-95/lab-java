import java.util.Scanner;

import services.BinToDecHex;
import services.DecToBinHex;
import services.HexToDecBin;
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
                                "Từ Nhị phân sang Thập phân",
                                "Từ Nhị phân sang Thập lục phân",
                                "Từ Thập lục phân sang Thập phân",
                                "Từ Thập lục phân sang Nhị phân",
                                "Từ Thập phân sang Thập lục phân",
                                "Từ Thập phân sang Nhị phân",
                        },
                        0);

                WriteLogService.logInput(String.valueOf(mainOptions.getSelectedOption()));
                PrintDividerUtil.printLine();

                /* Exit */
                if (mainOptions.getSelectedOption() == 0) {
                    System.out.println("End!");
                    break;
                }

                if (mainOptions.getSelectedOption() == 1) {
                    System.out.println("Nhập số nhị phân đầu vào:");
                    String inputBin = scanner.nextLine();
                    WriteLogService.logInput(inputBin);

                    System.out
                            .println("Kết quả: " + inputBin + " (BIN) = " + BinToDecHex.binToDec(inputBin) + " (DEC)");
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 2) {
                    System.out.println("Nhập số nhị phân đầu vào:");
                    String inputBin = scanner.nextLine();
                    WriteLogService.logInput(inputBin);

                    System.out
                            .println("Kết quả: " + inputBin + " (BIN) = " + BinToDecHex.binToHex(inputBin) + " (HEX)");
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 3) {
                    System.out.println("Nhập số thập lục phân đầu vào:");
                    String inputHex = scanner.nextLine();
                    WriteLogService.logInput(inputHex);

                    System.out
                            .println("Kết quả: " + inputHex + " (HEX) = " + HexToDecBin.hexToBin(inputHex) + " (BIN)");
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 4) {
                    System.out.println("Nhập số thập lục phân đầu vào:");
                    String inputHex = scanner.nextLine();
                    WriteLogService.logInput(inputHex);

                    System.out
                            .println("Kết quả: " + inputHex + " (HEX) = " + HexToDecBin.hexToDec(inputHex) + " (DEC)");
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 5) {
                    System.out.println("Nhập số thập phân đầu vào:");
                    int inputDec = scanner.nextInt();
                    WriteLogService.logInput(Integer.valueOf(inputDec).toString());

                    System.out
                            .println("Kết quả: " + inputDec + " (DEC) = " + DecToBinHex.decToHex(inputDec) + " (HEX)");
                    PrintDividerUtil.printLine();
                    continue;
                }

                if (mainOptions.getSelectedOption() == 6) {
                    System.out.println("Nhập số thập phân đầu vào:");
                    int inputDec = scanner.nextInt();
                    WriteLogService.logInput(Integer.valueOf(inputDec).toString());

                    System.out
                            .println("Kết quả: " + inputDec + " (DEC) = " + DecToBinHex.decToBin(inputDec) + " (BIN)");
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
