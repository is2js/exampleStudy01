package day03;

import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1. 종료입력전까지 무한반복
        while (true) {
            //4. 안내멘트 -> 종료조건 안내하고, 무한반복 탈출조건 채우기
            System.out.println("명령을 입력해주세요(exit:종료");
            //3. 무한 입력받기
            String command = scanner.nextLine();

            // 2. 무한반복문 작성시작부터 미리 if () break;넣어두기
            // 5. 입력 안내프린트에서 종료조건 확인한 뒤, if (종료조건) break;완성
            // - 문자열은 ==가 아닌 .equals()로 비교하기 ""쌍따옴표! ->
            if(command.equals("exit")) {
                //6. 무한반복 종료break;할때도 멘트 1번
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }

    }
}
