package day12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_1 {
    public static void main(String[] args) throws IOException {
        // 1. 기본 출력코드
        try {
            FileWriter writer = new FileWriter("c:/java_file_test/test1.txt");

            writer.write("안녕하세요.\n");
            writer.write("반갑습니다.\n");
            writer.write("잘가세요.\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 문제가 발생했습니다.");
        }

        // 2. 기본 입력 코드
        try {
            FileReader reader = new FileReader("c:/java_file_test/test1.txt");
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

            br.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다..");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 문제가 발생했습니다.");
        }
    }
}
