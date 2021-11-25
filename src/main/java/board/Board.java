package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    //3. 게시물처럼, 데이터저장시 1부터 +1씩 되도록, 관리해주는 변수가 필요하다.
    // -> 게시물 관리 변수: no -> articleNumber로 변경
    // int no = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int articleNumber = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
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

        //4. 이제 멤버데이터를 넣어줄 때마다, 자동증가고유번호도 넣어주면서 -> 넣고나서 ++
        // Member member = new Member(loginId, loginPw, nickname);
        Member member = new Member(memberNumber, loginId, loginPw, nickname);
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
        memberNumber++; //
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
        //11. 변경 -> test -> 여기까지 하면, 게시글의 작성자가 writer에서 memberId로 바뀐 것 처럼 출력될 것이다.
        // System.out.println("작성자 : " + article.writer);
        // 명령어를 입력해주세요[조재성(aaa)] : list
        // 번호 : 1
        // 제목 : 안녕하세요
        // 작성자 : 1
        // 등록날짜 : 2021.11.25
        // 조회수 : 0
        //     ====================================
        System.out.println("작성자 : " + article.memberId);
        System.out.println("등록날짜 : " + article.regDate);
        System.out.println("조회수 : " + article.hit);
        System.out.println("===================");
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        // 8. article이 바뀜에 따라, 테스트add시에도 바뀌어야한다.
        // -> nickname이나 loginId가 들어가있다면 -> memberId번호로 바꿔주자.
        // ex> "조재성" -> 1
        // articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, "조재성", 0));
        articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, 1, 0));
        articles.add(new Article(2, "반갑습니다.", "내용2입니다.", currentDate, 2, 0));
        articles.add(new Article(3, "안녕안녕", "내용3입니다.", currentDate, 1, 0));

        // 5. 테스트 member 2개가 id 1, 2번을 가져갔다. -> memberNumber가 1+2 -> 3번부터 올라가는 이유.
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
        // 9. update시 새로운 new Article객체를.. 만들어서 넣어줘야하는데,
        // -> 진입필터링 이후, 로그인된 상태 보장이 있어야함 -> 일단 주석처리하고 보류한다.
        // Article article = new Article(target, title, body, "2021.11.11", "익명", 0);
        //articles.set(targetIndex, article);

        System.out.println("수정이 완료되었습니다.");
        list(articles);
    }

    private void addArticle() {
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        //6. add시 Article에 멤버의 nickname, loginId가 아니라 고유번호를 넣도록 수정 -> Article 구조도 변경
        // -> 여기 수정후, Article class도 수정해주자.
        // Article article = new Article(articleNumber, title, body, currentDate, loginedMember.loginId, 0);
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
            // System.out.println("작성자 : " + article.writer);
            //10. 변경
            System.out.println("작성자 : " + article.memberId);
            System.out.println("등록날짜 : " + article.regDate);
            System.out.println("조회수 : " + article.hit);
            System.out.println("====================================");
        }
    }
}
