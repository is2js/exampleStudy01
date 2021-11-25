package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    int no = 1 + 3; // 게시물 고유번호 시작번호 + Test데이터 3개 들어감.
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
                // 8. 진입후 false 종료필터링을 -> 진입전 true 진입필터링으로 바꿔서 여기로 가져온다.
                // -> 로그인이 필요한 곳마다 진입전에 체크하도록 한다.
                // -> **이미 false시에는 로직실행X & (로그인필요)멘트가 나가므로 else처리는 할 필요없다? **
                // -> **if true 진입필터링은, t/f반환메서드내부에서 false일때, 멘트(진입하려면 로그인 필요 등)를 꼭 날려주자 ->  진입못한 else처리 할 필요가 없어진다.**
                if (isLoginCheck()==true) {
                    addArticle();
                } // false시 멘트 자동으로 나옴. "로그인 필요한 기능입니다"
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
            // 1. 분기문 작성
            if (command.equals("logout")) {
                //9. 로그아웃도 로그인검사를 "진입필터링 with 진입못할시 멘트"를 씌워주자.
                if (isLoginCheck()==true) {
                    logout();
                }
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void logout() {
        //3. 중복코드 함수화 됨.
        // if (loginedMember == null) {
        //     System.out.println("로그인이 필요한 기능입니다.");
        //     return;
        // }
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

        Member member = new Member(loginId, loginPw, nickname);
        members.add(member);
        System.out.println("회원가입이 완료되었습니다.");
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
        System.out.println("작성자 : " + article.writer);
        System.out.println("등록날짜 : " + article.regDate);
        System.out.println("조회수 : " + article.hit);
        System.out.println("===================");
    }

    private void makeTestData() {
        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, "조재성", 0));
        articles.add(new Article(2, "반갑습니다.", "내용2입니다.", currentDate, "김석영", 0));
        articles.add(new Article(3, "안녕안녕", "내용3입니다.", currentDate, "조재성", 0));

        members.add(new Member("aaa", "aaa", "조재성"));
        members.add(new Member("bbb", "bbb", "김석영"));

        loginedMember = members.get(0);
    }

    private void searchArticles() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scanner.nextLine();

        ArrayList<Article> searchedArticles = new ArrayList();

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
        Article article = new Article(target, title, body, "2021.11.11", "익명", 0);
        articles.set(targetIndex, article);
        System.out.println("수정이 완료되었습니다.");

        list(articles);
    }

    private void addArticle() {
        //1. 글쓰기가 add다.
        // -> (로그인)검사통과못하면 코드작동하면안됨 -> 메서드 맨 위에 if return종료필터링
        // if (loginedMember == null) {
        //     System.out.println("로그인이 필요한 기능입니다.");
        //     return;
        // }

        //2. logout()에 들어있는 코드와 완전 동일하므로 코드중복->메소드로 뺀다.
        // -> **이 때, <if return>의 종료필터링을 메서드화하려고 하지만,**
        // -> **메서드의 종료는, 타메서드에서 못하므로 여기와서 해야한다**
        // --> **즉, <if return 종료필터링 메서드화>는 끽해야 true, false 반환까지만 메서드화 하고, 종료는 여기서 해야한다.**
        // --> **<if return 종료필터링 메서드화>는 끽해야 true, false 반환까지만 메서드화 하고, 종료는 해당 메서드영역에서 if <받은 true or false>에 의한 return을 여전히 해야한다**

        // isLoginCheck() //-> 3. 일단 종료필터링을 메서드화했지만... true, false를 받아와햔다. -> 4.
        // 5. if return 종료필터링이 실행될 수 있게, 메서드화시켜 받은 true or false를 받아서 검사한다.
        // if (isLoginCheck() == false) {
        //     return;
        // }

        // 6. run분기 -> 기능 진입 -> isLoginCheck()-> false시 내부 안내문(로긴필요) 후 -> 종료의 패턴을
        //   run분기 -> isLoginCheck() == true시 -> 기능진입 시켜줘도 된다.
        //  **진입후 if not -> return 종료필터링**을 -> **진입전 if true시만 -> 진입필터링**으로 바꾸는 것이다.
        // 즉, **<t/f를 반환하는 메서드로>**가 있다면, **`<진입후 if not return종료필터링>`을 -> true/false가 나오는 상황이라면 `<진입전 if true시만 진입필터링>`으로 바꾸는게 낫다.**
        // -> 7. run분기로 가서, **<t/f를 반환하는 메서드로>**진입전에 if true시만 진입필터링으로 바꾸자.

        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        //10. 이제, isLoginCheck()의 진입시 필터링으로 인해
        // -> **각 분기메서드 내부는 이제, `무조건 로그인 된 상태`가 된다.**
        // -> **검사통과한 상태(로그인한 상태)를 활용해서 코드 작성**하자
        // -> addArticle 등 **이미 로그인된 상태 = loginedMember에 정보 차있는 상태** -> 빼쓰자.
        // --> 하드코딩"익명"대신, 로그인된 loginedMember객체에서 loginId정보가져와서 작성하기
        // ---> 보통은 nickname이 중복이 되므로, 글쓴이를 구분하려면 nickname넣으면 안된다.
        // ---> nickname을 넣고싶다? -> 회원id나 회원고유번호 등도 같이 입력되게 한다.!
        Article article = new Article(no, title, body, currentDate, loginedMember.loginId, 0);

        System.out.println("게시물이 저장되었습니다.");

        articles.add(article);
        no++;
    }

    //4.종료필터링을 옮겨왔지만,
    // 종료필터링의 return -> 이전메서드의 종료이기 때문에 -> void가 아닌 boolean형으로 true, false여부만 돌려줘야한다.
    // -> 이전메서드는 t/f를 받아 종료를 결정하도록 짜야한다.
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
            System.out.println("작성자 : " + article.writer);
            System.out.println("등록날짜 : " + article.regDate);
            System.out.println("조회수 : " + article.hit);
            System.out.println("====================================");
        }
    }
}
