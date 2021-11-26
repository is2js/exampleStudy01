package board;

// 1. 하위카테고리1: 일반멤버 빈 클래스 생성 -> 스페셜멤버도 빈 Class로 생성하자.
// 2. 기존에 있던 상위카테고리 Classs를 상속받기 -> 스페셜멤버도
// -> 자동 상속자 생성
// 3. 기존에 있던 Member가 일반회원이라 생각하고, GeneralMember는 변수의 변화가 없다
// -> 스페셜멤버만 [if instanceof로 따로 분기해서 관리]하기 위해, 같은 레벨로서 GeneralMemeber도 곁다리로 생성되는 것이다!!
public class GeneralMember extends Member {
	public GeneralMember(int id, String loginId, String loginPw, String nickname) {
		super(id, loginId, loginPw, nickname);
	}
}
