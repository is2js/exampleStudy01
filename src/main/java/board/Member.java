package board;

public class Member {
    int id;
    String loginId;
    String loginPw;
    String nickname;

    public Member(int id, String loginId, String loginPw, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
    }

    public void greeting() {
        System.out.println("안녕하세요~~");
    }
}
