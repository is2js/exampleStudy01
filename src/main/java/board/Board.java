package board;

import java.util.ArrayList;
import java.util.Scanner;

// 0. 핵심로직이 들어갈 Board 클래스에는 public static void main은 작성 안한다.
// -> 핵심로직 클래스에선 필요한 것만 구현한다.
public class Board {

    // 1. 입출력 관련 메서드를 작성할 것인데, main없이 main에서 작동할 메서드를 만든다.
    // -> 메인에서 run할 함수를 작성하고, **먼저 무한반복문 돌면서 입력받도록 작성하자**
    public void runBoard() {
        Scanner scanner = new Scanner(System.in);
        // 4. 저장에 앞서서 받은 데이터를 자료구조에 모아야하는데
        // -> 데이터묶음은.. 클래스를 생성해서 해야하지만 일단은 arraylist로 관리해보자.
        // -> 1개 어레이리스트에 만들 수도 있지만, 제목과 내용을 찾아내기가 까다로워짐.
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> bodies = new ArrayList<>();

        while (true) {
            System.out.print("명령어를 입력해주세요 : ");
            String command = scanner.nextLine();

            if (command.equals("q")) {
                System.out.println("종료되었습니다.");
                break;
            }

            // 2. 메뉴를 뽑아주는 help(menu)부터 시작 -> add(추가) -> list(조회) 순으로 작성하면서 분기문 작성
            // -> 내용물도 help(menu) -> add -> list(조회) 순으로 채울 것이다.
            // - 출력문을 그대로 복사해와서 사용한다.
            // 명령어를 입력해주세요 : (출력) help(입력)
            // add  : 게시물 등록 (출력)
            // list : 게시물 목록 조회 (출력)
            if (command.equals("help")) {
                System.out.println("add  : 게시물 등록");
                System.out.println("list : 게시물 목록 조회");
                continue;
            }
            // 3. 로직은 add시 입력받는 title등 데이터 입력받기
            if (command.equals("add")) {
                // 단순출력문은 println , 입력받는 안내문은 print
                System.out.print("제목을 입력해주세요 : ");
                String title = scanner.nextLine();
                System.out.print("내용을 입력해주세요 : ");
                String body = scanner.nextLine();
                // 5. 받은 데이터를 자료구조에 저장한다. -> 테스트 -> list(조회) 작성하기
                titles.add(title);
                bodies.add(body);
                System.out.println("게시물이 저장되었습니다.");
                continue;
            }
            if (command.equals("list")) {
                // TODO
                // 6. 자료구조에 add한 것을 꺼내보고 테스트하자.
                // **조회는 여러개를 다 출력할 수 있을 땐, 반복문으로 꺼내야한다.**
                // -> 2개를 index순으로 돌려야하니 향상된for문x

                // 번호 : 1(출력)
                // 제목 : 안녕하세요(출력)
                // 내용 : 반갑습니다(출력)
                // ====================================(출력)
                System.out.println(">>>>>>>>" + titles.size());
                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i);
                    String body = bodies.get(i);
                    System.out.println("번호 : " + (i + 1));
                    System.out.println("제목 : " + title);
                    System.out.println("내용 : " + body);
                    System.out.println("====================================");
                }
                continue;
            }

            // 예외
            System.out.println("잘못 입력하였습니다.");
        }
    }
}
