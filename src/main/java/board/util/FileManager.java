package board.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import board.BoardArticle;

public class FileManager {
    public BoardArticle loadArticleFromFile(int id) {
        String file = "C:/java_file_test/article/article_" + id + ".txt";
        
        //14. try문안에서만 할당 -> return은 밖에서 되어야함 -> 변수 생성도 밖
        BoardArticle boardArticle = null;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            while (true) {
                String currentLine = br.readLine();
                if (currentLine == null) {
                    break;
                }
                line += br.readLine();
            }

            br.close();
            reader.close();
            // 11. 이제 txt->  읽어온 누적 String을 출력하지 말고
            // -> 객체로 변환한 뒤 -> 객체를 반환해주는 식으로 바꾸자.
            // System.out.println(line);
            // BoardArticle boardArticle = getBoardArticleFromString(line);
            boardArticle = getBoardArticleFromString(line);
            //12. 반환해야함 -> 여기서 바로 하지말고 -> try/catch 밖에서...ㅠ
            // return boardArticle;

        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다..");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 문제가 발생했습니다.");
        }
        //13. try문내에서만 return해주면.. catch된 경우는 return이 없어진다.
        // -> try밖에서 return한다. -> 
        return boardArticle;
    }

    // 10. target String -> 객체 반환하는 메서드를 만들고, 내부코드를 복붙한다.
    public BoardArticle getBoardArticleFromString(String target) {
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
        return boardArticle;
    }

    public void saveArticleToFile(BoardArticle article) {
        try {
            FileWriter writer =
                    new FileWriter("c:/java_file_test/article/article_" + article.id + ".txt");

            // 뒤에 구분자는 필수 + 앞에 설명부분도 첨부해서 작성할 것.
            // -> 줄바꿈은 사람을 위한 것이니 필요시만 \n 추가할 것.
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
