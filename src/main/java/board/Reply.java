package board;

public class Reply {
	int id;
	//1. 각 댓글마다 부모글번호를 가지게 한다.(각 상세보기 -> One이 건네주는 FK로 해당하는 replies들 다 건져내서 출력시킬 예정)
	// - **댓글은 부모 게시글이 없으면 존재할수X**
	// -> 각 reply마다 부모글번호가 입력되어야, 상세보기(1개 article부모글) -> 부모글번호를 fk로 가지는 many 속 reply들을 추려서 뿌려줄 수있다.
	int parentId;
	String body;
	int memberId;
	String regDate;
	String nickname;

	//2. 생성자에서도 parentId가 들어가야한다.(nickname과 달리 생성시 정해짐.)
	// -> 테스트 데이터 등 에서 객체 생성코드도 다 수정필요할 예정.
	public Reply(int id, int parentId, String body, int memberId, String regDate) {
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
