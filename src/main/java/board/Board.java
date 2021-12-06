package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Board  { //extends Main

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
            //1. 명령어 추가 -> 로그인 필요없는 기능 -> 바로 함수 정의해서 작성
            if (command.equals("sort")) {
                sort();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void sort() {
        // 2. 진입하자마자, [정렬대상(기준) -> 방법 순으로 한꺼번에 ] 안내문 + input받기
        System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
        int target = Integer.parseInt(scanner.nextLine());
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
        int type = Integer.parseInt(scanner.nextLine());

        //3. 정렬을 하려면 바로 Collections.sort()부터 꺼낸다.
        // -> 일단은, target무시하고, 조회수 hit로 정렬되도록 Comparator 구현체를 만들어 sort 2번째 인자로 넣어준다.
        // Collections.sort();
        // -> 정렬의 실제 대상은 List에 들어가 있는 DB들 중 1개임.
        // -> boardArticles는 객체리스트라 바로 정렬X -> sort의 2번째 인자를 작성해야한다.
        // -> Comparator만들어서 정렬해줘야한다. cf) stream max()안에 기준을 넣어도 객체 1개 밖에 못꺼내지만, Comparator.comparingInt( 객체Class:: getter )
        // Collections.sort( boardArticles);
        // -> 맨 밑에 직접 Comparator를 작성해주자. -> 4.

        //7. 이제 Comparator<BoardArticle>를 구현한, ArticleComparator의 객체를 new생성자로 객체 생성해서 넣어주면 정렬된다.
        // -> sort( 대상list, comparator구현체객체 new로 생성)
        Collections.sort( boardArticles, new ArticleComparator());

        //8. 애초에 초기 데이터가 조회수(hit)가 다 0이라서 정렬후 확인이 어려우니
        // -> 초기데이터 hit를 수정좀 해주고 오자.
        // -> 실행 -> list -> sort , 1, 1(구현x), -> 다시 list
        // --> sort 한다음 바로 list도 보이게, 정렬된 db_lisst를 한번 더 호출해주자.
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
                    like = new Like(boardArticle.id, loginedMember.id, MyUtil.getCurrentDate(dateFormat));
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
    }
}

//4. Comparator.comparingInt 안쓸거면, -> 직접 Comparator<정렬할 객체Type>을 구현체 Class를 작성해야한다.
// 2) MVC공부시에는, max(기준으로 객체::compareTo ) 사용하기 위해
// 2-1) implements Comparable<Car> -> compareTo 오버라이딩 -> return Integer.compare(this.숫자변수, other객체.숫자변수getter())
// 3) stream에서  max( Comparator.comparingInt( 객체 :: getter )도 가능하다.
class ArticleComparator implements Comparator<BoardArticle> {
    @Override
    public int compare(BoardArticle o1, BoardArticle o2) {
        //5. compare 메서드는, 앞vs뒤 언제 자리바꿀껀지를 if + return으로 정해줘야한다.
        // -> if문에서, 정렬을 원하는 변수로 정해주면 되나보다.
        // -> **return 1;이 되는 분기 == 자리바꾸는 분기 == 왼쪽 > 오른쪽 일때 자리바꾼다? -> 오름차순
        // --> return 1;의 if분기면, 일단 자리바꿀건데, [부등호 큰쪽이 오른쪽으로 간다]  if  왼쪽 > 오른쪽이면,  -> 왼쪽이 오르쪽으로 간다?
        // --> return -1;(0아님 ! 조심!)일때는 자리가 안바뀌는 분긴데, if 바뀌는 분기(return 1;) 안걸리면 분기 작성안해주고 바로 return -1;되게 한다.
        // if () {
        //     return 1;
        // }
        // return -1;

        //6. 왼쪽객체를 언제 오른쪽으로 보낼까? 클때? 작을때?
        // -> 왼쪽이 클때, (오른쪽으로)보내면(return 1;)? 오름차순
        // -> 왼쪽이 작을때 보낸다면? 내림차순
        if ( o1.hit > o2.hit) {
            return 1;
        }
        return -1;


    }
}
