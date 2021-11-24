package board;

public class Member {
    //5. 변수 선언 -> 생성자까지.
    String loginId;
    String loginPw;
    String nickname;

    public Member(String loginId, String loginPw, String nickname) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
    }
}
