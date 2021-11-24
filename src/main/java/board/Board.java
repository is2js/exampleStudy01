package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    //2. 테스터데이터 넣어준만큼 시작을 뒤로.
//    int no = 1;
    int no = 1 + 3; // TODO: 테스트끝나면 +3삭제

    // 5. 인변의 초기화가 아니라, 인메사용 직전에, 인메 초반부 데이터 삽입코드를 인메사용용 개체 생성시 생성자에 넣어준다.
    // -> 인메run()에 대한 초기코드를 인메run()사용을 위한 객체 생성시 수행하게 하여
    // -> 인메run()의 첫부분을 더 앞으로 빼준다.
    public Board() {
        makeTestData();
    }

    public void runBoard() {
        //1. 인메에서 사용된 인변에 미리 넣어두려면,
        // -> 인메내부에서 시작할때 넣어줘야한다.
        // -> 그리고 **넣어준만큼 인변 - 데이터시작번호no를 옮겨준다.**
        //3. 테스트데이터 add과정을 method화 한다.
//        makeTestDate();
        //4. 메소드의 시작에 넣어야하는데, 메소드 메인로직을 가리므로,
        // -> 인메의 첫시작부분은 인메사용을 위한 객체생성시 생성자에 넣어준다.!!
        // -> 대박개념..


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
            if (command.equals("search")) {
                searchArticles();
                continue;
            }


            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void makeTestData() {
        articles.add(new Article(1, "안녕하세요", "내용1입니다."));
        articles.add(new Article(2, "반갑습니다.", "내용2입니다."));
        articles.add(new Article(3, "안녕안녕", "내용3입니다."));
    }


    private void searchArticles() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scanner.nextLine();

        ArrayList searchedArticles = new ArrayList();

        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).title.contains(keyword)) {
                searchedArticles.add(articles.get(i));
            }
        }
        list(searchedArticles);

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
