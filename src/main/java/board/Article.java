package board;

public class Article {
    // 데이터를 묶을 때, private으로 생성하는게 좋은데 아직은 안함.
    int id;
    String title;
    String body;

    public Article(int no, String title, String body) {
        this.id = no;
        this.title = title;
        this.body = body;
    }
}
