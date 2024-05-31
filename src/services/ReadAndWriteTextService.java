package services;

import java.io.*;

public class ReadAndWriteTextService {

    private static final String OUTPUT_DIRECTORY = "output-files";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    private static final String INPUT_DIRECTORY = "input-files";
    private static final String INPUT_FILE_NAME = "input.txt";

    public static String readFileToString() {
        String filePath = INPUT_DIRECTORY + File.separator + INPUT_FILE_NAME;

        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int character;
            while ((character = fileReader.read()) != -1) {
                stringBuilder.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static boolean writeStringToFile(String content) {
        // Join file path
        String filePath = OUTPUT_DIRECTORY + File.separator + OUTPUT_FILE_NAME;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
