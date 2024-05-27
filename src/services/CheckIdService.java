package services;

import java.util.Map;

import data.MapData;
import exceptions.CustomerIdNotValidException;

public class CheckIdService {
    private static CheckIdService instance;

    // Prevent external instantiation
    private CheckIdService() {
    }

    // Singleton
    public static CheckIdService getInstance() {
        if (instance == null) {
            instance = new CheckIdService();
        }
        return instance;
    }

    // public boolean checkCustomerId(String id) {
    // // Check length first
    // if (id.toCharArray().length != 12) {
    // return false;
    // }

    // // Check numberic
    // if (!id.matches("\\d+")) {
    // return false;
    // }

    // // Cut province code part id string
    // String provinceCode = id.substring(0, 3);

    // MapData mapData = MapData.getInstance();
    // Map<String, String> mapCodeProvide = mapData.getCodeProvinceMap();

    // if (!mapCodeProvide.containsKey(provinceCode)) {
    // return false;
    // }

    // return true;
    // }

    public boolean checkCustomerId(String id) throws CustomerIdNotValidException {
        // Check length first
        if (id.toCharArray().length != 12) {
            throw new CustomerIdNotValidException("Customer ID length must be 12 characters");
        }

        // Check numeric
        if (!id.matches("\\d+")) {
            throw new CustomerIdNotValidException("Customer ID must contain only numeric characters");
        }

        // Cut province code part id string
        String provinceCode = id.substring(0, 3);

        MapData mapData = MapData.getInstance();
        Map<String, String> mapCodeProvide = mapData.getCodeProvinceMap();

        if (!mapCodeProvide.containsKey(provinceCode)) {
            throw new CustomerIdNotValidException("Province code in Customer ID is not valid: " + provinceCode);
        }

        return true;
    }
}
