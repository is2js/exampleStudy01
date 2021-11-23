package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    public void runBoard() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> bodies = new ArrayList<>();
        // 6. autoincrement no를 저장할 list도 만들어주기
        ArrayList<Integer> numbers = new ArrayList<>();
        // 4. 게시물 고유번호 1부터해서 add호출시마다 1씩 증가 update
        // -> 반복문 밖에서 초기화하기
        int no = 1;

        while (true) {
            System.out.print("명령어를 입력해주세요 : ");
            String command = scanner.nextLine();

            if (command.equals("q")) {
                System.out.println("종료되었습니다.");
                break;
            }

            if (command.equals("help")) {
                System.out.println("add  : 게시물 등록");
                System.out.println("list : 게시물 목록 조회");
                continue;
            }
            if (command.equals("add")) {
                // 3. 게시물 고유번호는, 1부터 중복안되게 자동으로 1개씩 증가시키면 된다.
                // -> 1) int no = 1; 반복할때마다 초기화 안되게, 밖에서 선언해주고 반복-add시맏 update만 되도록 밖으로 빼기
                // 5. -> 2) 갔다와보니.. 1씩 증가하는 no를 저장할 ArrayList도 따로.. -> 생성하고 오기
                System.out.print("제목을 입력해주세요 : ");
                String title = scanner.nextLine();
                System.out.print("내용을 입력해주세요 : ");
                String body = scanner.nextLine();
                titles.add(title);
                bodies.add(body);
                System.out.println("게시물이 저장되었습니다.");
                // 7. 게시물 저장시, 해당 번호도 저장해주고, -> +1증가시키기
                // -> 조회(list)에서 번호도 수정해주자.
                numbers.add(no);
                no++;
                continue;
            }
            if (command.equals("list")) {
                System.out.println(">>>>>>>>" + titles.size());
                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i);
//                    String body = bodies.get(i);
                    // 8. 번호를 index- > 고유번호 numbers에서 꺼내오기
                    int number = numbers.get(i);
                    //                    System.out.println("번호 : " + (i + 1));
                    System.out.println("번호 : " + number);
                    System.out.println("제목 : " + title);
                    // 9. body는 안보여주기
                    //                    System.out.println("내용 : " + body);
                    System.out.println("====================================");
                }
                continue;
            }
            // 1. update(수정)은 id값을 입력받아서 특정 게시물만 수정해야한다.
            // -> index가 아닌 고유번호다 앞에께 삭제되더라도 땡겨가는 일은 없어야한다.
            //            명령어를 입력해주세요 : update
            //            수정할 게시물 번호 : 3
            //            없는 게시물 번호입니다.
            //                    명령어를 입력해주세요 : update
            //            수정할 게시물 번호 : 1
            //            제목 : 새제목
            //            내용 : 새내용
            //            수정이 완료되었습니다.
            //            번호 : 1
            //            제목 : 새제목
            if (command.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
                // 2. 숫자가 필요할땐 nextLine -> parseInt
                // -> 게시물번호는 index가 아니다! 고유번호가 필요하다.
                // -> add할 때, 데이터로서 추가해줘야한다. add로 갔다오자.
                int target = Integer.parseInt(scanner.nextLine());

                // 10. 게시물을 입력받은 고유번호로 검색할건데,
                // **어떤 값이 배열에 어딨는지는 index가 아닌 이상  for문으로 검색해봐야 안다.**
                // System.out.println("검색안하고 list.indexOf()로 index찾기 >>> " +
                // numbers.indexOf(target));
                // -> 우리가 찾는 것은 index -> **반복문내에서 찾아서 건져내야하기 때문에. 또 변수 생성. ㅠ
                int targetIndex = -1; // 조심!! 초기화를 0으로 하면 안됨. 0은 의미 있는 index번호임.
                for (int i = 0; i < numbers.size(); i++) {
                    if (numbers.get(i) == target) {
                        targetIndex = i;
                        break; // 찾았으면 break로 빠져나와야한다.
                    }
                }
                // 11. **targetIndex를 못찾을 수 도 있다.**
                // -> for문으로 배열속 값 찾기시 -> 값을 못찾았을 수도 있다 -> 초기화 값 활용하기
                // -> 못찾았으면 if not return, continue, break;와 마찬가지.
                if (targetIndex == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }
                // 12. 해당 게시물에 대한 index를 찾았으면, 새내용을 입력받아서  수정(set)해야한다.
                System.out.println("수정할 게시물 번호 : " + targetIndex);
                System.out.print("제목 : ");
                String title = scanner.nextLine();
                System.out.print("내용 : ");
                String body = scanner.nextLine();
                // arraylist.set(index, value)으로 수정해주기
                titles.set(targetIndex, title);
                bodies.set(targetIndex, body);
                System.out.println("수정이 완료되었습니다.");
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }
}
