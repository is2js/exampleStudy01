package day03;

import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String data = "";
        //3. 데이터 없음을 표현하는 방법은 null로 초기화 하는 것
        String data = null;
        while (true) {
            System.out.println("명령을 입력해주세요(exit:종료)");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            if (command.equals("help")) {
                System.out.println("add: 데이터 추가");
                System.out.println("read: 데이터 조회");
                System.out.println("update: 데이터 수정");
                System.out.println("delete: 데이터삭제");
                System.out.println("exit: 프로그램종료");
            }
            if (command.equals("add")) {
                System.out.print("저장할 값을 입력해주세요 : ");
                data = scanner.nextLine();
                System.out.println(data + "이/가 저장되었습니다.");
            }
            if (command.equals("read")) {
                // 4. 비어있는 데이터확인은 `if ( data == null )`로 편하게 한다.
                if (data == null) {
                    System.out.println("저장된 데이터가 없습니다.");
                    continue; // else 대신 early return되게 continue
                }
                System.out.println("현재 저장되어 있는 값: " + data);
            }
            if (command.equals("update")) {
                // 1. 업데이트 입력값 + 그전에 안내멘트 -> 받고나서 찍어주기멘트
                System.out.print("어떤 값으로 수정하시겠습니까? : ");
                data = scanner.nextLine();
                System.out.println(data + "로 값이 수정되었습니다.");
            }
            if (command.equals("delete")) {
                // 2. delete후 삭제될값 찍어주기 -> read시에는 저장된 데이터가 없다고 멘트 띄우기
                // -> 일단 결과를 보여주는 저장변수/데이터변수는 null로 초기화하도록 수정하자.
                // -> delete역시 null을 대입하는 것과 동일한 것임.
                data = null;
                System.out.println("삭제가 완료되었습니다.");
            }


        }

    }
}
