package board;

public class Member {
    // 1. 중복가능한 nickname을 글쓸 때 사용하고 싶으면, 중복불가능한 데이터도 글에 넣어줘야지 식별이 된다.
    // -> id(고유번호) 데이터를 추가한다.
    // -> loginId로 중복을 방지할 수 있지만, **수월하게 데이터를 관리하려면 1부터 1씩 증가하는 변수가 있는 것이 좋다**
    int id;
    String loginId;
    String loginPw;
    String nickname;

    //2. 생성자도 변경함.
    public Member(int id, String loginId, String loginPw, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
    }
}
