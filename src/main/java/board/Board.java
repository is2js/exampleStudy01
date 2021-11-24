package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
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
                printHelp();
                continue;
            }
            if (command.equals("add")) {
                addArticle();
                continue;
            }
            if (command.equals("list")) {
                list(articles);
                continue;
            }
            if (command.equals("update")) {
                updateArticle();
                continue;
            }

            if (command.equals("delete")) {
                deleteArticle();
                continue;
            }
            //1. run()메소드가 리팩토링되었다면, 계속 리팩토링하도록 작성하자.
            if (command.equals("search")) {
                //2. 검색의 결과는 복수개가 나올수 잇으니 복수로 메소드명을 짓는다!!
                // -> 무한반복 분기마다 early continue를 마지막에 달아주자! 예외만 맨 마지막에 남음
                searchArticles();
                continue;
            }


            System.out.println("잘못 입력하였습니다.");
        }
    }


    private void searchArticles() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scanner.nextLine();

        //7.
        ArrayList searchedArticles = new ArrayList();

        //2. 검색하려면, 반복문으로 articles(arraylist) 싹다 뒤져봐야한다.
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).title.contains(keyword)) {
                //3. 원하는 키워드를 포함했다면, 출력? 모아두기? -> 일단 출력해주자.
                // -> 일부 list를 출력/조회하는 함수가 없기 때문이다.
//                System.out.println("번호 : " + articles.get(i).id);
//                System.out.println("제목 : " + articles.get(i).title);
//                System.out.println("내용 : " + articles.get(i).body);
//                System.out.println("====================================");
                // -> test
                //4. 이제 조회 list()함수를.. arraylist전체가 아닌...
                // -> 여기서도 재활용할 수 있게 <파라미터로 Arraylist<Class>를 받아, 전체list가 아닌 일부list도 전체 다 출력되게> 리팩토링한다.

                //6. 이제 일부 article list도 조회(출력)할 수 있게 파라미터를 심었으니,
                // -> 검색된 article을 바로 출력 X -> 모아서 list로 건네주자.
                searchedArticles.add(articles.get(i));
            }
        }
        // 8. 검색된 결과를 list()조회->출력함수에 넘겨준다.
        // -> 결과가 없어도 .size() 0처리되서 반복문이 안도니까 괜찮다.
        list(searchedArticles);
        //

    }

    private void deleteArticle() {
        System.out.print("삭제할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());
        int targetIndex = getIndexOfArticleNumber(target);

        if (targetIndex == -1) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        articles.remove(targetIndex);
        System.out.println("삭제가 완료되었습니다.");

        list(articles);
    }

    private void updateArticle() {
        System.out.print("수정할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());

        int targetIndex = getIndexOfArticleNumber(target);

        if (targetIndex == -1) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String body = scanner.nextLine();
        Article article = new Article(target, title, body);
        articles.set(targetIndex, article);
        System.out.println("수정이 완료되었습니다.");

        list(articles);
    }

    private void addArticle() {
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();
        Article article = new Article(no, title, body);
        System.out.println("게시물이 저장되었습니다.");

        articles.add(article);
        no++;
    }

    private void printHelp() {
        System.out.println("add  : 게시물 등록");
        System.out.println("list : 게시물 목록 조회");
        System.out.println("update : 게시물 수정");
        System.out.println("delete : 게시물 삭제");
        System.out.println("search : 게시물 검색");
    }

    private int getIndexOfArticleNumber(int target) {
        for (int i = 0; i < articles.size(); i++) {
            Article currentArticle = articles.get(i);
            if (currentArticle.id == target) {
                return i;
            }
        }
        return -1;
    }

    //5. <내부에 반복문을 도는 조회함수>는 조회시 길이가변을 자동처리하도록 ArrayList 파라미터로 받게 한다.
    // -> 전체 list(articles), 일부list(articles), 정렬된list, 1개, 0개 다 가능해진다.
//    public void list() {
    public void list(ArrayList<Article> list) {
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            System.out.println("번호 : " + article.id);
            System.out.println("제목 : " + article.title);
            System.out.println("내용 : " + article.body);
            System.out.println("====================================");
        }
    }
}
