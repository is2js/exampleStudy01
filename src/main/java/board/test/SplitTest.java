package board.test;

import board.BoardArticle;

public class SplitTest {
    public static void main(String[] args) {
		String target = "id:1,title:안녕하세요,body:내용1입니다.,memberId:1,regDate:2021.12.08,hit:20";

		BoardArticle boardArticle = new BoardArticle();
		String[] properties_arr = target.split(",");
        for (String properties : properties_arr) {
			String[] property = properties.split(":");
			if (property[0].equals("id")) {
				boardArticle.id = Integer.parseInt(property[1]);
			}
			if (property[0].equals("title")) {
				boardArticle.title = property[1];
			}
			if (property[0].equals("body")) {
				boardArticle.body = property[1];
			}
			if (property[0].equals("regDate")) {
				boardArticle.regDate = property[1];
			}
			if (property[0].equals("memberId")) {
				boardArticle.memberId = Integer.parseInt(property[1]);
			}
			if (property[0].equals("hit")) {
				boardArticle.hit = Integer.parseInt(property[1]);
			}
		}

        System.out.println(boardArticle.id);
        System.out.println(boardArticle.title);

	}
}
