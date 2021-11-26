package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board extends Main {

    Scanner scanner = new Scanner(System.in);
    ArrayList<BoardArticle> boardArticles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<ReplyArticle> replies = new ArrayList<>();
    int boardArticleNumber = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
    int replyArticleNumber = 1; // 댓글 고유번호
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
                list(boardArticles);
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
            // 12. (개념)가장 중요한 부분은 따로 관리되는 하카Class객체들 ->[다형성으로서 1Type 1개 List]에 몰아받았으나
            // -> .get(i)로 꺼낼때 어떤 하카Class객체인지 구분이 안된다.
            // -> 이럴 때 사용하는 것이 하카Class객체 확인 키워드가 instanceof 이다. -> 확인되면 상카 -> (하카형변환)가지 해준다.
            // -> 요약) 다형성으로 몰아넣은 list에서, 어느 하위카테고리class의 객체인지 구별한다.
            Member member = members.get(i);

            if (member.loginId.equals(loginId) && member.loginPw.equals(loginPw)) {
                isExistLoginId = true;
                // 6. 로그인 되었을 때, 우수회원은 다르게 처리해준다.
                // -> 일반vs우수 구분은 if instanceof로 구분해줘야한다.
                // -> 그럴려면, Member객체를 -> GeneralMember객체 혹은 SpecialMember객체로 변경해줘야한다.
                // -> my) 하카class로 세분화하였다면, 상카class객체들을 하카class로 바꾸는 작업을 해야한다.
                // -> 7. 로 가서 먼저 [ 회원가입 -> Member객체 생성했던 부분]으로 가서 세분화된 객체로 나눠서 바꿔치기.

                // 13. 12.번 개념 적용 -> 일반vs우수회원 구분해서 인사하기
                // -> **다형성list에서 `instanceof로 확인되었다면, (하카class) 형변환해서 [새로운하카객체 변수 = ] 에 새로 담기`**까지 확실하게 해줘야한다.
                // -> 그래야 하위class만의 새로운 변수point (혹은 메소드)를 쓸 수 있게 된다.
                // -> **그래도 분기에서만 새변수에 담아 하카만의 새로운변수, 기능을 사용하고나서, 분기빠져나오면, 다형성 객체로 쭉 간다.**
                if (member instanceof GeneralMember) {
                    // 14. 다형성 상카 list에서 꺼낸 뒤에는, 확인후, 하카객체에 새로담아주자.
                    // (GeneralMember) member;
                    GeneralMember generalMember = (GeneralMember)member;
                    // System.out.println(member.nickname + "님 안녕하세요.!");
                    System.out.println("안녕하세요 일반회원" +generalMember.nickname + "님 반갑습니다.");
                }
                if (member instanceof SpecialMember) {
                    SpecialMember specialMember = (SpecialMember)member;
                    //15. 하카class객체로 형변환후 새로운 변수에 받아줬다면, 그 변수를 이용한 처리가 가능해진다.
                    // [새로운하카객체 변수].새로운 변수 or .새로운 기능 사용가능
                    // System.out.println(member.nickname + "님 안녕하세요.!");
                    System.out.println("안녕하세요 우수회원" + specialMember.nickname + "님 사랑합니다.! 회원님의 남은 포인트는 현재 "+ specialMember.point+ "입니다.");
                }
                //16. 하카별 처리가 필요한 분기에서만 새변수에 담아 하카만의 새로운변수, 기능을 사용하고 끝낸다.
                // -> [if instanceof의 분기]빠져나오면, 새로 담은 변수 안쓰고, 기존 다형성 객체로 쭉 간다
                // -> test시 로그아웃하고, login새로해보자.
                loginedMember = member;
                break;
            }
        }
        //17. test
        // 명령어를 입력해주세요[조재성(aaa)] : logout
        // 로그아웃 되셨습니다.
        // 명령어를 입력해주세요 : login
        // 아이디 : aaa
        // 비밀번호 : aaa
        // 안녕하세요 일반회원조재성님 반갑습니다.
        //     명령어를 입력해주세요[조재성(aaa)] : logout
        // 로그아웃 되셨습니다.
        // 명령어를 입력해주세요 : signup
        // 1. 일반회원, 2. 우수회원 : 2
        // 아이디를 입력해주세요 : cccs
        // 비밀번호를 입력해주세요 : ccc
        // 닉네임을 입력해주세요 : 우수회원ccc
        // 회원가입이 완료되었습니다.
        // 명령어를 입력해주세요 : login
        // 아이디 : ccc
        // 비밀번호 : ccc
        // 안녕하세요 우수회원우수회원ccc님 사랑합니다.! 회원님의 남은 포인트는 현재 0입니다.
        // 명령어를 입력해주세요[우수회원ccc(ccc)] :

        if (!(isExistLoginId)) {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
        }
    }

    private void signup() {
        // 7. 기존 Member객체(상카class) -> 세분화된 하카Class객체들로 판단해서 바꿔줘야한다.
        // -> 사용자입력을 통해서 일반회원 or 우수회원 세분화해서 객체를 생성하자.
        System.out.print("1. 일반회원, 2. 우수회원 : ");
        int memberFlag = Integer.parseInt(scanner.nextLine());

        System.out.print("아이디를 입력해주세요 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String loginPw = scanner.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = scanner.nextLine();
        // 8. 회원종류에 따라 다른 (하위카테고리class 중 택1) 객체를 만들어서 받아줘야한다.
        // Member member = new Member(memberNumber, loginId, loginPw, nickname);
        // -> **분기에 따라 new하카객체 생성시, [분기별 변수에 받는 것은 분기 맨 위에 상카객체Type 변수]로 다형성으로 편하게 받아준다!!**
        Member member = null;
        if (memberFlag == 2) {
            member = new SpecialMember(memberNumber, loginId, loginPw, nickname, 0);
        }
        if (memberFlag == 1) {
            member = new GeneralMember(memberNumber, loginId, loginPw, nickname);
        }
        // 9. 기존list라도, 상카classType의 list이므로, 하카Class객체를 다형성으로 편하게 받을 수 있다.
        // -> 꺼내기 전까진, 어떤회원이 우수회원이고, 어떤회원이 우수회원인지 모른다.
        members.add(member);

        System.out.println("회원가입이 완료되었습니다.");
        memberNumber++;
    }

    private void readArticles() {
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        int target = Integer.parseInt(scanner.nextLine());
        BoardArticle boardArticle = getArticleByArticleNumber(target);
        if (boardArticle == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        boardArticle.hit++;
        printArticle(boardArticle);

        readProcess(boardArticle);
    }

    private void printArticle(BoardArticle boardArticle) {
        System.out.printf("==== %d번 게시물 ====\n", boardArticle.id);
        System.out.println("번호 : " + boardArticle.id);
        System.out.println("제목 : " + boardArticle.title);
        System.out.println("-------------------");
        System.out.println("내용 : " + boardArticle.body);
        System.out.println("-------------------");
        System.out.println("작성자 : " + boardArticle.nickname);
        System.out.println("등록날짜 : " + boardArticle.regDate);
        System.out.println("조회수 : " + boardArticle.hit);
        System.out.println("===================");
        System.out.println("======= 댓글 ======");
        for (ReplyArticle currentReplyArticle : replies) {
            if (currentReplyArticle.parentId == boardArticle.id) {
                currentReplyArticle = (ReplyArticle) setNickname(currentReplyArticle);
                System.out.println("내용 : " + currentReplyArticle.body);
                System.out.println("작성일 : " + currentReplyArticle.regDate);
                System.out.println("===================");
            }
        }
    }

    private void readProcess(BoardArticle boardArticle) {
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = Integer.parseInt(scanner.nextLine());
            if (readCommand == 5) {
                break;
            }
            if (readCommand == 1) {
                reply(boardArticle);
                continue;
            }
            if (readCommand == 2) {
                System.out.println("[좋아요 기능]");
                continue;
            }
        }
    }

    private void reply(BoardArticle boardArticle) {
        System.out.print("댓글 내용을 입력해주세요 : ");
        String rbody = scanner.nextLine();
        int memberId = loginedMember.id;
        String regDate = MyUtil.getCurrentDate(dateFormat);
        ReplyArticle replyArticle =
                new ReplyArticle(replyArticleNumber, boardArticle.id, rbody, memberId, regDate);
        replies.add(replyArticle);
        replyArticleNumber++;
        System.out.println("댓글이 등록되었습니다.");

        printArticle(boardArticle);
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate(dateFormat);
        boardArticles.add(new BoardArticle(1, "안녕하세요", "내용1입니다.", currentDate, 1, 0));
        boardArticles.add(new BoardArticle(2, "반갑습니다.", "내용2입니다.", currentDate, 2, 0));
        boardArticles.add(new BoardArticle(3, "안녕안녕", "내용3입니다.", currentDate, 1, 0));

        // 11. `new Member로 만든 부분`은 상카Class객체 사용부분은 하카Class로 세분화하더라도,
        // -> **다형성에 의해 에러가 안나니 new Memebr -> new 하카객체로  직접  가서 바꾸자.**
        // -> 초기 테스트 데이터를 일반회원으로 넣어놓자.
        // members.add(new Member(1, "aaa", "aaa", "조재성"));
        // members.add(new Member(2, "bbb", "bbb", "김석영"));
        members.add(new GeneralMember(1, "aaa", "aaa", "조재성"));
        members.add(new GeneralMember(2, "bbb", "bbb", "김석영"));

        loginedMember = members.get(0);
    }

    private void searchArticles() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scanner.nextLine();

        ArrayList<BoardArticle> searchedBoardArticles = new ArrayList<>();

        for (int i = 0; i < boardArticles.size(); i++) {
            if (boardArticles.get(i).title.contains(keyword)) {
                searchedBoardArticles.add(boardArticles.get(i));
            }
        }
        list(searchedBoardArticles);
    }

    private void deleteArticle() {
        System.out.print("삭제할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());
        BoardArticle boardArticle = getArticleByArticleNumber(target);

        if (boardArticle == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        boardArticles.remove(boardArticle);
        System.out.println("삭제가 완료되었습니다.");

        list(boardArticles);
    }

    private void updateArticle() {
        System.out.print("수정할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());
        BoardArticle boardArticle = getArticleByArticleNumber(target);
        if (boardArticle == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String body = scanner.nextLine();
        System.out.println("수정이 완료되었습니다.");

        boardArticle.title = title;
        boardArticle.body = body;
        System.out.println("수정이 완료되었습니다.");

        list(boardArticles);
    }

    private void addArticle() {
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        String currentDate = MyUtil.getCurrentDate(dateFormat);
        BoardArticle boardArticle =
                new BoardArticle(boardArticleNumber, title, body, currentDate, loginedMember.id, 0);
        System.out.println("게시물이 저장되었습니다.");

        boardArticles.add(boardArticle);
        boardArticleNumber++;
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

    private BoardArticle getArticleByArticleNumber(int target) {
        BoardArticle targetBoardArticle = null;
        for (int i = 0; i < boardArticles.size(); i++) {
            BoardArticle currentBoardArticle = boardArticles.get(i);
            if (currentBoardArticle.id == target) {
                targetBoardArticle = currentBoardArticle;
                break;
            }
        }

        targetBoardArticle = (BoardArticle) setNickname(targetBoardArticle);
        return targetBoardArticle;
    }

    private BaseArticle setNickname(BaseArticle info) {
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

    public void list(ArrayList<BoardArticle> list) {
        for (int i = 0; i < list.size(); i++) {
            BoardArticle boardArticle = list.get(i);
            System.out.println("번호 : " + boardArticle.id);
            System.out.println("제목 : " + boardArticle.title);
            System.out.println("작성자 : " + boardArticle.memberId);
            System.out.println("등록날짜 : " + boardArticle.regDate);
            System.out.println("조회수 : " + boardArticle.hit);
            System.out.println("====================================");
        }
    }
}
