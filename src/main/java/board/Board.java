package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

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
        // 2. 이 아래로 코드를 복사해서 써야한다? -> 복사is중복이니
        // -> 이 아래로 메소드화시켜서 메소드자체를 가져간다.
        // -> 리팩토링 단축키(alt+ctrl+m 2번) 활용하기
        // --> **특정필드(id, number)등이 필요하면 ById, ByNumber를 메소드이름에 단다. 그게 아니면  동사+명사로**
        // System.out.printf("==== %d번 게시물 ====\n", article.id);
        // System.out.println("번호 : " + article.id);
        // System.out.println("제목 : " + article.title);
        // System.out.println("-------------------");
        // System.out.println("내용 : " + article.body);
        // System.out.println("-------------------");
        // System.out.println("작성자 : " + article.nickname);
        // System.out.println("등록날짜 : " + article.regDate);
        // System.out.println("조회수 : " + article.hit);
        // System.out.println("===================");
        printArticle(article);

        //6. printArticle -> reply -> readProcess -> readArticles까지 거슬러 올라와서
        // -> printArticle()에 필요한 인자인 상세보기 대상 article을 파라미터로 순차적으로 넘겨준다.
        //readProcess();
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
        //11. 댓글출력을 따로 할려고 하지말고, 상세글보기에 이어서 출력되게 붙혀준다.
        System.out.println("======= 댓글 ======");
        // 12. 댓글을 출력하려면, replies에서 반복문으로 싹 꺼내오면서 출력하면 된다. for문이라 없으면 안돈다.
        // -> iter로 향상된 for문이라도, 변수명은 reply -> currentreply 쓰려고 애써보자.
        // -> **일단 구현을 위해서, 각 게시글 번호에 맞는 댓글이 아니라, 모든 댓글이 뿌려지도록 한다.**
        // --> 나중에 가보면 알겠지만, 1번 게시물에서 등록한 댓글이, read 2번글 상세보기에서도 다보임.
        for (Reply currentReply : replies) {
            //15. 이제 memberId로 nickname을 받아와서 넣어주는 코드를 짜자.
            // -> 현재의 reply에 대해, reply객체를 통째로 받아서 -> memberId로 조회한 최신 nickname을 reply.nickname을 채워준다.(생성자에선 안햇었음!)
            // -> reply.memberId만 넘기지말고 객체를 통째로 넘겨줫다가, nickname이 채워진 객체를 통째로 받자!
            // -> article의 경우, article객체검색로직 -> nickname채우는 로직 -> 완성된 aritcle객체 반환이었지만
            // -> reply는 검색X 반환X -> 바로 출력하기 때문에 -> 출력직전에 nickname을 채워, 완성된 nickname이 출력되게 한다.
            currentReply = setReplyNickName(currentReply); //->  16. 메서드 작성하고 오기

            System.out.println("내용 : " + currentReply.body);
            //13. reply든, article이든, nickname대신 memberId를 가지고 있다가 -> 중복/변경가능한 nickname을 매번 조회해서 가져온다.
            // System.out.println("작성자 : " + reply.memberId);
            // -> 생성자에는 없어도, 필드로는 nickname을 가지고 있어서, 거기에 받아주는 형태를 취하자..
            // -> Reply에 nickname필드추가하기. -> 14.
            System.out.println("작성일 : " + currentReply.regDate);
            System.out.println("==================="); // 댓글끝날때마다 줄 계속 넣어주기
        }

    }

    private Reply setReplyNickName(Reply currentReply) {
        // 16. article시 작성했던 코드를 활용한다.
        // -> target article검색 직후 -> target article에 nickname을 채워주기 위한 코드였다.
        // -> 마찬가지로 **들어온 reply객체가 null이 아닐때만, 필드접근하여 reply.memberId-> reply.nickname 채우도록 해준다.**
        // -> 이 부분을..Article도,, 메소드화 시켜서, setArticleNickname해줘야할듯..

        // if (targetArticle != null) {
        //     Member writer = getMemberByMemberId(targetArticle.memberId);
        //     targetArticle.nickname = writer.nickname;
        // }
        // return targetArticle;
        if (currentReply != null) {
            Member writer = getMemberByMemberId(currentReply.memberId);
            currentReply.nickname = writer.nickname;
        }
        // return null;
        // 17. reply가 null이 들어왔음..자동으로 로직없이 종료되면 reply 그대로 반환 -> null이 반환된다.
        return currentReply; // 리팩토링 되겟지만, article에서 객체에 nickname박는 로직도 함수화해주자. -> 18.
    }

    //7. 파라미터 받기
    // private void readProcess() {
    private void readProcess(Article article) {
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = Integer.parseInt(scanner.nextLine());
            if (readCommand == 5) {
                break;
            }
            if (readCommand == 1) {
                // 4. reply()를 호출하는 readProcess()에도, 게시물 정보는 없다.
                // -> readProcess()를 호출하는 곳으로 가서, **물 흐르듯이 넘기면된다.** -> 5.
                //8. 파라미터 받기
                // reply();
                reply(article);
                continue;
            }
            if (readCommand == 2) {
                System.out.println("[좋아요 기능]");
                continue;
            }
        }
    }

    //9. 파라미터 받기
    // private void reply() {
    private void reply(Article article) {
        System.out.print("댓글 내용을 입력해주세요 : ");
        String rbody = scanner.nextLine();
        int memberId = loginedMember.id;
        String regDate = MyUtil.getCurrentDate(dateFormat);

        Reply reply = new Reply(replyNumber, rbody, memberId, regDate);
        replies.add(reply);
        replyNumber++;
        System.out.println("댓글이 등록되었습니다.");

        // 1. 댓글이 등록된 뒤 바로, 상세보기(readArticles) 중 출력해주는 부분만 다시 보여주기
        // readArticles() 전체가 아니라 출력부분만 필요함. -> 코드복사 -> 메소드화 해서 가져올 생각.

        // 3. 상세보기(readArticles())중 article객체받아 출력부분만 메소드화 시킨 것을 가져와 사용
        // -> 근데 파라미터로 들어갈 article객체가 없는 상황이다.
        // printArticle(article); // 에러
        // -> 지금까지 Article은 (index->)게시물번호로 찾았었다. 근데, reply()안에서는 번호도 없다.
        // --> **reply()를 호출하는 곳으로가서, 넘겨받도록 수정해야한다.** -> 4.
        //10. 최종적으로 파라미터를 받아왔다.
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

        // 18.
        // - 조건에 맞는 객체를 반환하는 순간에, [객체 검색로직] 직후 -> [nickname박는 로직]도 같이 넣어줘야 완성된 객체가 반환된다.
        // -> reply에서 해준 것처럼(setReplyNickName) article로 닉네임 박는 로직을 메소드화
        targetArticle = setArticleNickname(targetArticle);
        return targetArticle;
    }

    private Article setArticleNickname(Article targetArticle) {
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
