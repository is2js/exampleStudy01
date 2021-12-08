package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import board.util.MyUtil;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<BoardArticle> boardArticles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<ReplyArticle> replies = new ArrayList<>();
    ArrayList<Like> likes = new ArrayList<>();
    int boardArticleNumber = 1 + 3 + 27; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
    int replyArticleNumber = 1; // 댓글 고유번호
    Member loginedMember = null;
    String dateFormat = "yyyy.MM.dd";
    Pagination pagination = new Pagination();


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
            if (command.equals("sort")) {
                sort();
                continue;
            }

            if (command.equals("page")) {
                page();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void page() {
        while (true) {
            System.out.print("페이징 명령어를 입력해주세요 ((1. 이전,  2. 다음,  3. 선택,  4. 뒤로가기): ");
            //1.[문자열숫자를 받는 부분]은 -> [parseInt하는] 부분에서 에러 발생가능하므로 예외처리를 한다
            // -> try 밑에서도 써야하는 변수선언은, 위에 미리 default값으로 선언해놓고 try내부에선 할당만
            // int pageCommand = Integer.parseInt(scanner.nextLine());

            //8. 메서드로 무한반복으로 올바른값 입력받고 난 뒤 parsing된 int를 받는다.
            // -> 인자로 스캐너 입력값을 받는다!
            // int pageCommand = 0;
            int pageCommand = inputIntData();
            //9. parseInt쓰는 모든 곳을 convertStringToInt(scanner.nextLine())로 변환


            // try{
            //     pageCommand = Integer.parseInt(scanner.nextLine());
            // } catch (NumberFormatException e) {
            //     // 2. catch부분은 해당 예외발생시, 프로그램 끄지말고 이 코드를 실행해줘! 다
            //     System.out.println("숫자만 입력해야 합니다.");
            //     // 3. 종료를 안시키니 밑으로 타고 내려가는데,
            //     // -> 에러가 나면 pageCommand는 defatul값 0으로 내려가므로,
            //     // -> 분기를 안탄다.
            //     // -> 분기 안타면, list() 호출후 다시 반복문 반복되므로..
            //     // -> 분기 안타는 제일 끝엔.. 예외처리를 해줬었는데
            //     // -> 여기서는 여러분기상 list()를 태워야하니..
            //     // -> [[[default값 분기]]]를 세로 만들자.
            // }
            //4. 원래는 분기예외처리를, if continue 분기들 -> 안걸리는 부분 = 예외 = 분기없는 맨끝에서 처리였으나
            // -> 모든 분기마다 if 처리 후, continue없이,  공통작업 list()호출인 상황에서는
            // -> [입력받지 않은 default 값으로 분기 생성]하여 예외처리 되게 한다.

            //5. 만약 올바른 값이 입력될때까지 계속해서 받아야한다면???
            // -> while (true)안에 집어넣고, try 제대로 파싱시 break; catch 끄지말고, 여기서 그대로 내려가면 다시 while문으로..
            // --> [[try parse.Int시에만 while (true) 탈출]]하게 작성하면, 올바른값입력할때까지 계속 입력 받을 수 있다.
            // while (true) {
            //     try {
            //         pageCommand = Integer.parseInt(scanner.nextLine());
            //         // 1) 파싱성공시 탈출부만 있으면, 올바른 값 -> 파싱성공-> break 외에는 무한반복이 되게 할 수 있다.
            //         break;
            //     } catch (NumberFormatException e) {
            //         // 2) catch부분에서는 그냥 흘러가면 알아서, 무한 입력 시도가 된다.
            //         // -> parseInt로 검색해서, 파싱하는 부분은 다 이렇게 만들어주면 예외처리가 된다.
            //         // -> 그런데 integer쓰는 곳마다 try/catch 다해주면 번거러우니 메소드로 만든다.
            //         // -> 7. 메소드에 짤라넣음.
            //         System.out.println("숫자만 입력해야 합니다.");
            //     }
            // }

            if (pageCommand == 0) {
                System.out.println(" 알 수 없는 명령입니다.");
            }
            if (pageCommand == 1) {
                pagination.currentPageNo--;
            }
            if (pageCommand == 2) {
                pagination.currentPageNo++;
            }
            if (pageCommand == 4) {
                break;
            }

            list(boardArticles);
        }

    }

    private int inputIntData() {
        int convertedData = 0;
        while (true) {
            try {
                convertedData = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해야 합니다.");
            }
        }
        return convertedData;
    }

    private void sort() {
        System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
        int target = inputIntData();
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
        int type = inputIntData();

        Collections.sort(boardArticles, new ArticleComparator(target, type));

        list(boardArticles);
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
                member.greeting();

                loginedMember = member;
                break;
            }
        }

        if (!(isExistLoginId)) {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
        }
    }

    private void signup() {
        System.out.print("1. 일반회원, 2. 우수회원 : ");
        int memberFlag = inputIntData();

        System.out.print("아이디를 입력해주세요 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String loginPw = scanner.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = scanner.nextLine();
        Member member = null;
        if (memberFlag == 2) {
            member = new SpecialMember(memberNumber, loginId, loginPw, nickname, 0);
        }
        if (memberFlag == 1) {
            member = new GeneralMember(memberNumber, loginId, loginPw, nickname);
        }
        members.add(member);

        System.out.println("회원가입이 완료되었습니다.");
        memberNumber++;
    }

    private void readArticles() {
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        int target = inputIntData();
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
        Like like = getLikeByArticleIdAndMemberId(boardArticle.id, loginedMember.id);
        int likeCount = getLikeCountByArticleId(boardArticle.id);
        if (like == null) {
            System.out.printf("좋아요 : ♡ %d\n", likeCount);
        } else {
            System.out.printf("좋아요 : ♥ %d\n", likeCount);
        }
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

    private int getLikeCountByArticleId(int boardArticleId) {
        int likeCount = 0;
        for (Like like : likes) {
            if (like.articleId == boardArticleId) {
                likeCount++;
            }
        }
        return likeCount;
    }

    private void readProcess(BoardArticle boardArticle) {
        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int readCommand = inputIntData();
            if (readCommand == 5) {
                break;
            }
            if (readCommand == 1) {
                reply(boardArticle);
                continue;
            }
            if (readCommand == 2) {
                Like like = getLikeByArticleIdAndMemberId(boardArticle.id, loginedMember.id);

                if (like == null) {
                    like =
                            new Like(
                                    boardArticle.id,
                                    loginedMember.id,
                                    MyUtil.getCurrentDate(dateFormat));
                    likes.add(like);
                    System.out.println("해당 게시물을 좋아합니다.");
                    printArticle(boardArticle);
                    continue;
                }
                likes.remove(like);
                System.out.println("해당 게시물의 좋아요를 해제합니다.");
                printArticle(boardArticle);

                continue;
            }
        }
    }

    private Like getLikeByArticleIdAndMemberId(int boardArticleId, int loginedMemberId) {
        for (Like like : likes) {
            if (boardArticleId == like.articleId && loginedMemberId == like.memberId) {
                return like;
            }
        }
        return null;
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
        boardArticles.add(new BoardArticle(1, "안녕하세요", "내용1입니다.", currentDate, 1, 20));
        boardArticles.add(new BoardArticle(2, "반갑습니다.", "내용2입니다.", currentDate, 2, 100));
        boardArticles.add(new BoardArticle(3, "안녕안녕", "내용3입니다.", currentDate, 1, 30));
        for (int i = 4; i<=30; i++) {
            boardArticles.add(new BoardArticle(i, "제목" + i, "내용" + i, currentDate, 1, 30));
        }

        members.add(new GeneralMember(1, "aaa", "aaa", "조재성"));
        members.add(new SpecialMember(2, "bbb", "bbb", "김석영", 0));

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
        int target = inputIntData();
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
        int target = inputIntData();
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
        for (int i = pagination.getStartIndex(); i < pagination.getEndIndex(); i++) {
            BoardArticle boardArticle = list.get(i);
            System.out.println("번호 : " + boardArticle.id);
            System.out.println("제목 : " + boardArticle.title);
            System.out.println("작성자 : " + boardArticle.memberId);
            System.out.println("등록날짜 : " + boardArticle.regDate);
            System.out.println("조회수 : " + boardArticle.hit);
            System.out.println("====================================");
        }

        for (int i = pagination.getStartPageNoInBlock(); i <= pagination.getEndPageNoInBlock(); i++) {
            if (i == pagination.currentPageNo) {
                System.out.print("[" + i + "] ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

class ArticleComparator implements Comparator<BoardArticle> {
    private int target;
    private int type;

    public ArticleComparator(int target, int type) {
        this.target = target;
        this.type = type;
    }


    @Override
    public int compare(BoardArticle o1, BoardArticle o2) {
        int result = getCompareResult(o1, o2);
        if (type ==2) {
            result *= -1;
        }
        return result;
    }

    private int getCompareResult(BoardArticle o1, BoardArticle o2) {
        if (type == 1) {
            if (o1.id > o2.id) {
                return 1;
            }
            return -1;
        }

        if (type == 2) {
            if (o1.hit > o2.hit) {
                return 1;
            }
            return -1;
        }
        return 1;
    }
}
