package board.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now); //
        System.out.println(now.getClass()); //
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        System.out.println(now.format(dateTimeFormatter));
        System.out.println(now.format(dateTimeFormatter).getClass());
    }
}
