package board;

import board.util.MyUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    int no = 1 + 3;

    public Board() {
        makeTestData();
    }

    public void runBoard() {
        while (true) {
            System.out.print("명령어를 입력해주세요 : ");
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
                addArticle();
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
            //1. 분기문으로 login 메서드를 만든다.
            if (command.equals("login")) {
                login();
                continue;
            }

            System.out.println("잘못 입력하였습니다.");
        }
    }

    private void login() {
        // 2. 본격기능구현전, 요구사항대로 일단 받아준다.
        //        아이디 : dfdfdf
        //        비밀번호 : aaaadf
        //        비밀번호를 틀렸거나 잘못된 회원정보입니다.
        //        아이디 : hong123
        //        비밀번호 : h1234
        //        홍길동님 환영합니다
        System.out.print("아이디 : ");
        String loginId = scanner.nextLine();
        System.out.print("비밀번호 : ");
        String loginPw = scanner.nextLine();

        //8. 반복문내 찾았을경우만 처리<-> 다돌아도 못찾은 경우 표시용 boolean변수
        // 반복문내 찾을 경우만 true로 바꿔주고, 반복문끝나서도 못찾은 false면.. 못찾았다 처리해주기!
        boolean isExistLoginId = false;

        //3. 아뒤비번을 받았다면, 등록된 사람인지 저장소에서 id를 검색해서 확인해야한다.
        // -> id로 몇번째 저장된사람인지 알길이 없으니. members를 다 뒤져서 몇번째 index인지 확인 후, -> index존재시, 비밀번호 확인
        // -> 비밀번호 확인전에, 일단은 id부터 확인한다.
        for (int i = 0; i < members.size(); i++) {
            // if (members.get(i).loginId //~
            Member member = members.get(i); // 일단 객체부터 얻은 뒤, 비교하자.
//            if (member.loginId.equals(loginId)) {
//                //4. 저장소에 있는 id라고 판단된 상태에서 -> (꺼내놓은 객체^^)member한테 loginPw 맞는지
//                // -> 여기서 비밀번호를 물어본다.
//                if (member.loginPw.equals(loginPw)) {
//                    // 5. if (OK) Id, Pw 둘다 탄 상태 -> 아뒤있고, 비번도 맞는 상태
//                    // -> 로그인해야하니 환영인사부터 해준다.
//                    System.out.println("안녕하세요! ");
//                }
//            }
            //6. 요구사항에서 보면, [비밀번호를 틀렸거나 잘못된 회원정보입니다.]를 한번에 처리한다.
            // -> <2조건 다 맞는 것>  vs  [둘중에 1개 이상틀림]으로만 구분된다.
            // -> 2분기를 4가지 경우의수로, 중첩할게 아니라 한번에 < 조건1 && 조건2 >로 이어서 한다.
            // -> 2조건 한꺼번에 다 맞는 경우만 로그인 실행.
            if ( member.loginId.equals(loginId) && member.loginPw.equals(loginPw) ) {
                //9. 딱 한번 찾은 경우에만 표시한다. 못찾으면 반복문 다돌아도 false로 유지되게됨.
                isExistLoginId = true;
                //12. <반복문끝에서 다돌아도 못찾은 경우처리>와 상대되는 찾은경우  <여기>가 분기된다고 생각하자.
                // - 나머지 N-1의 못찾은 경우는 그냥 else처리 없이 넘어간다. 찾은경우? vs 다돌아도 못찾은 경우만 처리한다.
                // -> 이제 조건을 만족한경우 == 로그인처리를 <반복문검색에서 if찾는 부분>에서 처리한다.
                System.out.println(member.nickname + "님 안녕하세요.!"); // 13. test

                //10. 만약, loginId가 중복허용안된다고 가정하면 -> 찾으면 바로 검색반복문 빠져나오면 된다.
                break;
            }
            //7. 주의!!) 매 반복문 내에서는 if <->else 둘중에 n번을 반복해버리니까 조심한다.
            // -> N번을 돌면서 < 찾았을때만 처리하고, 나머지99% 못찾았을때는 else처리 없이 건너띄어야한다 >
            // 1) if 찾았을 때만 -> 로그인  해주며
            // 2) 반복문내 else는  -> 그외에 N-1번을 못찾은 경우를 다 실행시킨다.
            //    -> 반복문내에는 **못찾았을 경우는 그냥 넘어가다가 끝났는데도 못찾을 때가 문제다**
            // 3) 반복문내 찾았을때만 처리 -> 못찾았을때는 다돌고나서 처리 -> 그럴려면, boolean변수가 필요하다.
            //    -> 찾았을때만 boolean swtich바꾸고 -> 다 끝나도 못찾아서 if switch변수 안바뀜 으로 처리한다.
        }
        //11. [다돌아도 못찾은 경우]를 반복문 끝나고 나서 처리되게한다.
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
        articles.add(
                new Article(1, "안녕하세요", "내용1입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
        articles.add(
                new Article(2, "반갑습니다.", "내용2입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
        articles.add(
                new Article(3, "안녕안녕", "내용3입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), "익명", 0));
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
        System.out.print("제목을 입력해주세요 : ");
        String title = scanner.nextLine();
        System.out.print("내용을 입력해주세요 : ");
        String body = scanner.nextLine();

        String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");

        Article article = new Article(no, title, body, currentDate, "익명", 0);
        System.out.println("게시물이 저장되었습니다.");

        articles.add(article);
        no++;
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
