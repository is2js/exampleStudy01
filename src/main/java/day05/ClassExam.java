//package day05;
//// 클래스: <기능, 데이터처리를 할 수 있는> 메소드들과 <데이터들의> 모음집. 메소드들은 클래스를 통해 기능발휘함.
//public class ClassExam {
//
//    // 1. 데이터(결과값?)이 있어야한다. 모든 객체들 공통 결과값.... static. or 편하게 쓸 수 잇는 유틸변수 static
//    static int a = 10;
//    static int b = 20;
//    static int c = 0;
//    // c = a+b; -> 데이터 처리, 연산은 메소드에서 하게되어있다. class에서 데이터처리는 못함. (에러남) -> 그 메소드들을 모아둔게 class다.
//
//    public static void main(String[] args) {
//        // 2. 사람 표현해보기 -> 데이터가 있어야하고(class), (method)안에서 데이터를 처리해서.. -> 데이터 처리는 메소드안에서
//
//        System.out.println("20살 홍길동");
//        System.out.println("20살 홍길동"); // 서로 다른 것 -> 동일하게 접근하려면 변수에 담아야한다.
//        // 데이터는 메소드(지역변수)가 아닌 class에서 변수로 만들어쓴다.
//        String name = "홍길동";
//        int age = 21;
//        String home = "대전";
////        introduce(age, name, home);
//
//        // 3. 1개 단위(=1명) 당 필요한 데이터(변수)가 여러개(3개)다 -> 변수로 쓰다가 놓치지말고, 묶어서 관리해야한다.
//        // -> 데이터 묶음도 class. (기능 모음집도 class)
//        // -> 사람이 변수로 여러개를 직접 관리하면 100% 실수한다 -> class로 변수들을 묶어버린다.
//        // 매번 달라지는(private = 인스턴스) 데이터(변수)의 묶음 -> 같이 들고 다닐려면 묶어야함. -> class
////        String name2 = "조재성";
////        int age2 = 34;
////        String home2 = "대전";
//        Person p2 = new Person();
//        p2.name = "조재성";
//        p2.age = 34;
//        p2.home = "대전";
//        introduce(p2);
//
//        // 5. 변수 3개 대신 이제 class로 인스턴스를 만들면 된다.
//        // -> .으로 안에 있는 변수(데이터)에 접근한다.
//        Person hong = new Person();
//        hong.name = "홍길동";
//        hong.age = 20;
//        hong.home = "서울";
//        introduce(hong);
//
////        // 7. class도 타입이 된다.
//        Person[] persons = new Person[3];
//        persons[0] = hong;
//        persons[1] = p2;
////        persons[2] = hong;
//
//
//        for (Person p : persons) {
//            System.out.println("11");
//        }
//
//    }
//
//    //6. 파라미터를 인스턴스변수가 아니라 객체1개로 받아버리자. 옮길때는 통째로 옮겨야함!
////    private static void introduce(int age, String name, String home) {
//    private static void introduce(Person person) {
//        System.out.println(person.home + "에 사는" + person.age + "살 " + person.name + "입니다.");
//    }
//}
//// 4. class로 1명을 만들기 위한 변수 데이터들을 묵어보자.
////class Person {
////    String name;
////    String home;
////    int age;
////}
