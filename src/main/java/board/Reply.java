package board;

//3. 각 클래스는 상카Class(BaseClass)를 상속한다.
public class Reply extends BaseInfo {
	//4. Base상카Class를 상속했다면, 공통변수들은 물려받게 되니 삭제한다.
	// int id;
	int parentId;
	// String body;
	// int memberId;
	// String regDate;
	// String nickname;

	public Reply(int id, int parentId, String body, int memberId, String regDate) {
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
