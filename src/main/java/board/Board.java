package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    int no = 1 + 3;

    public Board() {
        makeTestData();
    }

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
            if (command.equals("search")) {
                searchArticles();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void makeTestData() {
        // 2. 조회수는 0으로, 날짜는 "2021.11.11"형식으로 일단 넣어준다.
        // 13. 테스트데이터도, run()실행용 객체가 생성될 떄 생성자에서 추가되는데, 실행되는 날짜로서 추가해준다.
        articles.add(
                new Article(1, "안녕하세요", "내용1입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
        articles.add(
                new Article(2, "반갑습니다.", "내용2입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
        articles.add(
                new Article(3, "안녕안녕", "내용3입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
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
        // 3. 추가나, 수정시 필요한 데이터들도 일단은, 받기전에 테스트데이터를 넣어준다.
        // "2021.11.11", "익명", 0
        Article article = new Article(target, title, body, "2021.11.11", "익명", 0);
        articles.set(targetIndex, article);
        System.out.println("수정이 완료되었습니다.");

        list(articles);
    }

    private void addArticle() {
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        // 12. add할 때, 테스트 데이터 -> 현재날짜를 문자열로 받아와서 추가해준다.
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        Article article = new Article(no, title, body, currentDate, "익명", 0);
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

    // 4. 출력이자 조회 함수에다가, 추가데이터를 출력하도록 추가해준다.
    //    작성자 : 익명
    //    등록날짜 : 2020.10.22
    //    조회수 : 20
    public void list(ArrayList<Article> list) {
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            System.out.println("번호 : " + article.id);
            System.out.println("제목 : " + article.title);
            //            System.out.println("내용 : " + article.body);
            System.out.println("작성자 : " + article.writer);
            System.out.println("등록날짜 : " + article.regDate);
            System.out.println("조회수 : " + article.hit);
            System.out.println("====================================");
        }
    }
}
