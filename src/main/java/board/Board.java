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
            //1. 새로운 명령어 대한 분기를 작성하고 함수를 작성한다.
            if (command.equals("read")) {
                readArticles();
                continue;
            }


            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void readArticles() {
        // 2. 기본적으로 뿌려지는 것을 완성해보자.
        //        상세보기할 게시물 번호를 입력해주세요 : 1
        //                ==== 1번 게시물 ====
        //        번호 : 1
        //        제목 : 제목1
        //                -------------------
        //                내용 : 안녕하세요 ~
        //                -------------------
        //                        작성자 : 익명
        //        등록날짜: 2021.03.07
        //                ===================
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        // 숫자를 받으니까 parseInt도 같이~
        // 숫자 target(id) -> 메소드 -> arraylist 속 index 찾기 / 없으면 -1로 반환해서 early return(continue)
        int target = Integer.parseInt(scanner.nextLine());
        int targetIndex = getIndexOfArticleNumber(target);

        if (targetIndex == -1) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        // id -> arraylist 속 index를 찾았다면, 꺼내야한다.
        //3. index -1이 아니라서 return 안됬다면 상세보기됨 -> 조회수 1개 올려야한다!
        // -> 조회수는 먼저 올리고 상세보기에서 올린 값을 보여줘야한다.
        Article article = articles.get(targetIndex);
        article.hit++;
        System.out.printf("==== %d번 게시물 ====\n", article.id);
        System.out.println("번호 : " + article.id);
        System.out.println("제목 : " + article.title);
        System.out.println("-------------------");
        System.out.println("내용 : " + article.body);
        System.out.println("-------------------");
        System.out.println("작성자 : " + article.writer);
        System.out.println("등록날짜 : " + article.regDate);
        System.out.println("조회수 : " + article.hit);
        System.out.println("===================");

    }

    private void makeTestData() {
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

    public void list(ArrayList<Article> list) {
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            System.out.println("번호 : " + article.id);
            System.out.println("제목 : " + article.title);
            System.out.println("작성자 : " + article.writer);
            System.out.println("등록날짜 : " + article.regDate);
            System.out.println("조회수 : " + article.hit);
            System.out.println("====================================");
        }
    }
}
