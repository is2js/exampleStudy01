package board;

public class Article {
    int id;
    String title;
    String body;
    //1. 추가 정보데이터를 데이터용 클래스에 인변으로 준다.
    // - 날짜도 일단 String으로 준다.
    String regDate;
    String writer;
    int hit;

    //2. 각 객체 데이터 setter대신 생성자에서 초기화도 변수를 늘려준다.
    // -> ide이용해서 다시 작성해준다.
    public Article(int id, String title, String body, String regDate, String writer, int hit) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.writer = writer;
        this.hit = hit;
    }
}
