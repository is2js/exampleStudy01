package board.test;

import java.util.Scanner;

public class PagingTest {
    public static void main(String[] args) {
        // 3. 원하는 페이지(페이징은 이전or다음)로 이동하려면, 스캐너가 필요하다. 입력받아야됨.
        Scanner scanner = new Scanner(System.in);

        // 2. 쉬운거 부터 만들어본다
        // [1] 2 3 4 5 >>
        // 페이징 명령어를 입력해주세요 ((1. 이전,  2. 다음,  3. 선택,  4. 뒤로가기): 2
        // System.out.println("1 2 3 4 5");
        // System.out.println("[1] 2 3 4 5");

        // 6. 1 ~ 5부터 반복문으로 동적으로 만든다.
        // for (int i = 1; i <= 5; i++) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        // 7. 시작 int i=6, i <=10으로 바꾸면.. 6~10도 출력된다.
        // for (int i = 6; i <= 10; i++) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        // 8. 중요한 것은, 현재페이지에  [ ] 를 표시하는  것 -> if i == 현재페이지 일때만 [ ] 달아서 출력
        // for (int i = 1; i <= 5; i++) {
        //     if (i == 1) {
        //         System.out.print("[" + i + "] ");
        //         continue;
        //     }
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        //9. 근데 현재페이지는 상수1이 아니라 바뀌니까 변수로 바꾼다.
        // -> 변수에 할당되는 숫자 바꿀때마다 [ ]가 달려서 나타남.
        // int currentPageNo = 3;
        // for (int i = 1; i <= 5; i++) {
        //     // if (i == 1) {
        //     if (i == currentPageNo) {
        //         System.out.print("[" + i + "] ");
        //         continue;
        //     }
        //     System.out.print(i + " ");
        // }
        // System.out.println();



        int currentPageNo = 1;
        //13. 12번에서 출력부를 while문 내부러 넣어주기 위해 출력문 주석처리
        // for (int i = 1; i <= 5; i++) {
        //     // if (i == 1) {
        //     if (i == currentPageNo) {
        //         System.out.print("[" + i + "] ");
        //         continue;
        //     }
        //     System.out.print(i + " ");
        // }
        // System.out.println();


        //12. 변수선언이후의 출력부터 전체코드를 while로 감싸고 실행시켜본다.
        while (true) {
            for (int i = 1; i <= 5; i++) {
                // if (i == 1) {
                if (i == currentPageNo) {
                    System.out.print("[" + i + "] ");
                    continue;
                }
                System.out.print(i + " ");
            }
            System.out.println();

            // 4. 이전or다음 선택 안내문과함께 입력받는다.
            System.out.println("1. 다음, 2. 이전");
            int pcmd = Integer.parseInt(scanner.nextLine());
            // 5. 1(다음)을 입력했다면, 1 [2] 3 4 5가 출력되어야한다.
            // -> 직접 그릴 수 없으니 동적으로 만들어야함.
            // --> [페이지블럭]을 바꾸면, [6]부터 7 8 9 10으로 나타나야한다.

            //10. currentPageNo 변수를  직접 바꿔주는게 아니라
            // 사용자 입력input(이전or다음) -> 증감을 시키도록 해야한다.
            if (pcmd ==1 ) {
                currentPageNo++;
                //11. 보통 ->  증감수정이 있고 난뒤, 한번더 출력해줘야한다.
                // -> (페이지를 계속 넘겨야하므로) 끝나지 않고 반복해서 보여주면서 입력
                // -> 출력부를 한번더 호출해주는게 아니라   while로 감싸준다.
                // --> 아직 break에 대한 생각은 놓아둔다. -> 12.
            }
            if (pcmd == 2) {
                currentPageNo--;
            }

        }
    }
}
