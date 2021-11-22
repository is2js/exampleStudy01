package day03;

import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = "";
/**/
        while (true) {
            System.out.println("명령을 입력해주세요(exit:종료)");
            String command = scanner.nextLine();

            if(command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            //1. [종료/예외조건]은 필터링으로서 if early return된다 생각하고, 나머지 정상범위만 if else if else인데
            // -> 1) help명령어를 if로 시작하기
            // -> 앵간하면 각 커맨드마다 if로 분기처리 해버린다.
            if (command.equals("help")) {
                System.out.println("add: 데이터 추가");
                System.out.println("read: 데이터 조회");
                System.out.println("update: 데이터 수정");
                System.out.println("delete: 데이터삭제");
                System.out.println("exit: 프로그램종료");
                //my) 이 경우 무한반복문안에서의 건너뜀이 일어나야한다.?
                // -> 만약, 아래부분이 모두 if안에 있다면, 공통코드 없다면 안줘도된다.
                // -> 아랫부분이 [배반이면 else로 시작해서 또 경우따지기] -> 잘안쓸예정
                // -> 아랫부분에 공통코드가 있냐없냐에 따라 continue할지 /  바로 다음 루프로 가므로 continue안할지 결정됨.
//                continue;
            }
            //2. add처리 - 문자열 1개 저장할 것임.
            if (command.equals("add")) {
                // 3. 저장할 것을 받는다. 받기전엔 멘트(ln빼서 : 옆에서 바로 받게)
                // -> 여기서매번 변수생성해서 받으면 될까?
                // -> 루프안에서 변수생성은.. 다른 루프에서 못꺼내본다. -> 나중에 처리
                System.out.print("저장할 값을 입력해주세요 : ");
//                String data = scanner.nextLine();
                data = scanner.nextLine();
                // 4. 받은 것은 일단 찍어본다. -> 테스트
                System.out.println(data + "이/가 저장되었습니다.");
            }
            //5. add에 저장했던 놈을 read에서 출력해주자.
            // -> 변수를 if문안에 생성하면 안될듯싶다. -> {}중괄호가 영역이며, 지역을 의미한다.
            if (command.equals("read")) {
                System.out.println("현재 저장되어 있는 값: " + data);
            }



        }

    }
}
