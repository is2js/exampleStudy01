package board;

public class ReplyArticle extends BaseArticle {
	int parentId;

	public ReplyArticle(int id, int parentId, String body, int memberId, String regDate) {
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
