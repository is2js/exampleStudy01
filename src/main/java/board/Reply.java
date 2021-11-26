package board;

public class Reply {
	int id;
	int parentId;
	String body;
	int memberId;
	String regDate;
	String nickname;

	public Reply(int id, int parentId, String body, int memberId, String regDate) {
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
