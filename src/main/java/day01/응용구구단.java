package day01;

import java.util.Scanner;

public class 응용구구단 {
    public static void main(String[] args) {
        //1. 일단 2단부터  9단까지 출력시키기 by 이중 for문
//        for (int dan = 2; dan < 10; dan++) {
//            for (int gop = 1; gop < 10; gop++) {
//                System.out.println(dan + " X " + gop + " = " + (dan * gop));
//            }

        //2. 사용자가 원하는 것을 입력을 받아, 코드수정없이 바뀌도록 응용구구단 만들기
        Scanner sc = new Scanner(System.in);
        System.out.println("시작단의 숫자는?");
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("끝단의 숫자는?");
        int m = Integer.parseInt(sc.nextLine());
        System.out.println("곱의 갯수: ");
        int limit = Integer.parseInt(sc.nextLine());
        for (int dan = n; dan <= m; dan++) {
            if (dan % 2 != 0) {
                System.out.println("홀수단은 스킵");
                continue;
            }
            for (int gop = 1; gop <= limit; gop++) {
                    System.out.println(dan + " X " + gop + " = " + (dan * gop));

            }
        }


    }
}
