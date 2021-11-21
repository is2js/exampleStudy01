package day02;

import java.util.Random;

public class 랜덤수뽑기 {
    public static void main(String[] args) {
//        int random = new Random().nextInt(10); // 0~n-1
//        System.out.println(random);
        // 자바API(미리 만들어서 제공하는 도구) -> Math
        // Math.random(); // 0.001~0.999 실수값 제공 -> * 10^n -> (int)를 통해.. 0~9, 0~99 등의 난수 발생
//        double random = Math.random();
//        System.out.println((int) (random * 10 + 1)); // (int) 1.xxx ~ 10.xxxx -> 1~10

        // 6개 뽑으려면, 반복문으로 6번 실행시키면 됨.
        for (int i = 0; i < 6; i++) {
            double random = Math.random();
            System.out.println((int) (random * 10 + 1));
        }
    }
}
