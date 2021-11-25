package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    int no = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    Member loginedMember = null;

    public Board() {
        makeTestData();
    }

    public void runBoard() {
        while (true) {
            if (loginedMember == null) {
                System.out.print("명령어를 입력해주세요 : ");
            } else {
                System.out.print(
                        "명령어를 입력해주세요["
                                + loginedMember.nickname
                                + "("
                                + loginedMember.loginId
                                + ")] : ");
            }
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
            if (command.equals("signup")) {
                signup();
                continue;
            }
            if (command.equals("login")) {
                login();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void login() {
        System.out.print("아이디 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호 : ");
        String loginPw = scanner.nextLine();
        boolean isExistLoginId = false;

        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.loginId.equals(loginId) && member.loginPw.equals(loginPw)) {
                isExistLoginId = true;
                System.out.println(member.nickname + "님 안녕하세요.!");
                loginedMember = member;
                break;
            }
        }
        if (!(isExistLoginId)) {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
        }
    }

    private void signup() {
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String loginPw = scanner.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = scanner.nextLine();

        Member member = new Member(loginId, loginPw, nickname);
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
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
        // 2. 게시글 작성자를 "익명" -> 특정회원으로 바꿔주자..?!
        // + 함수를 인자에서 호출하지말고, 변수로 빼서 최적화시켜주자.
        // -> test
        // 명령어를 입력해주세요 : login
        // 아이디 : aaa
        // 비밀번호 : aaa
        // 조재성님 안녕하세요.!
        // 명령어를 입력해주세요[조재성(aaa)] :
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, "조재성", 0));
        articles.add(new Article(2, "반갑습니다.", "내용2입니다.", currentDate, "김석영", 0));
        articles.add(new Article(3, "안녕안녕", "내용3입니다.", currentDate, "조재성", 0));

        // 1. 이 것은 run메서드()가 실행되기 직전, run메서드()실행을 위한 객체 생성시 생성자에서 작동한다.
        members.add(new Member("aaa", "aaa", "조재성"));
        members.add(new Member("bbb", "bbb", "김석영"));

        // 3. 로그인 하는 것도 귀찮으니 `메서드 기능구현용객체. 생성자.`에서 초기데이터 넣어주던 메소드(makeTestData)에
        // -> `loginedMember = `를 초기 데이터에서 1개 꺼내 넣어주기
        loginedMember = members.get(0);
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
