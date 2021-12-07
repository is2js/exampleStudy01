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
    int boardArticleNumber = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
    int memberNumber = 1 + 2; // 회원 고유번호
    int replyArticleNumber = 1; // 댓글 고유번호
    Member loginedMember = null;
    String dateFormat = "yyyy.MM.dd";
    //5. 페이지내이션 기능 사용을 위해 객체를 만든다. -> 생성자 없으면 노파라미터로서 생략된 기본생성자.
    // -> 이제 페이지네이션을 list()에 적용한다. -> 6.
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
                //9. 분기에 page()메서드를 넣어 기능 진입하게 하기
                page();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void page() {
        //12. [안내문부터 + 입력받고 -> 출력]을 한번 진입시 계속 반복하도록
        // -> while (true)로 감싸준다.
        while (true) {
            // 10. 요구사항 안내문
            System.out.print("페이징 명령어를 입력해주세요 ((1. 이전,  2. 다음,  3. 선택,  4. 뒤로가기): ");
            // -> 다시 명령어 입력받아 분기하는 코드를 복사해와서 맞게 수정한다.
            int pageCommand = Integer.parseInt(scanner.nextLine());
            if (pageCommand == 1) {
                // currentPageNo++;
                pagination.currentPageNo--;
            }
            if (pageCommand == 2) {
                // currentPageNo--;
                pagination.currentPageNo++;
            }// -> 여기까지 하고 잘 움직여지는지 테스트 -> 이전 or 다음후, list()를 한번 출력해줘야한다.
            //13. 4 누르면 종료하도록 분기 추가
            if (pageCommand == 4) {
                break;
            }

            // 11. 이전/다음 등의 [입력받은데로 수정후 바로 다시 출력]해줘야 사용자들이 확인할 수 있다.
            // -> page, 다음, page, 다음 반복하다가 5를 넘어가니까, 페이지블럭 안바뀌고 [ ]가 안보임.
            // -> 문제해결해야함. -> list()출력시 페이지블럭 업데이트에 따른, 페이지시작번호 변화가 반영이 안된듯?
            // --> **업데이트 -> 다시 변수에 반영을 해줘야하는데,**
            // --> Pagination클래스에 변수를 <층 고려안하고 다 몰아넣어놓고>, 최초에 생성후 업데이트가 안되는로직이라서 그럼.
            // -> 나중에 고려하고..
            // --> 매번 page 를 치고 이전or다음 -> page.. 반복해야하므롤
            // -> 한번 page치고 들어오면 ,입력받아 그만둘때까지 반복되도록 while문으로 감싸자
            list(boardArticles);
        }

    }

    private void sort() {
        System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
        int target = Integer.parseInt(scanner.nextLine());
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
        int type = Integer.parseInt(scanner.nextLine());

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
        int memberFlag = Integer.parseInt(scanner.nextLine());

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
            int readCommand = Integer.parseInt(scanner.nextLine());
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
        //6. 1) [PagingTest에서 쓰던 **로직 코드**] + 2) 미리 떼어놓고 객체로 가져다 쓰는 [리얼pagination객체.**변수**]를 이용해서
        // -> << 페이지블럭 + 페이지 숫자 >> 부터 출력하게 한다. (게시물 나눠서뿌리는거말고)
        // for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
        //     if (i == currentPageNo) {
        //         System.out.print("[" + i + "] ");
        //         continue;
        //     }
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        //7. while문 [[안/팍 상관없이]] 변수들은 일단 가져와서 순서대로 모다놨으니, 객체. 변수로 변경해서 적용시킨다.
        // -> 빨간 줄 든 변수들 앞에 pagination. 맡 붙이면 된다.
        // -> 하고 테스트 -> list 명령어시 페이지가 잘나오는지 확인
        for (int i = pagination.startPageNoInBlock; i <= pagination.endPageNoInBlock; i++) {
            if (i == pagination.currentPageNo) {
                System.out.print("[" + i + "] ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        //8. 이제 page명령어를 받아서 -> 이전/다음을 받아 -> 현재 페이지 번호를 컨트롤 하게 해야함.
        // -> 기능추가하러 가자. -> 9.

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
