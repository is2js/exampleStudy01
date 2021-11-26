package board;

public class GeneralMember extends Member {
	public GeneralMember(int id, String loginId, String loginPw, String nickname) {
		super(id, loginId, loginPw, nickname);
	}

	//5. 각 하위카테고리Class별로, 공통기능 메소드greeting()을 수정하는 @Override해서 쓰자.
	// -> 메소드명만 똑같다면, 여전히 다형성 상카객체에서  하카별 객체종류상관없이 호출된다.
	// -> **공통기능으로서 일괄 메서드호출은 되지만, 내부내용은 수정한대로 알아서 처리된다.**
	@Override
	public void greeting() {
        System.out.printf("안녕하세요 일반회원 %s님 반갑습니다\n", this.nickname);
	}
}
