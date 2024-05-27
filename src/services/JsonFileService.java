package services;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileService {
    public static <T> List<T> readJsonFile(String fileJsonPath, Class<T> elementType) {
        try {
            FileInputStream fis = new FileInputStream(fileJsonPath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(fis,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
        } catch (IOException e) {
            System.out.println("Không tìm thấy file: " + fileJsonPath);
            return null;
        }
    }
}
