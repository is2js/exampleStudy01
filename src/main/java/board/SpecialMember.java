package board;

public class SpecialMember extends Member {
    int point;

    public SpecialMember(int id, String loginId, String loginPw, String nickname, int point) {
        super(id, loginId, loginPw, nickname);
        this.point = point;
    }
    @Override
    public void greeting() {
        System.out.printf("안녕하세요 우수회원 %s님 사랑합니다. 회원님의 남은 포인트는 현재 %s입니다.\n", this.nickname, this.point);
    }
}
