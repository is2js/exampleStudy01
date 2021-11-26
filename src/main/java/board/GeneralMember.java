package board;

public class GeneralMember extends Member {
	public GeneralMember(int id, String loginId, String loginPw, String nickname) {
		super(id, loginId, loginPw, nickname);
	}

	@Override
	public void greeting() {
        System.out.printf("안녕하세요 일반회원 %s님 반갑습니다\n", this.nickname);
	}
}
