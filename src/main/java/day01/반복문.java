package day01;

public class 반복문 {
    public static void main(String[] args) {

//        if도 조건에는 변수1개 도입이 필요하다. -> 조건변수
//        if (a < 10) {
//
//        }

        //1. 조건변수 1개 이용  while   from if
        // -> 보통 조건변수를 증가시킨다.
        int a = 1;
        // a가 10에 종료 -> 9까지 진행
        while (a < 10) {
            System.out.println(a);
            a += 1;
        }
        //2. 무한반복 + break
        int c = 1;
        while (true) {
            if ( c==10) {
                break;
            }
            System.out.println(c);
            c++;
        }
    }
}

