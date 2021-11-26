package board;

public class Reply {
	int id;
	String body;
	int memberId;
	String regDate;
	//14. nickname을 객체생성시 받아주진 않더라도, 필드로는 가지고있어야 받아줄 수 있다.
	// 생성자X 필드에만 추가해주기
	String nickname;

	public Reply(int id, String body, int memberId, String regDate) {
		this.id = id;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
