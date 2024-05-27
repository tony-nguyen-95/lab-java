package services;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
    public static <T> void writeBinaryFile(List<T> customers, String binaryFilePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryFilePath))) {
            oos.writeObject(new ArrayList<>(customers)); // Serialize a new ArrayList
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readBinaryFile(String filePath) {
        List<T> result = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            boolean eof = false;
            while (!eof) {
                try {
                    result = (List<T>) ois.readObject();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
