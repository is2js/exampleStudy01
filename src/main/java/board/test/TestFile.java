package board.test;

import board.BoardArticle;
import board.util.FileManager;
import board.util.MyUtil;

public class TestFile {
    public static void main(String[] args) {
        BoardArticle article =
                new BoardArticle(
                        1, "안녕하세요", "내용1입니다.", MyUtil.getCurrentDate("yyyy.MM.dd"), 1, 20);

        FileManager manager = new FileManager();
        manager.saveArticleToFile(article);
        // 안녕하세요내용1입니다.2021.12.08
    }
}
