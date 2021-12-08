package board;

public class BoardArticle extends BaseArticle {
    public String title;
    public int hit;

    public BoardArticle(int id, String title, String body, String regDate, int memberId, int hit) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.memberId = memberId;
        this.hit = hit;
    }
}
