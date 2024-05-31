import java.util.Scanner;

import services.NormalizeTextService;
import services.OptionsService;
import services.ReadAndWriteTextService;
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

                if (mainOptions.getSelectedOption() == 1) {
                    System.out.println("Đọc văn bản từ file input...");
                    String result = NormalizeTextService
                            .normalizeString(ReadAndWriteTextService.readFileToString());

                    System.out.println("Ghi kết quả vào file output...");
                    ReadAndWriteTextService.writeStringToFile(result);

                    System.out.println("Chuẩn hoá thành công!");

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
