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
        //13. 이제 index를 받을 필요없이 바로 article로 받는다.
        // int targetIndex = getArticleByArticleNumber(target);
        Article article = getArticleByArticleNumber(target);
        //14. 못찾은 index의 -1 -> 못찾은 객체의 null로 바꾼다.
        // -> 못찾을시 종료필터링이다.
        // if (targetIndex == -1) {
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        //15. 행위 1개가 줄어들었다.
        // Article article = articles.get(targetIndex);
        article.hit++;
        System.out.printf("==== %d번 게시물 ====\n", article.id);
        System.out.println("번호 : " + article.id);
        System.out.println("제목 : " + article.title);
        System.out.println("-------------------");
        System.out.println("내용 : " + article.body);
        System.out.println("-------------------");
        //16. memberId대신 새롭게 추가된 nicname을 출력해준다.
        // System.out.println("작성자 : " + article.memberId);
        System.out.println("작성자 : " + article.nickname);
        System.out.println("등록날짜 : " + article.regDate);
        System.out.println("조회수 : " + article.hit);
        System.out.println("===================");
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
        //17. 초기데이터든, 글을 새로 작성하든, nickname은 바로 입력안하고 ""로 비워둔다.
        // -> 변경가능하므로, 필요할때마다(게시물 검색, read/update/delete 등) Member를 통해 조회후 현재nickname을 입력시킨다.
        // -> 원래는 여기서도.. memberId를 통해 가져와서  입력해야함...
        // articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, 1, "", 0));
        //24. nickname은 생성자에서 빠짐. 넣어줄 필요X. 가지고 있기만 하면 됨.
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
        //17. 수정할 부분들 똑같이 다 수정해주기
        // int targetIndex = getArticleByArticleNumber(target);
        Article article = getArticleByArticleNumber(target);

        // if (targetIndex == -1) {
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }
        //18. 삭제도.. romove(index)였으니,  <Article>타입의 arraylist이므로
        // -> **article을 넣으면, remove(value)로 article을 삭제해준다.**
        //articles.remove(targetIndex);
        articles.remove(article);
        System.out.println("삭제가 완료되었습니다.");

        list(articles);
    }

    private void updateArticle() {
        System.out.print("수정할 게시물 번호 : ");
        int target = Integer.parseInt(scanner.nextLine());
        //19. 수정
        Article article = getArticleByArticleNumber(target);
        // if (targetIndex == -1) {
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String body = scanner.nextLine();
        System.out.println("수정이 완료되었습니다.");

        //20. 기존에는 new Article객체 생성 -> artilce.set(index, article);로 수정했으나
        // -> **지금은 꺼내온 article이 있으므로, 새인스턴스(X) -> 인스턴스변수 값만 수정해준다**
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
        //21. nickname은 일단 조회(검색)전까지는 ""로 등록되도록 하기
        //22. **ㅜ_ㅠ nickname은 생성자에서 초기화할 필요가 없다(null로 그냥 두고, 객체생성시 안집어넣기)**
        // -> Article의 생성자에서 nickname초기화빼기 (변수로만 가지고 있기)
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

    //12. 마지막으로 함수이름도 바꾼다. getIndex(x) -> getArticle...
    // my) ctrl+f -> ctrl+h로 바꾼다.  or f2두번으로 바꾼다.
    // private Article getIndexOfArticleNumber(int target) {
    private Article getArticleByArticleNumber(int target) {
        // 1. 기존에는 <입력받은 게시물번호id> -> 해당하는 고유번호(id) 찾자마자 <원하는 index값> 리턴으로 종료(검색은 찾으면 종료)였는데
        //    -> id로 검색 Article을 찾았다면, <index 값만 return후 종료>가 아니라
        //    -> **<검색시 찾은 targetArticle 객체>를 건져놓고, break로 검색만 종료 -> 함수 이어가면서, member정보 얻고 -> nickname까지 채워서 -> article완성**
        //    -> id로 찾은 Article속에, memberId가 박혀있는데, 그것을 활용해, nickname데이터는 조회시마다 (변경되는, 변경된 데이터가 위치하는)member를 통해 채운다.
        Article targetArticle = null;
        for (int i = 0; i < articles.size(); i++) {
            Article currentArticle = articles.get(i);
            if (currentArticle.id == target) {
                // 2. index를 반환하고 메소드 종료가 아니다. -> Article객체를 쟁겨놓고, 함수는 지속, 반복문만 종료
                // return i;
                targetArticle = currentArticle;
                break; // 검색성공시 [바로 값넘기고 함수종료 = return]할 거아니면, [반복문이라도 종료 = break]한다.
            }
        }

        // 3. 검색된 게시물의, 회원번호를,넘겨서 -> 회원 자체정보(Member객체)를 가져온다
        // read or update or delete시 검색(Number) 결과(Index)로 찾은  index가 아닌 -> 찾은 targetArticle객체
        // -> 그 게시물의 memberId를 받아서 -> member객체를 찾아온다.
        // 1) 파라미터로 [ 게시물 검색 - > 게시물 속 memberId를 통한 멤버 검색]에서
        //    찾은 [memberId]를 받는 선언부 ( targetArticle(찾은게시물).memberId)
        // 2) getMember할 수 있는 메소드 정의 -> 직접 작성하기
        // getMemberByMemberId(); // 일단 파라미터/(targetArticle.memberId) 빼두고-> 메소드 자동완성으로 작성하고 오기
        //7. 회원정보를 받아왔다.
        // -> 근데 <검색을 위한 반복문>이 다 끝나고 못찾을 수도 있다. targetArticle = null; 유지
        // -> null처리를 해줘야한다. -> null이 아닐때만, member에게 정보를 가져오게 시킨다.
        if ( targetArticle != null ) {
            Member writer = getMemberByMemberId(targetArticle.memberId);
            // 8. 생각해보니, 중복안되는 고유정보 memberId를 박아놨으니, 중복가능한 nickname을 마음껏 article에 포함 및 출력시 사용할 수 있다.
            // -> memberId와 마찬가지로 **nickname**도 Article의 데이터로 포함시키기

            // 10. 검색된 게시물의 닉네임을, memberId -> Member -> nickname으로 추출해서 넣어준다?
            // -> 아~ memberId는 로그인된 정보에서 받고, nicname은.. 글쓸때마다 membderId -> nickname추출해서 넣나?
            targetArticle.nickname = writer.nickname;
        }

        //11. 이제 더이상 index(못찾으면 -1)를 반환하진 않는다.
        // -> article객체를 찾고, -> memberId를 이용해  글쓴이정보를 사용하고(nickname추출후 받기)
        // -> 완성된 article을 반환한다.
        //return -1;
        return targetArticle;
    }

    // 4. 해당 memberId(int)에 대한 Member객체를 반환해야함.
    private Member getMemberByMemberId(int memberId) {
        // 3. arraylist에서 for문으로 검색해야한다. 누가 해당 memberId를 가지고 있는지
        // -> **for검색시에는 찾은 것을 받아줄 변수도 미리 선언해주는 버릇을 가지자.**
        Member targetMember = null;
        for (int i = 0; i < members.size(); i++) {
            // 4. i번째 객체도 바로 쓰지말고, 받아두자. 특정변수로 검색할 거고, 사용은 또 다른변수나객체 자체를 사용할 수 있으니
            Member currentMember = members.get(i);
            if (memberId == currentMember.id) {
                // 5. 검색 -> 찾았으면, 미리 선언한 바구니변수에 넣어주고, [검색끝!] -> break로 끝내야한다.
                targetMember = currentMember;
                break;
            }
        }
        //6. 찾은 targetMember 객체 자체를 반환하자. -> 못찾았으면 null이 반환될 것이다.
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
