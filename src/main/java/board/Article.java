package board;

public class Article {
    int id;
    String title;
    String body;
    String regDate;
    int memberId;
    //9. Article마다 nickname을 출력시키기 위해, memberId를 구분자로 도입한 것이다. nickname도 있어야함.
    // -> 하지만, 게시물쓸때 바로 채우진 X
    String nickname;
    int hit;

    // 23. -> Article의 생성자에서 nickname초기화빼기 (변수로만 가지고 있기)
    public Article(int id, String title, String body, String regDate, int memberId, int hit) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.memberId = memberId;
        this.hit = hit;
    }
}
