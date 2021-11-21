package day02;

// 1. 배열연습을 위한 로또 프로그램

public class 로또프로그램 {
    public static void main(String[] args) {
        // 형변환: 형이 달라지면 작동방식이 달라지니 구분해야한다.
        //"10"; //문자열  10;//정수int 10.0;//실수double -> 컴퓨터는 정수와 실수완전히 다른값으로 본다.
        System.out.println(10 / 3); // 3 -> 정수/정수
        System.out.println(10.0 / 3); // 3.33 -> 실수/정수 -> 컴퓨터가 (사람이 실수했네..) 자동 형변환.. 정수->실수 by auto casting
        System.out.println(10.0 / 3.0); // 3.33 -> 실수/정수


    }
}
