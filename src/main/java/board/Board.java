package board;

import java.util.ArrayList;
import java.util.Scanner;

// 1.데이터가 2개이상 표현되는 단위는 무조건 묶은 class로 생각한다
// -> Article class를 만든다.
public class Board {

    Scanner scanner = new Scanner(System.in);
    /// 5. 저장을 3개 list에 각각 -> 1개 list에 Class타입의 객체를 보관
    ArrayList<Article> articles = new ArrayList<>();
    //    ArrayList<String> titles = new ArrayList<>();
    //    ArrayList<String> bodies = new ArrayList<>();
    //    ArrayList<Integer> numbers = new ArrayList<>();
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
                //13. 구조화 완료후  help에 기능 추가해주기
                System.out.println("add  : 게시물 등록");
                System.out.println("list : 게시물 목록 조회");
                System.out.println("update : 게시물 수정");
                System.out.println("delete : 게시물 삭제");
                //14. 앞으로 만들 검색
                System.out.println("search : 게시물 검색");

                continue;
            }
            if (command.equals("add")) {
                System.out.print("제목을 입력해주세요 : ");
                String title = scanner.nextLine();
                System.out.print("내용을 입력해주세요 : ");
                String body = scanner.nextLine();
                // 2. 데이터보관용 class가 만들어졌다면, 인스턴스를 1개 만든 뒤,
                // add: 기존 개별 데이터들을 한꺼번에 담는다.
                // -> 아직 private 변수 -> 생성자 초기화 -> getter, setter로 접근 미완성
                // -> public한 인스턴스변수들이라 바로 대입 시킨다.
                //                Article article = new Article();
                //                article.id = no;
                //                article.title = title;
                //                article.body = body;
                // 3. 객체 생성후 데이터 초기화는 너무 귀찮으므로, 생성자를 정의하여
                // 객체 생성시 다 넣도록 수정하자.
                // ** ctrl + . 생성자 신공 **
                // new Article(no, title, body);
                Article article = new Article(no, title, body);
                System.out.println("게시물이 저장되었습니다.");

                // 4. 이제 데이터 묵음을 묶더라도 저장은 arraylist<Class>에 해줘
                // -> 순서대로 저장된다.
                // titles.add(title);
                // bodies.add(body);
                // numbers.add(no);
                articles.add(article);
                // 6. 이제 자료구조를 완전히 바꾼만큼. 빨간줄 뜨는 것들을 다 처리해준다.

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

                int targetIndex = getIndexOfArticleNumber(target);

                if (targetIndex == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

//                System.out.println("수정할 게시물 index : " + targetIndex);
                System.out.print("제목 : ");
                String title = scanner.nextLine();
                System.out.print("내용 : ");
                String body = scanner.nextLine();
                //7. 수정도 마찬가지 articles의 index1개만 수정시켜주면 된다.
                // -> 이 때, 수정된 값이 아니라 수정된 객체가 들어가야한다.
                // -> **수정용 인스턴스를 또 하나 만들어야함**
                // -> **수정이란? 기존 것은 덮어쓰기되서 삭제당함.**
                Article article = new Article(targetIndex, title, body);
                articles.set(targetIndex, article);
//                titles.set(targetIndex, title);
//                bodies.set(targetIndex, body);
                System.out.println("수정이 완료되었습니다.");

                list();

                continue;
            }

            if (command.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int target = Integer.parseInt(scanner.nextLine());
                int targetIndex = getIndexOfArticleNumber(target);

                if (targetIndex == -1) {
                    System.out.println("없는 게시물 번호입니다.");
                    continue;
                }
                //8.
                articles.remove(targetIndex);
//                titles.remove(targetIndex);
//                bodies.remove(targetIndex);
//                numbers.remove(targetIndex);
                System.out.println("삭제가 완료되었습니다.");

                list();

                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private int getIndexOfArticleNumber(int target) {
        //9. 검색하는 데이터배열(이 바껴서)의 기준이 바뀜
//        for (int i = 0; i < numbers.size(); i++) {
        for (int i = 0; i < articles.size(); i++) {
            // 10. **articles에서 get하면 article이 나오므로.. 내부데이터까지 한번 더 가야함**
//            if (numbers.get(i) == target) {
            Article currentArticle = articles.get(i);
            if (currentArticle.id == target) {
                return i;
            }
        }
        return -1;
    }

    public void list() {
        //11.
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
//            int number = numbers.get(i);
//            String title = titles.get(i);
//            String body = bodies.get(i);
            System.out.println("번호 : " + article.id);
            System.out.println("제목 : " + article.title);
            System.out.println("내용 : " + article.body);
            System.out.println("====================================");
        }
    }
}
