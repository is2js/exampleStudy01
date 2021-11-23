package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> bodies = new ArrayList<>();
    ArrayList<Integer> numbers = new ArrayList<>();
    int no = 1;

    public void runBoard() {

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
                System.out.print("제목을 입력해주세요 : ");
                String title = scanner.nextLine();
                System.out.print("내용을 입력해주세요 : ");
                String body = scanner.nextLine();
                titles.add(title);
                bodies.add(body);
                System.out.println("게시물이 저장되었습니다.");
                numbers.add(no);
                no++;
                continue;
            }
            if (command.equals("list")) {
                list();
                continue;
            }
            if (command.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
                int target = Integer.parseInt(scanner.nextLine());

                // 3. 삭제시에도 사용되는 index검색코드를 함수화한다.
                // -> 일단은 void로  작성해두고,, 찾은 index를 돌려주도록 하자.
                // -> 찾을 때 필요한 input target번호를 넣어주자.
//                getIndexOfArticleNumber(target)
                int targetIndex = getIndexOfArticleNumber(target);

                if (targetIndex == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                System.out.println("수정할 게시물 번호 : " + targetIndex);
                System.out.print("제목 : ");
                String title = scanner.nextLine();
                System.out.print("내용 : ");
                String body = scanner.nextLine();
                titles.set(targetIndex, title);
                bodies.set(targetIndex, body);
                System.out.println("수정이 완료되었습니다.");

                // 1.list(조회)같은 경우, 자신 부피뿐만 아니라 다른데서도 많이 쓰이므로 리스트로 뺀다.
                list();

                continue;
            }

            // 2. 삭제의 경우, 수정과 비슷하지만 더 쉽다.
            // 명령어를 입력해주세요 : delete
            // 삭제할 게시물 번호 : 3
            // 없는 게시물 번호입니다.
            // 명령어를 입력해주세요 : delete
            // 삭제할 게시물 번호 : 1
            // 삭제가 완료되었습니다.
            if (command.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int target = Integer.parseInt(scanner.nextLine());
                //6. 수정과 중복되는 코드 -> 리팩토링된 코드를 활용해서 index를 찾는다.
                int targetIndex = getIndexOfArticleNumber(target);

                // 검색은 항상 못찾았을 때를 대비해야한다.
                if (targetIndex == -1) {
                    System.out.println("없는 게시물 번호입니다.");
                    continue;
                }
                //7. delete는 찾은 index로 바로 remove대리면 된다. (수정처럼 추가데이터 받지x)
                titles.remove(targetIndex);
                bodies.remove(targetIndex);
                numbers.remove(targetIndex);
                System.out.println("삭제가 완료되었습니다.");

                list();
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    //4. 결국 찾은 index를 return해줘야한다. -> 찾으면 그값, 못찾으면 default -1
    // -> for반복문으로 검색하는 코드가 메소드 안으로 들어왔다면, for에서 받검색시 찾았을 때 받아주는 변수도 필요없다 찾으면 바로 return
//    private void getIndexOfArticleNumber(int target) {
    private int getIndexOfArticleNumber(int target) {
//        int targetIndex = -1;

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == target) {
//                targetIndex = i;
                //중요!
//                break;
                return i;
            }
        }
        //중요!
//        return targetIndex;
        return -1;

    }

    public void list() {
        for (int i = 0; i < titles.size(); i++) {
            int number = numbers.get(i);
            String title = titles.get(i);
            String body = bodies.get(i);
            System.out.println("번호 : " + number);
            System.out.println("제목 : " + title);
            System.out.println("====================================");
        }
    }
}
