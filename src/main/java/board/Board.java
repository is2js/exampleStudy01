package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board extends Main {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Reply> replies = new ArrayList<>();
    int articleNumber = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
    int replyNumber = 1; // 댓글 고유번호
    Member loginedMember = null;
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
        printArticle(article);

        readProcess(article);
    }

    private void printArticle(Article article) {
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
        System.out.println("======= 댓글 ======");
        for (Reply currentReply : replies) {
            if (currentReply.parentId == article.id) {
                // 9. 다형성으로 리팩토링시 문제점은, 상카객체를 return하는 경우, 하카객체 자리에 바로 못들어간다.
                // -> BaseInfo 자리엔 Reply가 들어가지만,  Reply = 자리엔, Baseinfo를 넣으려면 형변환을 해야한다.
                // -> 호.. 다형성 리팩토링시, 상카객체를 return하는 메소들도 있으니, 메소드 호출처에서  = (작은것으로 형변환)은 필수적으로 들어간다.
                // currentReply = setNickname(currentReply);
                currentReply = (Reply) setNickname(currentReply);
                System.out.println("내용 : " + currentReply.body);
                System.out.println("작성일 : " + currentReply.regDate);
                System.out.println("===================");
            }
        }
    }

    // 6.
    // private Reply setReplyNickName(Reply currentReply) {
    //     if (currentReply != null) {
    //         Member writer = getMemberByMemberId(currentReply.memberId);
    //         currentReply.nickname = writer.nickname;
    //     }
    //     return currentReply;
    // }

    private void readProcess(Article article) {
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = Integer.parseInt(scanner.nextLine());
            if (readCommand == 5) {
                break;
            }
            if (readCommand == 1) {
                reply(article);
                continue;
            }
            if (readCommand == 2) {
                System.out.println("[좋아요 기능]");
                continue;
            }
        }
    }

    private void reply(Article article) {
        System.out.print("댓글 내용을 입력해주세요 : ");
        String rbody = scanner.nextLine();
        int memberId = loginedMember.id;
        String regDate = MyUtil.getCurrentDate(dateFormat);
        Reply reply = new Reply(replyNumber, article.id, rbody, memberId, regDate);
        replies.add(reply);
        replyNumber++;
        System.out.println("댓글이 등록되었습니다.");

        printArticle(article);
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate(dateFormat);
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

        targetArticle = (Article) setNickname(targetArticle);
        return targetArticle;
    }

    // 5. 개념설명: (공통변수 혹은 )공통기능 뿐만 아니라 **하카 객체들이 비슷하게 사용(파라미터등에서)되는 타Class 메소드들을 -> 다형성으로 처리해준다.**
    // 1) 공통변수 및 공통기능 뽑아내서 상카Class생성후 상속은 -> 하카 객체를 묶어줄 다형성 처리를 위한 발판임.
    // 2) 주로 하위카테고리 객체들이 공통적으로 사용(주로 파라미터로)되는 곳을 묶어서 1개로 만든다.
    // -> setArticleNickname(하카 객체1) 과 setReplyNickname(하카 객체2) 를 통합할 것이다.
    // -> 1개의 메소드를 복붙해서 다형성 통합 메서드를 만들 준비한다.
    // --> setArticleNickname() 복붙 -> setArticleNickname() 하나주석 + setReplyNickname()도 주석

    // private Article setArticleNickname(Article targetArticle) {
    //     if (targetArticle != null) {
    //         Member writer = getMemberByMemberId(targetArticle.memberId);
    //         targetArticle.nickname = writer.nickname;
    //     }
    //     return targetArticle;
    // }

    // 7. 메소드명에서 Article 단어를 지운다.
    // private Article setNickname(Article targetArticle) {
    // 8. 이제 하카객체 Article 파라미터 -> 하카 객체들 모두 받을 수 있게 상카Class, BaseClass객체를 받도록 한다  by 다형성
    // private Article setNickname(Article targetArticle) {
    // -> 그이후 빨간줄 나는 것들을 다 수정해준다.
    private BaseInfo setNickname(BaseInfo info) {
        if (info != null) {
            Member writer = getMemberByMemberId(info.memberId);
            info.nickname = writer.nickname;
        }
        return info;
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
