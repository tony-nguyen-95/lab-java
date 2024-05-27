package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetCurrentDateTime {
    public static String getDateTimeFormatted() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        // Format the LocalDateTime instance
        return currentDateTime.format(formatter);

    }
}
