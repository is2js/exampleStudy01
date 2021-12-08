package board.test;

import java.io.File;

import board.BoardArticle;
import board.util.FileManager;
import board.util.MyUtil;

public class TestFile {
    public static void main(String[] args) {
        // FileManager manager = new FileManager();
        //5. File객체를 생성하면, 1개 파일에 대한 정보를 볼 수 있다.
        File file = new File("c:/java_file_test/article/article_1.txt");
        System.out.println(file.getName()); //파일명
        System.out.println(file.getParentFile()); // 부모폴더경로
        System.out.println(file.isDirectory()); // 폴더냐 디렉토리냐

        //6. 파일객체로 디렉토리(폴더)도 가져올 수 있다.
        File file2 = new File("c:/java_file_test/article");
        System.out.println(file2.getName()); //폴더명
        System.out.println(file2.getParentFile()); // 부모폴더경로 (내가 폴더라도.. 나 자신빼고)
        System.out.println(file2.isDirectory()); //

        //7. File객체를 쓰는 이유 : 파일명 리스트list (or 파일들을 File객체로  listfile) 가져올 수 있어서
        // -> 이름만 list지.. 배열이 나와서 바로 print는 안됨.
        String[] fileNames = file2.list();
        for (String fileName : fileNames) {
            //8. 문제점 : 파일명을 1 10 11 . 순으로 가져옴.. 정렬이 필요함.. -> 나중에 처리
            // -> 다시 돌아간다. 일단 File객체로 파일명(list)들을 얻을 수 있으니 -> 그 안에 포함된 id로 객체로 불러올 수 도 있음.
            System.out.println(fileName);
        }
    }
}
