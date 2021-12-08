package day12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_1 {
    public static void main(String[] args) throws IOException {
        // 1. 가장 쉬운 FileWriter를 쓴다.
        // -> 윈도우 절대경로(생성할 파일.txt까지 포함)를 생성자 파라미터로 준다 c:/java_file_test
        // -> 예외처리 IOException을 강제한다.
        try {
            FileWriter writer = new FileWriter("c:/java_file_test/test1.txt");
            // 3.  writer생성시, catch에 안걸렸으면 아래 코드가 실행될 것이다.
            // -> 생성된 파일을 확인해보니, write()할때마다 줄바꿈은 제공되지 않음.
            // -> 한번 열때마다 덮어쓰기됨.
            // writer.write("안녕하세요.");
            writer.write("안녕하세요.\n");
            writer.write("반갑습니다.\n");
            writer.write("잘가세요.\n");
            // 4. 다 적었으면 close();도 해줘야한다.
            writer.close();
        } catch (IOException e) {
            // 2. 파일이 없거나, 접근 권한 없을 때 IOException에 걸린다.
            System.out.println("파일 쓰기 중 문제가 발생했습니다.");
        }

        // 5. 파일read는 FileReader로 한다.
        // -> 빨간줄이 안떠도  ctrl+.에 surround  try-catch가 뜬다.
        try {
            FileReader reader = new FileReader("c:/java_file_test/test1.txt");

            // 6.reader가 read할 때는 숫자로 읽어온다.
            // int read = reader.read();
            // System.out.println("읽어온 파일 : " + read); //읽어온 파일 : 50504
            // -> 안녕하세요의 안 -> 50504 -> 한글자씩 숫자로 읽어오는...

            //7. 읽어올때는 FileReader가 숫자로 읽어오니, 추가 준비물이 필요하다.
            // -> BufferedReader -> 파라미터로 FileReader객체를 넣어준다.
            // -> 최종 원하는 것은 버퍼드리더 객체임.
            BufferedReader br = new BufferedReader(reader);
            // int read1 = br.read();
            // //8. BufferedReader에만 readLine이 있다.
            // String line = br.readLine();
            // System.out.println(line);
            // //9. 한번 readLine()할때마다 추가로 아랫부분을 읽어온다.
            // String line2 = br.readLine();
            // System.out.println(line2);
            // String line3 = br.readLine();
            // System.out.println(line3);
            // // 10. 파일에 더이상 line이 없으면, null이 출력된다.
            // String line4 = br.readLine();
            // System.out.println(line4);
            // 11. 마지막에 null이 나오는 것을 이용해서 반복문에 넣어서 null나올때까지 돌려서 가져온다.
            // -> null나오기 직전까지 돌리기~~ != null~
            // -> while ()에서 검사하기 직전 -> 마지막에 조건변수 업데이트 시키는 규칙으로 마지막에 readLine해주자~
            // --> 업데이트변수라도 첫번째는 넣어줘야한다~
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

            // 다썼으면 닫아준다.
            br.close();
            reader.close();


        } catch (FileNotFoundException e) {
            //6. 읽을때는 IO예외 + 시도할 파일 자체가 없는 파일낫파운드 예외를 먼저 처리해줘야한다.
            System.out.println("파일이 없습니다..");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 문제가 발생했습니다.");
        }
    }
}
