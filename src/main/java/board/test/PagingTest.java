package board.test;

import java.util.ArrayList;
import java.util.Scanner;

public class PagingTest {

    public static void main(String[] args) {
        // 11. 상수화 하더라도,가져갈 수 있게 main함수안에다가.. 변수로 수정해서 유지하자.
        int pageCountPerBlock = 5;
        int itemCountPerPage = 3;

        Scanner scanner = new Scanner(System.in);
        int currentPageNo = 1;
        // 1. db로 List<String>을 만들고, 30 개 정도 데이터(게시물)를 만들어준다.
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String str = "data" + i;
            datas.add(str);
        }
        // 2. 데이터를 게시물처럼 1개씩 뿌려준다. -> 30개 전체가 다나와버린다.
        // for (int i = 0; i < datas.size(); i++) {
        //     System.out.println(datas.get(i));
        //     System.out.println("=============");
        // }

        // 3. 이제, 30개 게시물 중에, [한 페이지당 3개의 게시물만 보이게 ]하자
        // -> 여기가 진짜 페보갯..ㅠㅠ
        // -> 전 시간엔 블보페갯..
        // for (int i = 0; i < 3; i++) {
        //     System.out.println(datas.get(i));
        //     System.out.println("=============");
        // }
        // 4.
        // -> 다음늘 눌렀을 때 4~6(i=3~5)번이 보이게 동적으로 수정해야한다.
        // --> 이것 역시 동적으로 뭐에 종속되느냐??
        // --> 우리가 입력(업데이트)하는 것은 [현재페이지 ]->
        // --> 현재페이지가 바뀜에 따라, +1시 +3칸씩 증가하면 된다.?
        // -> 결국엔, 우리가 현재페이지를 바꿈에 따라, 모든 것이 결정되어 계산되어야한다.

        // 5.
        // -> for문속 i =0 , 3을 변수로 바꾸고 없으니까 선언까지 해준다.
        // int startIndex = 0;
        // int endIndex = 0 + 3;
        // int endIndex = startIndex + 3; // 정해진다.

        // 6. 또 현재페이지에 따라서 변하는 공식을 확인해야한다.
        // 현재페이지 1 -> 0~2(3) , 2->3~5(6) , 3-> 6~8(9)...  n -> 3*(n-1) ~  3*n
        // -> 7. 현재페이지의 수정은 while문안에서 이루어지니, 종속관계로서 while문 안으로 넣어줘야한다.
        // int startIndex = 3 * (currentPageNo - 1);
        // int endIndex = startIndex + 3; // 정해진다.
        // for (int i = startIndex; i < endIndex; i++) {
        //     System.out.println(datas.get(i));
        //     System.out.println("=============");
        // }

        while (true) {
            // int currentBlockNo = (int) Math.ceil((double) currentPageNo / 5);
            // 9. 페이지블럭당 보여질 페이지갯수 5 -> 여러군데서 중복되니, 변수 -> 상수화한다.
            // -> pageCountPerBlock
            int currentBlockNo = (int) Math.ceil((double) currentPageNo / pageCountPerBlock);

            // 1부터 시작하면, 페이지블럭 시작번호 : (n-1) * [페보갯 -> 블럭당 보여질 페이지 갯]  + 1
            int startPageNoInBlock = pageCountPerBlock * (currentBlockNo - 1) + 1;
            int endPageNoInBlock =
                    startPageNoInBlock + (pageCountPerBlock - 1); // 페보갯에 의해 start +4만 해줘도 된다.
            // 8. 이동 후 test -> list에서 게시물을 꺼내오다보니까 범위를 넘어서면 index에러남.
            // int startIndex = 3 * (currentPageNo - 1);
            // 10. 페이지당 보여질 갯수 3 -> 여러군데 쓰이니, 변수화한다.
            int startIndex = itemCountPerPage * (currentPageNo - 1);
            int endIndex = startIndex + itemCountPerPage; // 정해진다.

            for (int i = startIndex; i < endIndex; i++) {
                System.out.println(datas.get(i));
                System.out.println("=============");
            }
            for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
                if (i == currentPageNo) {
                    System.out.print("[" + i + "] ");
                    continue;
                }
                System.out.print(i + " ");
            }
            System.out.println();

            System.out.println("1. 다음, 2. 이전");
            int pcmd = Integer.parseInt(scanner.nextLine());
            if (pcmd == 1) {
                currentPageNo++;
            }
            if (pcmd == 2) {
                currentPageNo--;
            }
        }
    }
}
