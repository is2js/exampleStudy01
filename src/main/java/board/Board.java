package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
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

        Member member = new Member(memberNumber, loginId, loginPw, nickname);
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
        memberNumber++; //
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

        //4. **길어지면서 & 먼가 성격이 조금 바뀌고, 계층이 달라진다면 -> method로 빼주자.**
        // -> 작성하다가, 길어지면 메뉴+입력대기 시작부터 싹다 잘라내서 메소드로뺀다.
        readProcess();

        // // 1. 상세보기는, 다 끝난 뒤, 영역끝 -> 함수끝나기 전에 한번더 물어보면서 입력을 받아줘야한다.
        // // - 대기는 입력받는 것으로 인해 생기니까 **함수끝나기전 안내문+대기** 걸어줘야 메뉴가 생긴다.
        // System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        // //2. only 문자열 숫자만 들어온다고 가정하면, 바로 파싱한다.
        // int readCommand = Integer.parseInt(scanner.nextLine());
        // //3. 각 메뉴는 분기를 해줘야한다.
        // // -> 각 분기는 print로만 채워둔다.
        // if (readCommand == 1) {
        //     System.out.println("[댓글 기능]");
        // }
        // if (readCommand == 2) {
        //     System.out.println("[좋아요 기능]");
        // }
        // if (readCommand == 5) {
        //     // 종료
        // }
    }

    //5. 메소드로 1,2,5 3개분기만 뺀 상태에서 테스트해보자.
    private void readProcess() {
        //6. 메뉴+진입 -> 테스트가 완료되었을 때 -> while문에서 무한반복으로 계속 띄워주게 하자.
        // System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        // int readCommand = Integer.parseInt(scanner.nextLine());
        // if (readCommand == 1) {
        //     System.out.println("[댓글 기능]");
        // }
        // if (readCommand == 2) {
        //     System.out.println("[좋아요 기능]");
        // }
        // if (readCommand == 5) {
        // }
        //7. whlie true로 시작 -> 5번 분기가 탈출조건으로 있으니까 break를 걸어준다. 그외 분기는 coninue
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = Integer.parseInt(scanner.nextLine());
            if (readCommand == 5) {
                break; // 무한반복시 먼저 해줘야할 break; 조건절이 분기중 1개임.
            }
            if (readCommand == 1) {
                System.out.println("[댓글 기능]");
                continue;
            }
            if (readCommand == 2) {
                System.out.println("[좋아요 기능]");
                continue;
            }
        }
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
        articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, 1,  0));
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

        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
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

        if ( targetArticle != null ) {
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
