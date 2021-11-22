package day03;

import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                continue;
            }
            if (command.equals("add")) {
                System.out.print("저장할 값을 입력해주세요 : ");
                data = scanner.nextLine();
                System.out.println(data + "이/가 저장되었습니다.");
                continue;
            }
            if (command.equals("read")) {
                if (data == null) {
                    System.out.println("저장된 데이터가 없습니다.");
                    continue;
                }
                System.out.println("현재 저장되어 있는 값: " + data);
                continue;
            }
            if (command.equals("update")) {
                System.out.print("어떤 값으로 수정하시겠습니까? : ");
                data = scanner.nextLine();
                System.out.println(data + "로 값이 수정되었습니다.");
                continue;
            }
            if (command.equals("delete")) {
                data = null;
                System.out.println("삭제가 완료되었습니다.");
                continue;
            }
            //1. **원하지 않는 것만 `싹다` 맨 마지막에 남아.. if없이 처리됨.**
            System.out.println("잘못된 명령어 입니다.");

        }
    }
}
