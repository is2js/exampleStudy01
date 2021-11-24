package board.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        // LocalDate.now();
        // 시스템에 default로 지정된 시간과 타임존을 이용하여 현재 날짜를 가져옵니다.

        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDate now = LocalDate.now();
        System.out.println(now); //
        System.out.println(now.getClass()); //
        // 7. 2021-06-17 -> 원하는형태 X 문자열 아님

        // 8. 1) 원하는 포맷으로 포맷터 정의 -> 적용
        // DateTimeFormatter.ofPattern("")로 포맷터를 정의 할 수 있다.
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        // LocalDate타입에 포맷터 적용과 동시에 -> 2) 문자열로도 바뀐다!!
        System.out.println(now.format(dateTimeFormatter));
        System.out.println(now.format(dateTimeFormatter).getClass());
    }
}
