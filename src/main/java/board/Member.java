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

    // 1. **하위카테고리Class별(회원종류별)로 다르게 작동할 메소드를 **`같은이름의 공통기능`메소드로서 Member(상위카테고리class)에 정의**해준다.
    // -> 상카Class에 공통기능으로서 메소드를 추가했으면 -> 하카Class 각각에 상속되어서 가지고 있을 것이다.
    public void greeting() {
        System.out.println("안녕하세요~~");
    }
}
