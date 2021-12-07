package board.test;

import java.util.Scanner;

import board.Main;

public class PagingTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int currentPageNo = 1;
        //3. 이래놓고 test하면 잘 되는데,
        // -> **start, end가 동적으로 바뀌는 순간은**
        // -> if 현재페이지 currentPageNo 변수가 어디있느냐에 따라서 바뀌어야한다.
        // -> 현재페이지가 1~5사이 범위 -> start1, end5
        // -> 현재페이지가 6~10사이 범위 -> start6, end10
        // -> 현재페이지 변화에 종속적으로 바뀐다.

        //4. 규칙을 찾아야해서 오래 걸릴 수 있음.
        // -> 현재페이지변수가  몇번째 페이지블럭(1~5, 6~10, ..) 에 있는지에 따라서 그 수가 달라진다.
        // -> 현재페이지에 따라 -> 현제 페이지블럭이 결정
        // --> 페이지블럭도 1부터 순서를 준다.
        // int currentBlock = 1;

        //5. 사실 1로 fix된게 아니라 계산이 들어가야한다.
        // currentPageNo에 따라 달라져야한다.
        // 1~5-> 1 (번째 페이지블럭)
        // 6~10-> 2 (번째 페이지블럭)
        // 11~15-> 3 (번째 페이지블럭)
        // 5n-4 ~ 5n -> n
        // -> cf) for반복문에서 i는 0부터 시작함 -> n 자리에 (i+1)대입
        // 5i+1 ~ 5(i+1)
        // [라이크라이언] 1) 총게갯:미정 2) 페보갯:5(고정) -> 3) 총페갯: ceil(총게갯/5) 아후
        // -> 4) for(총페갯수만큼 반복)문에서 0부터 시작하는 i(n-1끝)에 대해
        //           start = i*(페보갯)+1   ~  end = (i+1)*(페보갯)
        // --> for (int i = 0 ; i < n(총페이지수) ; i++)
        //      현제페이지 -> (i+1)
        //      페이지블럭start (i)*5    +1
        //      페이지블럭end   (i+1)*5
        // --->0부터 시작하는 i번째 페이지 상태이며, (i)*5    +1 번째부터 ~ (i+1)*5번재 데이터까지 진행된다.
        // --> 현재 페이지블럭을 찾는게 아니라, 현제 페이지블럭(i=0부터 시작)을 가지고, 현재 상태에서의 시작~끝번호를 구한다.

        // [차태진]  [[현제 페이지]]를   [[5(페이지당 보여질 갯수)]]로 나누어보고, [[[올림]]]하면  --> 현제 페이지 블럭 번호가 나온다.
        // 1/5 -> 0.xx -> 올림시 1
        // 5/5 -> 1

        // 6/5 -> 1.xxx -> 올림시 2
        // 10/5 -> 2

        // 공식: 현제페이지번호 / (페보갯)  -> 올림 -> 현재 페이지블럭 번호
        // -> 근데, 인트/인트는 무조건 정수가 나옴 -> 하나 실수형으로 만들어야함.
        // -> 이대로라면 올림하는의미가 없다 -> 아무거나 하나를 double로 형번환해주자.
        // int currentBlockNo = (int) Math.ceil((double) currentPageNo /5);

        //6. 현재 페이지번호(이전or다음)이 바뀔때마다 -> 현재 페이지블럭 번호가 갱신되어야한다.
        // 1~5 -> 1, 6~10->2로 갱신 ,   x~ currentPageNo ~x+5 -> (int) Math.ceil((double) currentPageNo /5);
        // -> **현제페이지번호가 선언될때가 아니라, 갱신될때마다 바뀌어야한다 -> 현재페이지번호가 업데이트되는 while문 내부로 들어가야한다.**
        // -> 7.으로 위치이동
        // int currentBlockNo = (int) Math.ceil((double) currentPageNo /5);
        // System.out.println(currentBlockNo);

        //2. 일단 while문 밖에서 페이징블럭 시작,끝번호를 선언해보자.
        // -> 9. 종속관계에 의해 2변수 while문으로 들어가서 생성됨
        // int startPageNoInBlock = 1;
        // int endPageNoInBlock = 5;

        while (true) {
            //7. update or 종료가 결정되는 input 뒤에 놓으면,
            // 종료시에 업데이트 될 수도 있다.
            // -> [update가 확정되는 순간==다시 루프 들어온 순간] 이면서,
            // --> 최초진입에서도 계산해주는 맨첨으로 올려준다.
            int currentBlockNo = (int) Math.ceil((double) currentPageNo /5);
            // System.out.println("현재 페이지 블럭 번호 : " + currentBlockNo);

            //8. [페이지블럭번호]를 알면 -> 페이지블럭 start, end번호를 쉽게 구한다.
            // 1 -> 1~5, 2 -> 6~10 ,  1부터 시작하는 n -> 5*(n-1) +1 ~ 5*(n)
            // 1) [현재페이지번호] -> 업데이트마다 [현재 페이지블럭번호] 종속
            // 2)  --> [현재페이지블럭번호] 업데이트 마다 --> 페이지블록의 [시작번호], [끝번호] 종속
            // --> 시작번호 끝번호도 [[[while문 변수의 업데이트마다 달라지므로]]] while문 안에 들어와야한다.
            //10.
            int startPageNoInBlock = (currentBlockNo-1)*5 + 1 ; // 1부터 시작하면, 페이지블럭 시작번호 : (n-1) * 페보갯  + 1
            // int endPageNoInBlock = currentBlockNo*5; // 1부터 시작하면, 페이지블럭 끝번호 : (n) * 페보갯
            int endPageNoInBlock = startPageNoInBlock+4; // 페보갯에 의해 start +4만 해줘도 된다.



            //1. 페이징블럭도 변수처리해준다. -> 변수사용부터 하고, **어디에 생성할지 천천히 생각해본다.**
            // 1-> startPageNoInBlock, 5-> endPageNoInBlock
            // for (int i = 1; i <= 5; i++) {
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
            if (pcmd ==1 ) {
                currentPageNo++;
            }
            if (pcmd == 2) {
                currentPageNo--;
            }

        }
    }
}
