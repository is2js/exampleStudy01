package board.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import board.BoardArticle;
import sun.security.util.BitArray;

public class FileManager {

    // 9. File객체 -> 파일명 -> 이제 id만 추출해야지.. 객체를 가져올 수 있음.
    // -> 파일명에서 id를 따로 추출하는 작업 -> 역으로 또 일하는 것이니
    // --> id받는 버전말고, id가 포함된 파일명 받는 버전으로 loadArticleFromFile( String fileName)을 하나 더 만들어주자.
    public BoardArticle loadArticleFromFile(String fileName) {
        // String file = "C:/java_file_test/article/article_" + id + ".txt";
        String file = "C:/java_file_test/article/" + fileName;

        //10. 파라미터가 다르면, 다른함수다. 하지만 안에 내용이 겹치니,
        // 1) 공통코드를 메서드화 해도 되겠지만.
        // 2) 한쪽은 살리고, 다른한쪽이 살려진 메서드()를 통째로 활용하도록 해보자.
        // --> id를 받아서 만드는놈은 -> id받고 -> fileName만들고 -> 여기 메서드를 호출하도록 수정하자.
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

            boardArticle = getBoardArticleFromString(line);

        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다..");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 문제가 발생했습니다.");
        }

        return boardArticle;
    }

    public BoardArticle loadArticleFromFile(int id) {
        //11. id를 받는 메서드에서 공통부분 삭제 -> id -> fileName만들고 -> 살린메서드 호출
        // String file = "C:/java_file_test/article/article_" + id + ".txt";
        String fileName = "article_" + id + ".txt";
        return loadArticleFromFile(fileName);
    }

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

    public ArrayList<BoardArticle> getAllArticles() {
        // 4. 전체 다 가져오려면, pc파일들을 관리해주는 File이라는 클래스를 이용해야한다.
        // -> 5. TestFile 에서 연습하고 온다.


        //13. 받아줄 list생성
        ArrayList<BoardArticle> articles = new ArrayList<>();

        //12. 이제 부모폴더를 File객체에 -> file명리스트(배열) -> filename으로 객체 얻어오는 메소드()로 까지 완성했으니
        // 파일명 리스트를 받아오자.
        File articleFolder = new File("c:/java_file_test/article");
        String[] articleFileNames = articleFolder.list();

        for (String articleFileName : articleFileNames) {
            BoardArticle boardArticle = loadArticleFromFile(articleFileName);

            articles.add(boardArticle);
        }

        return articles;
    }
}
