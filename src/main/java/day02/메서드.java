package day02;

public class 메서드 {
    // 변수 -> 중복되는 값을 재활용하며 바꾸기  ex> sout(1) sout(1) sout(1)  -> a = 1; sout(a) sout(a) sout(a)
    // 메서드 -> 중복되는 코드를 재활용하며 값 바꿔쓰기
    // 주의점) 메서드 -> 메서드는 서로 독립적이다 -> 메서드안에 메서드를 만들어 쓸 수 없다.
    // --->  main메서드안에 메서드를 정의할수 X
    public static void main(String[] args) {
//        int dan = 3;
//        gugudan(dan);
//
//        int square = 메서드.square(dan);
//        System.out.println(square);

//        greeting(1);
//        greeting(2);
//        greeting(3);
//        greeting2(2, 3);

        printEven(5); // 2 4 6 8 10
    }

    public static boolean isEven(int num) {
        // num >=1 부터 대입가능. 0도 짝수라 판단하니 조심.
        if (num % 2 == 0) {
            return true;
        }
        return false;
    }
    private static void printEven(int number) {
        for (int i = 1; i <= number; i++) {
            if (!(isEven(i))) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void greeting(int lang) {
        if (lang == 1) {
            System.out.println("안녕하세요");
            return;
        }
        if (lang == 2) {
            System.out.println("하이~");
            return;
        }
        if (lang == 3) {
            System.out.println("봉쥬");
            return;
        }
    }

    private static void greeting2(int lang, int repeat) {
        String ment = "";
        if (lang == 1) {
            ment = "안녕하세요";
        }
        if (lang == 2) {
            ment = "하이";
        }
        if (lang == 3) {
            ment = "봉쥬";
        }
        for (int i = 0; i < repeat; i++) {
            System.out.println(ment);
        }
    }

    private static void gugudan(int dan) {
        for (int i = 1; i < 10; i++) {
            System.out.println(dan + " X " + i + " = " + (dan * i));
        }
    }

    public static int square(int num) {
        return num * num;
    }
}
