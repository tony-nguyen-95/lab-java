package services;

import java.util.*;

public class HexToDecBin {
    private static final Map<String, String> hexToFourBitMap = new HashMap<String, String>() {
        {
            put("0", "0000");
            put("1", "0001");
            put("2", "0010");
            put("3", "0011");
            put("4", "0100");
            put("5", "0101");
            put("6", "0110");
            put("7", "0111");
            put("8", "1000");
            put("9", "1001");
            put("A", "1010");
            put("B", "1011");
            put("C", "1100");
            put("D", "1101");
            put("E", "1110");
            put("F", "1111");
        }
    };

    private static boolean validateInput(String inputString) {
        return inputString != null && !inputString.isEmpty() && inputString.matches("[0-9A-Fa-f]+");
    }

    public static String hexToBin(String inputString) {
        if (!validateInput(inputString)) {
            throw new IllegalArgumentException("Đầu vào không hợp lệ");
        }

        StringBuilder result = new StringBuilder();

        System.out.println("Quá trình tính toán chuyển đổi Thập lục phân thành Nhị phân:");
        for (int i = 0; i < inputString.length(); i++) {
            String hexChar = String.valueOf(inputString.charAt(i)).toUpperCase();
            result.append(hexToFourBitMap.get(hexChar));
            System.out.println("Chuyển đổi " + hexChar + " thành nhóm 4 bits: " + hexToFourBitMap.get(hexChar));
        }

        return result.toString();
    }

    public static int hexToDec(String input) {
        if (!validateInput(input)) {
            throw new IllegalArgumentException("Đầu vào không hợp lệ");
        }

        // To bin first
        System.out.println("Chuyển đổi thành Nhị phân trước");
        String convertToBin = hexToBin(input);

        // From bin to dec
        System.out.println("Cuối cùng, chuyển Nhị phân sang Thập phân");
        return BinToDecHex.binToDec(convertToBin);
    }

}
