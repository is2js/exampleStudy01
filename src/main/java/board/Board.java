package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    // 8. memeber저장용 db
    ArrayList<Member> members = new ArrayList<>();
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
            if (command.equals("read")) {
                readArticles();
                continue;
            }
            // 1. 로그인을 안하면, 글쓰기 버튼 자체가 안뜬다. -> 작성자가 있으려면, 로그인부터 해야한다.
            // -> 비로그인: 익명? / -> 로그인: 작성자 입력 구분해야한다?

            // 2.회원을 저장해놓진 말고, 일단은 가입 폼(형태)만 작성해보자.
            if (command.equals("signup")) {
                signup();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void signup() {
        // 3. 저장은 db없을 때는 -> add(추가)다.
        // -> addAtricle() 코드를 복사해 와서 수정해서 작성한다.
        // -> atricle에 add 해줄 title, body, 등을 -> loginId, loginPw,로 바꿔서 add(저장)해야한다.
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String loginPw = scanner.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = scanner.nextLine();

        // 4. 자 이제, Article이 아닌 저장할 데이터용 Class를 만들어야한다.
        // -> 데이터들은 Class로 묶어서 관리해야한다.
        // -> Member class를 만들자. Article과는 전혀다른 개념이므로, 데이터를 추가하는게 아니라 새로 만들어야한다.
        // -> 서로 다른 하나의 개념 -> class로 표현

        // 6. class생성후, 해당 class의 구조체에 데이터를 넣어준다.
        // - 가입일자는 생략한다.
        // String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
        Member member = new Member(loginId, loginPw, nickname);
        // 7. 이제 articles같은, member저장용  arrayulist를 위에 만들어주고 오자.
        // 9.
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
        //        no++; // 회원 고유번호는 따로 세지 않는다.

    }

    private void readArticles() {
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        int target = Integer.parseInt(scanner.nextLine());
        int targetIndex = getIndexOfArticleNumber(target);

        if (targetIndex == -1) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
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

        ArrayList<Article> searchedArticles = new ArrayList();

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
