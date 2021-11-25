package board;

public class Reply {
	//4. Reply은 rbody만 입력받지만 많은 데이터로 이루어져있다.
	// -> 그리고 필수적으로 id(식별데이터)가 있다.
	// -> 댓글작성자 -> **`타 객체(Member)에서 받는 정보(칼럼, 변수)는  직접적인 내용의 칼럼이 아니라 FK를 들고 있어야한다!!!**
	int id; // 식별데이터
	String body; // 입력은 rbody에 받지만, Reply안에서는 body이다.
	int memberId; // 댓글작성자 -> **`타 객체(Member)에서 받는 정보(칼럼, 변수)는  직접적인 내용의 칼럼이 아니라 FK를 들고 있어야한다!!!**
	String regDate; // 작성일

	//5. 생성자 생성: 참고) Article에서는 빈 nickname을 변수를 선언만(null)하고, 생성자에서 주진 않았다. 생성자에선 memberId만 너어준다.
	public Reply(int id, String body, int memberId, String regDate) {
		this.id = id;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
