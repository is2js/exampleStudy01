package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    //13.
    ArrayList<Reply> replies = new ArrayList<>();
    int articleNumber = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
    //10. id는 run메소드() 시작시부터 관리되도록 인스턴스변수로 1부터 시작시킨다~
    int replyNumber = 1; // 댓글 고유번호
    Member loginedMember = null;
    // 7. 유틸메소드의 default인자를 변수로 미리 선언해놓기 + (내생각엔 format파라미터로 받앗는데 너무 하드코딩반복 -> default값으로서 변수로 ->
    // -> 바뀌면 바꿔서 넣으면 됨.)
    String dateFormat = "yyyy.MM.dd";

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
                if (isLoginCheck() == true) {
                    addArticle();
                }
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
            if (command.equals("logout")) {
                if (isLoginCheck() == true) {
                    logout();
                }
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void logout() {
        loginedMember = null;
        System.out.println("로그아웃 되셨습니다.");
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

        Member member = new Member(memberNumber, loginId, loginPw, nickname);
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
        memberNumber++;
    }

    private void readArticles() {
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        int target = Integer.parseInt(scanner.nextLine());
        Article article = getArticleByArticleNumber(target);
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        article.hit++;
        System.out.printf("==== %d번 게시물 ====\n", article.id);
        System.out.println("번호 : " + article.id);
        System.out.println("제목 : " + article.title);
        System.out.println("-------------------");
        System.out.println("내용 : " + article.body);
        System.out.println("-------------------");
        System.out.println("작성자 : " + article.nickname);
        System.out.println("등록날짜 : " + article.regDate);
        System.out.println("조회수 : " + article.hit);
        System.out.println("===================");

        readProcess();
    }

    private void readProcess() {
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = Integer.parseInt(scanner.nextLine());
            if (readCommand == 5) {
                break;
            }
            if (readCommand == 1) {
                // 1. 댓글등록 [기능] -> [메소드]로 따로 빼면서 구현한다.
                // System.out.println("[댓글 기능]");
                reply();
                continue;
            }
            if (readCommand == 2) {
                System.out.println("[좋아요 기능]");
                continue;
            }
        }
    }

    private void reply() {
        // 2. 입력부터 안내문과 함께 요구한다. -> 입출력부터 완성한다. -> 그담이 기능
        System.out.print("댓글 내용을 입력해주세요 : ");
        String rbody = scanner.nextLine(); // 댓글내용 : reply body
        // 3. 기능으로서 받은 댓글을 저장해야한다.
        // -> db대신  arraylist를 또 생성해야함.
        // -> **Article(게시물)에 댓글 저장한다는 생각을 버려라.**
        // -> 1개 Article당 여러개의 댓글이 달리므로. 따로 저장하는데, 연관성을 나타내는 변수를 One to Many에게 Fk를 줘야함.
        // -> **댓글이 rbody만 받았다고 해서 1개의 데이터로 이루어진게 아니다!**
        // -> 출력할 때 보면, 번호, 작성자,내용, 작성일, 등 여러정보가 있다. (최소3개)
        // -> 등록할 데이터를 위해 Reply 데이터 생성한다. -> 4.

        // 6. Reply를 등록하면 4개의 데이터필요 -> rbody 1개밖에 안받음.
        // -> 나머지 3개를 구해오자.
        // 6-1) memberId는 -> 로그인된 정보에서 가져온다.
        int memberId = loginedMember.id;
        // 6-2) regDate는? -> MyUtil의 공유목적의 유틸메소드를 활용한다.
        // -> 파라미터로 포맷을 주는데, **이참에 메소드에 default포맷을 넣어주자.**
        // -> **python과 달리, 파라미터의 default값의 메소드선언부가 아닌, 사용처에서 변수로 생성한다.
        // -> my) 파라미터로 계속 반복해서 들어가니 , 값의 반복 -> 변수(절대 안변하면 상수)로 빼주는 방식을 이용하는 개념도 포함된듯?
        // -> 7. String dateFormat =
        // 8.
        // String regDate = MyUtil.getCurrentDate("yyyyMMdd");
        // -> 이제 getCurrentDate() 사용된 곳 찾아가서 다  "하드코딩" -> 변수 처리해주자.
        // -> ctrl+f -> f3으로 하나씩 찾아가기 or F12 2번씩 눌러서 하나하나 방문하기
        String regDate = MyUtil.getCurrentDate(dateFormat);

        // 9.( 6-3)변수 )마지막 댓글의 id도, 자동으로 1씩 증가하도록 1시작 -> add마다 ++1; 되도록 [변수]관리해주자.
        // -> addArticle때 처럼 1개 마련해놓고 add마다 1씩 증가시키기

        //11. 이제  파편의 데이터들 4개를 new Reply에 묶어서 저장하고, -> 댓글id+1 해준다.
        // new Reply(replyNumber, rbody, memberId, regDate);
        Reply reply = new Reply(replyNumber, rbody, memberId, regDate);
        //12. 댓글db를 만들어서 add해준다.
        replies.add(reply);
        //14. 테스트
        replyNumber++;
        System.out.println("댓글이 등록되었습니다.");
        //15. 정말 댓글이 등록되었는지 알기 위해, 댓글이 추가된 상세화면을 출력하라고 요구사항에 적혀있다.
        // -> 다음에 구현
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate(dateFormat); // 이참에 default 파라미터를 사용해준다.
        articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, 1, 0));
        articles.add(new Article(2, "반갑습니다.", "내용2입니다.", currentDate, 2, 0));
        articles.add(new Article(3, "안녕안녕", "내용3입니다.", currentDate, 1, 0));

        members.add(new Member(1, "aaa", "aaa", "조재성"));
        members.add(new Member(2, "bbb", "bbb", "김석영"));

        loginedMember = members.get(0);
    }

    private void searchArticles() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scanner.nextLine();

        ArrayList<Article> searchedArticles = new ArrayList<>();

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
        Article article = getArticleByArticleNumber(target);

        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        articles.remove(article);
        System.out.println("삭제가 완료되었습니다.");

        list(articles);
    }

    private void updateArticle() {
        System.out.print("수정할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());
        Article article = getArticleByArticleNumber(target);
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String body = scanner.nextLine();
        System.out.println("수정이 완료되었습니다.");

        article.title = title;
        article.body = body;
        System.out.println("수정이 완료되었습니다.");

        list(articles);
    }

    private void addArticle() {
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        String currentDate = MyUtil.getCurrentDate(dateFormat);
        Article article = new Article(articleNumber, title, body, currentDate, loginedMember.id, 0);
        System.out.println("게시물이 저장되었습니다.");

        articles.add(article);
        articleNumber++;
    }

    private boolean isLoginCheck() {
        if (loginedMember == null) {
            System.out.println("로그인이 필요한 기능입니다.");
            return false;
        }
        return true;
    }

    private void printHelp() {
        System.out.println("add  : 게시물 등록");
        System.out.println("list : 게시물 목록 조회");
        System.out.println("update : 게시물 수정");
        System.out.println("delete : 게시물 삭제");
        System.out.println("search : 게시물 검색");
    }

    private Article getArticleByArticleNumber(int target) {
        Article targetArticle = null;
        for (int i = 0; i < articles.size(); i++) {
            Article currentArticle = articles.get(i);
            if (currentArticle.id == target) {
                targetArticle = currentArticle;
                break;
            }
        }

        if (targetArticle != null) {
            Member writer = getMemberByMemberId(targetArticle.memberId);
            targetArticle.nickname = writer.nickname;
        }
        return targetArticle;
    }

    private Member getMemberByMemberId(int memberId) {
        Member targetMember = null;
        for (int i = 0; i < members.size(); i++) {
            Member currentMember = members.get(i);
            if (memberId == currentMember.id) {
                targetMember = currentMember;
                break;
            }
        }
        return targetMember;
    }

    public void list(ArrayList<Article> list) {
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            System.out.println("번호 : " + article.id);
            System.out.println("제목 : " + article.title);
            System.out.println("작성자 : " + article.memberId);
            System.out.println("등록날짜 : " + article.regDate);
            System.out.println("조회수 : " + article.hit);
            System.out.println("====================================");
        }
    }
}
