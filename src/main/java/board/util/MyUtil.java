package board.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {
    //5. 이제 본격 기능을 추가해야하는데, 기능(1) add에 필요한 오늘 날짜 구하는 코드를
    // -> 프로젝트 전반에 쓰이는 코드이므로, util패키지의 MyUtil클래스를 따로두어서
    // -> 유틸메소드만 모은다. 일단 void라고 작성해놓고 나중에 수정한다.

    //10. 날짜 -> 문자열 변환함수는 포맷을 파라미터로 받으면 좋다!!
//    public String getCurrentDate() {
    //11. 유틸성 공유목적의 메소드만 static을 붙인다.
    // -> static 변수는 클래스단위 -> 모든 객체가 공유하여 관리되므로 **서로 다른 것들이 영향을 많이 미친다.**
    // -> 하지만 유틸같은 경우, **(인스턴스)변수=상태**가 없어 영향을 안받으며, **상태가 없는 메소드라서 매번 동일한 동작만**하기 때문에
    // -> static 공유목적은 class에 붙어있으니, 객체생성없이 바로 쓸 수 있다.


//    public String getCurrentDate(String dateFormat) {
    public static String getCurrentDate(String dateFormat) {
        //6. 복붙->Test했던 코드를 리팩토링해서 붙혀넣는다.
        // -> 일단 Test로 가서 1) 원하는 포맷의 날짜를 2) 문자열로 가져올 수 있는지 확인하자.

        //9. Test-Main메소드에서 확인한 코드를 복붙한 뒤  void -> String으로 건네줄 준비를 한다.
        LocalDate now = LocalDate.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        String formatedNow = now.format(dateTimeFormatter);
        return formatedNow;
    }
}
