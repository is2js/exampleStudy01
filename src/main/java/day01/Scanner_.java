package day01;

import java.util.Scanner;

public class Scanner_ {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        // 입력받으려면 일단 enter칠 때 까지 대기해 -> 문자열이 그대로 sc.nextLine()으로 들어간다.
        //1) 문자열 -> nextLine()
//        String str = sc.nextLine();
        // * 2) 숫자 -> nextInt인데, 쓰지말자!!!!!!!! -> 입력이 꼬인다.
//        int number = sc.nextInt();
        // 숫자로 받고 싶다면  문자열로 먼저 받고 -> parseInt씌운 뒤  int로 받으면 됨.
        int num = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();
        System.out.println(num);


//        System.out.println("당신입력값 은 : " + (number*20));

    }
}
