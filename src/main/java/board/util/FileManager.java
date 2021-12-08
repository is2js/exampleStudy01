package board.util;

import java.io.FileWriter;
import java.io.IOException;
import board.BoardArticle;

public class FileManager {
    public void saveArticleToFile(BoardArticle article) {
        try {
            FileWriter writer =
                    new FileWriter("c:/java_file_test/article/article_" + article.id + ".txt");

            writer.write("id:" + article.id + ",");
            writer.write("title:" + article.title + ",");
            writer.write("body:" + article.body + ",");
            writer.write("memberId:" + article.memberId + ",");
            writer.write("regDate:" + article.regDate + ",");
            writer.write("hit:" + article.hit);

            writer.close();
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 문제가 발생했습니다.");
            // e.printStackTrace();
        }
    }
}
