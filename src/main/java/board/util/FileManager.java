package board.util;

import java.io.FileWriter;
import java.io.IOException;

import board.BoardArticle;

// 1. 날짜, 랜덤숫자처럼 파일 입출력도 Util패키지에 새로운 클래스 `FileManager`로 만든다.
public class FileManager {
    // 2. 읽을때와 쓸때가 따로 있으니 나눠서 메서드화 해준다.

    // 3. 파일저장도, 객체(게시물)1개별로 -> 1개 파일에 저정하는 것이 좋다.
    // ex> article_1, article_2, .. article_게시물번호로 저장할 예정
    // -> 파일은 생성하더라도, 폴더는 미리 생성해놔야한다.
    // -> 계속바뀌는 파일명 속  id를 파라미터로 받아주자.
    // --> 이 때, 객체가 들어와서 그 정보를 저장할 예정이므로, 객체를 파라미터로 받고,
    //     거기서 파일명에 들어갈 id번호도 받자.
    // ---> 파라미터로 받은 객체. 변수를 쓸떼 안나온다? -> 접근제어자 public으로 바꿔주기..
    public void saveArticleToFile(BoardArticle article) {
        try {
            FileWriter writer =
                    new FileWriter("c:/java_file_test/article/article_" + article.id + ".txt");
            // TODO: 객체(게시물) 내용 저장
            // 6.write는 줄바꿈 안해준다. -> 직접 구분자 이용해서 붙혀주자.
            // 안녕하세요내용1입니다.2021.12.08
            // -> [[마지막꺼 제외]]하고 끝에 콤마를 붙혀줘보자.
            // -> my) 구분자를 넣어서 저장해줘야 안깨진다!!!!!!! 필수!!
            // 1,안녕하세요,내용1입니다.,1,2021.12.08,20,

            // writer.write(article.id);
            // 7. 변수마다 뒤에 구분자붙혀서 writer하는 것이 필수인데,
            // -> 앞에 설명도 같이 적어줘야 순서를 기억안해도 된다.
            // id:1,title:안녕하세요,body:내용1입니다.,memberId:1,regDate:2021.12.08,hit:20
            // -> **줄바꿈도 해주면 좋겠지만, 어차피 컴퓨터가 읽어올꺼라, 구분자면 충분하다.**
            writer.write("id:" + article.id + ",");
            writer.write("title:" + article.title + ",");
            writer.write("body:" + article.body + ",");
            writer.write("memberId:" + article.memberId + ",");
            writer.write("regDate:" + article.regDate + ",");
            writer.write("hit:" + article.hit);
            // 4. class로 만든 모듈 테스트는, Test클래스-main에서 객체 생성해서 한다.
            // -> 준비물이 필요하여 다른 객체를 만든다면, new 객체( 로 검색해서, 이전에 만든 경험을 살려서 만들어준다.

            writer.close();
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 문제가 발생했습니다.");
            // 5. 무슨 문제가 발생햇는지 확인하고 싶다면 e를 찍어준다.
            e.printStackTrace();
        }
    }
}
