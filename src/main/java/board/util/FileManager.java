package board.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import board.BoardArticle;

public class FileManager {
    // 1. read하는 코드를 가져와 loadArticleFromFile() 메서드를 작성한다.
    // -> input으로는 파일경로가 들어온다.
    // -> 특이점으로는 한줄씩 뽑아서 출력하는게 아니라, 출력하면서 + String변수에 누적시킨다.
    // -> 메서드가 개발되면, testmain에서 테스트를 해보자. -> 2. TestFile
    //4. 기본 테스트를 통과했으면, 파라미터로 파일경로 -> id값만 입력해도 되도록 게시물 전용으로 변경해준다.
    // -> manager.loadArticleFromFile("C:/java_file_test/article/article_1.txt");
    // --> manager.loadArticleFromFile(id);
    // public void loadArticleFromFile(String file) {
    public void loadArticleFromFile(int id) {
        String file = "C:/java_file_test/article/article_" + id + ".txt";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            // 2. 중요) 업데이트변수 -> 업데이트후 그것 != null로 검사했는데
            //  업데이트가 누적업데이트로 바뀌면 -> 그 변수가지고... 검사를 바로 할 수 가 없다..
            // -> **결과값만 업데이트 변수** -> 끝에서 업데이트 후 while문에 바로 올라가서 검사 가능 했지만ㄴ
            // ->  **누적 업데이트 변수로 바뀜** -> 당시의 값을 대변하지 못하므로, while문에 올라가봤자 검사를 못한다.

            // 2-2) 누적변수가지고는, 해당 값을 검사를 못함..
            // while (line != null) {
            while (true) {
                // System.out.println(line);
                // line = br.readLine();
                // 2-3) 현재의 업데이트만 받아주는 변수를 생성 -> 검사 -> 통과하면 누적으로 바꾼다.
                String currentLine = br.readLine();
                if (currentLine == null) {
                    break;
                }
                line += br.readLine(); // 2-1) 업데이트 -> 누적업데이트로 바뀜.
            }

            // 누적된 것 최종출력만
            System.out.println(line);

            br.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다..");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 문제가 발생했습니다.");
        }
    }

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
