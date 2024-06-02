package services;

import java.util.*;

public class BinToDecHex {
    private static final Map<String, String> fourBitToHexMap = new HashMap<String, String>() {
        {
            put("0000", "0");
            put("0001", "1");
            put("0010", "2");
            put("0011", "3");
            put("0100", "4");
            put("0101", "5");
            put("0110", "6");
            put("0111", "7");
            put("1000", "8");
            put("1001", "9");
            put("1010", "A");
            put("1011", "B");
            put("1100", "C");
            put("1101", "D");
            put("1110", "E");
            put("1111", "F");
        }
    };

    private static boolean validateInput(String inputString) {
        return inputString != null && !inputString.isEmpty() && inputString.matches("[01]+");
    }

    public static String binToHex(String inputString) {
        if (!validateInput(inputString)) {
            throw new IllegalArgumentException("Đầu vào không hợp lệ");
        }

        StringBuilder result = new StringBuilder();
        int additionChars = 4 - (inputString.length() % 4);

        System.out.println("Tạo mẫu mỗi 4 bits...");
        for (int i = 0; i < additionChars; i++) {
            result.append('0');
        }

        String paddedInput = result.append(inputString).toString();
        System.out.println("Mẫu đầu vào theo nhóm 4 bits: " + paddedInput);

        result.setLength(0);

        System.out.println("Quá trình tính toán chuyển đổi Nhị phân thành Thập lục phân:");
        for (int i = 0; i < paddedInput.length(); i += 4) {
            String fourBits = paddedInput.substring(i, i + 4);
            result.append(fourBitToHexMap.get(fourBits));
            System.out.println("Chuyển đổi nhóm 4 bits " + fourBits + " thành: " + fourBitToHexMap.get(fourBits));
        }

        return result.toString();
    }

    public static int binToDec(String input) {
        if (!validateInput(input)) {
            throw new IllegalArgumentException("Đầu vào không hợp lệ");
        }

        int result = 0;
        int length = input.length();

        StringBuilder printProcess = new StringBuilder();

        System.out.println("Quá trình tính toán chuyển đổi Nhị phân thành Thập phân:");
        for (int i = 0; i < length; i++) {
            char bit = input.charAt(length - 1 - i);

            if (bit == '1') {
                result += powerForTwo(i);
            }

            if (i != length - 1) {
                printProcess.append(bit + "x" + "2^" + i + " + ");
            } else {
                printProcess.append(bit + "x" + "2^" + i);
            }

        }

        System.out.println(printProcess.append(" = ").append(result));

        return result;
    }

    public static int powerForTwo(int power) {
        if (power < 0) {
            throw new IllegalArgumentException("Cơ số không được âm");
        }
        return 1 << power;
    }

}
