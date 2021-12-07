package board.test;

import java.util.ArrayList;
import java.util.Scanner;

public class PagingTest {

    public static void main(String[] args) {
        int pageCountPerBlock = 5;
        int itemCountPerPage = 3;
        int currentPageNo = 1;

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String str = "data" + i;
            datas.add(str);
        }

        while (true) {
            int currentBlockNo = (int) Math.ceil((double) currentPageNo / pageCountPerBlock);

            int startPageNoInBlock = pageCountPerBlock * (currentBlockNo - 1) + 1;
            int endPageNoInBlock =
                    startPageNoInBlock + (pageCountPerBlock - 1);
            int startIndex = itemCountPerPage * (currentPageNo - 1);
            int endIndex = startIndex + itemCountPerPage;

            for (int i = startIndex; i < endIndex; i++) {
                System.out.println(datas.get(i));
                System.out.println("=============");
            }
            // my1) Board에 적용시켰으면 주석처리 해버리자 ㅋ
            // for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
            //     if (i == currentPageNo) {
            //         System.out.print("[" + i + "] ");
            //         continue;
            //     }
            //     System.out.print(i + " ");
            // }
            // System.out.println();

            System.out.println("1. 다음, 2. 이전");
            //my2)
            // int pcmd = Integer.parseInt(scanner.nextLine());
            // if (pcmd == 1) {
            //     currentPageNo++;
            // }
            // if (pcmd == 2) {
            //     currentPageNo--;
            // }
        }
    }
}
