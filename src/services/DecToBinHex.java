package services;

import java.util.*;

public class DecToBinHex {
    private static final Map<Integer, String> decToHexMap = new HashMap<Integer, String>() {
        {
            put(0, "0");
            put(1, "1");
            put(2, "2");
            put(3, "3");
            put(4, "4");
            put(5, "5");
            put(6, "6");
            put(7, "7");
            put(8, "8");
            put(9, "9");
            put(10, "A");
            put(11, "B");
            put(12, "C");
            put(13, "D");
            put(14, "E");
            put(15, "F");
        }
    };

    public static String decToBin(int input) {
        System.out.println("Quá trình tính toán chuyển đổi Thập phân thành Nhị phân:");

        if (input == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        int remainder = input;
        StringBuilder printProcess = new StringBuilder();
        while (remainder > 0) {
            result.append(remainder % 2);

            System.out.println(
                    printProcess.append(remainder + " : 2 = " + remainder / 2 + " remainder " + (remainder % 2)));

            remainder /= 2;
            printProcess.setLength(0);
        }

        return result.reverse().toString();
    }

    public static String decToHex(int input) {
        System.out.println("Quá trình tính toán chuyển đổi Thập phân thành Thập lục phân:");

        if (input == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        int remainder = input;
        StringBuilder printProcess = new StringBuilder();
        while (remainder > 0) {
            result.append(decToHexMap.get(remainder % 16));

            System.out.println(
                    printProcess.append(remainder + " : 16 = " + remainder / 16 + " remainder " + (remainder % 16)));

            remainder /= 16;

            printProcess.setLength(0);

        }

        return result.reverse().toString();
    }

}
