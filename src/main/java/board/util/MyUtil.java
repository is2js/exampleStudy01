package board.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {
    public static String getCurrentDate(String dateFormat) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        String formatedNow = now.format(dateTimeFormatter);
        return formatedNow;
    }
}
