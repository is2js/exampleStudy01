package day05;

public class 생성자 {
    //1. 자바 첫 시작점인 main메소드는 static이 붙어야한다는 약속이다.
    // -> static은 공유의 목적으로 쓴다. my)유틸메소드, 유틸변수
    // -> 공유시키려면, 딱 정해져있어야 하는데, 그 장소가 class다. (인스턴스가 아니다) -> 객체보다 먼저 존재하는 class에서 먼저 공유되게 한다.
    // -> class에서 사용하는 static자원은  instance에서 사용하는 자원에 접근할 수 없다. (main or 유틸static 메소드에서, static안붙은 변수못씀)
    // --> 메인static에서 중요로직을 짠다면, 공유자원인  static자원밖에 못쓴다.
    // --> 공유하고 싶지 않은 자원을 main에서 쓰려면 어거지로 static을 써야한다. --> 메인이 아니라 각 class에서 로직을 작성해야한다!

    //2. 객체를 만들기 위해 -> class 파일을 새로 만든다!! 메인에서 정의 X
    // -> Person 파일, default 인스턴스변수만 만들고 -> 돌아와서 객체 생성하기
    public static void main(String[] args) {
//        Person p1 = new Person();
        // 3. 객체는 초기화하고 난 뒤에야 의미가 있다. -> 인스턴스변수가 10개나 된다면? -> 객체 초기화를 객체한테 맡기자.
        // -> method로서 setter()도 쓸 수 있지만, 메소드도 또 파라미터로 서로 다른값을 받아야하는 고생 -> setter(String name, int age.. )
        // ->
//        p1.name = "홍길동";
//        p1.age = 20;
//        p1.home = "대전";
//        p1.setData("홍길동", 20, "대전");
//        System.out.println(p1.name);

//        Person p2 = new Person();
//        p2.name = "조재성";
//        p2.age = 34;
//        p2.home = "서울";

        new Person();
    }
}
