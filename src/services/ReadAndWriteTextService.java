package services;

import java.io.*;

public class ReadAndWriteTextService {

    private static final String OUTPUT_DIRECTORY = "output-files";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    private static final String INPUT_DIRECTORY = "input-files";
    private static final String INPUT_FILE_NAME = "input.txt";

    public static StringBuilder readFileToStringBuilder() {
        String filePath = INPUT_DIRECTORY + File.separator + INPUT_FILE_NAME;

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
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
