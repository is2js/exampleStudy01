package day02;

// 1. 배열연습을 위한 로또 프로그램

public class 형변환 {
    public static void main(String[] args) {
        // 형변환: 형이 달라지면 작동방식이 달라지니 구분해야한다.
        //"10"; //문자열  10;//정수int 10.0;//실수double -> 컴퓨터는 정수와 실수완전히 다른값으로 본다.
        System.out.println(10 / 3); // 3 -> 정수/정수
        System.out.println(10.0 / 3); // 3.33 -> 실수/정수 -> 컴퓨터가 (사람이 실수했네..) 자동 형변환.. 정수->실수 by auto casting
        System.out.println(10.0 / 3.0); // 3.33 -> 실수/정수

        int c = 10;
        double d = c; // 작은 것을 큰 것에 넣는 것은 가능한데, 반대는 안된다.
        // 작 -> 큰 자동형변환되서 들어간 것.
        System.out.println(d);

        double b = 10.3;
//        int a = b; // 큰 -> 작은 것은 안전하지않고짤리니, 자동형변환x 에러남.
        int a = (int)b; // 큰 -> 작은 것을 직접 수동형변환(int) 해서, 강제해서 형변환시켜보자.

        // 형변환이 필요한 이유: 그러나 `변수에 담긴 int`들을 계산하는데, double 실수값으로 받아야 할때는? ex> k=10, j=3 -> k/j
        // -> 직접 소수점 못찍어준다 -> **최소 1개는 소수점 찍히도록 double형변환 해줘야한다.
        int k = 10;
        int j = 3;
        System.out.println(k/j); // 3 -> 변수에 담기면 직접 실수변환용 소수점은 못찍어준다. (자동형변환 기대 못함)
        System.out.println( (double) k/j ); // 3.3333333333333335 수동형변환을 통해 소수점을 찍어준다.
//        System.out.println( (double) k/ (double)j ); // 3.3333333333333335 수동형변환을 통해 소수점을 찍어준다.
    }
}
