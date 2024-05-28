package services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

class TwoOutputStream extends OutputStream {
    private final OutputStream out1;
    private final OutputStream out2;

    public TwoOutputStream(OutputStream out1, OutputStream out2) {
        this.out1 = out1;
        this.out2 = out2;
    }

    @Override
    public void write(int b) throws IOException {
        out1.write(b);
        out2.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out1.write(b);
        out2.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out1.write(b, off, len);
        out2.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out1.flush();
        out2.flush();
    }

    @Override
    public void close() throws IOException {
        out1.close();
        out2.close();

    }
}

public class WriteLogService {

    private static PrintStream filePrintStream;
    private static PrintStream originalSystemOut;
    private static InputStream originalSystemIn;

    public static void startLogging(String appName) {
        try {
            // Get current date and time for the log file name
            String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "logs/log_" + appName.toLowerCase() + "_" + datetime + ".txt";

            // Create a new file output stream
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            // Save the original System.out and System.in
            originalSystemOut = System.out;
            originalSystemIn = System.in;

            // Create a TwoOutputStream to write to both the file and System.out
            TwoOutputStream twoOutputStream = new TwoOutputStream(originalSystemOut, fileOutputStream);
            PrintStream twoPrintStream = new PrintStream(twoOutputStream);

            System.setOut(twoPrintStream);

            // Set filePrintStream to write directly to the file
            filePrintStream = new PrintStream(fileOutputStream);

            System.setIn(new FileInputStream(FileDescriptor.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopLogging() {

        if (originalSystemOut != null) {
            System.setOut(originalSystemOut);
        }
        if (originalSystemIn != null) {
            System.setIn(originalSystemIn);
        }

        if (filePrintStream != null) {
            filePrintStream.close();
        }
    }

    public static void logInput(String input) {
        if (filePrintStream != null) {
            filePrintStream.println(input);
        }
    }
}
