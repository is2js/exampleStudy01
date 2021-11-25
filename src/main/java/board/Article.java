package board;

public class Article {
    int id;
    String title;
    String body;
    String regDate;
    //7. 입력할 데이터가 바뀌었음.
    // String writer;
    int memberId;
    int hit;

    public Article(int id, String title, String body, String regDate, int memberId, int hit) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        // this.writer = writer;
        this.memberId = memberId;
        this.hit = hit;
    }
}
