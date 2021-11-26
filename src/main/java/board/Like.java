package board;

//1. 좋아요 정보를 담아둘 Class를 만든다.
// -> 좋아요 1개당 데이터는 총 3개가 필요하다
// 1) 어떤게시물(one이 준 FK 그나마 추림용. 부모id)의 좋아요인가? -> 상세보기 진입상황에서는 현재게시글 정보가 들어온다. -> 거기서 id만 빼서 저장하면 된다.
// 2) 해당게시물에는 누가(조회용FK) 체크했나? -> 로그인된 사람의 id를 가져오면 된다.
// 3) 언제 눌렀나?(등록날짜) -> 유틸메소드로 생성한다.
// -> **Tip) registerDate는 데이터의 성격과 관계없이 거의 무조건 있어야한다.**
public class Like {
	int articleId;
	int memberId;
	String regDate;

	public Like(int articleId, int memberId, String regDate) {
		this.articleId = articleId;
		this.memberId = memberId;
		this.regDate = regDate;
	}
}
