package board;

//3. 각 클래스는 상카Class(BaseClass)를 상속한다.
public class Article extends BaseInfo {
    //4. Base상카Class를 상속했다면, 공통변수들은 물려받게 되니 삭제한다.
    // int id;
    String title;
    // String body;
    // String regDate;
    // int memberId;
    // String nickname;
    int hit;

    public Article(int id, String title, String body, String regDate, int memberId, int hit) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.memberId = memberId;
        this.hit = hit;
    }
}
