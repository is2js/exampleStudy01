package day05;

public class Person {
    // 3. 인스턴스 변수로 일단 만든다. 공유.. 유틸변수아니며 그냥 다 인스턴스변수!! ->
    // (non-static) vs static  ,   public vs private(생성자 및 getter, setter메서드로 접근)
    String name;
    int age;
    String home;

    public Person(String name, int age, String home) {
        this.name = name;
        this.age = age;
        this.home = home;
        System.out.println("객체가 생성될때마다 생성자는 반드시 실행됩니다~");
    }
    public Person() {
        System.out.println("객체가 생성될때마다 생성자는 반드시 실행됩니다~");
    }

//    public void setData(String name, int age, String home) {
//        this.name = name;
//        this.age = age;
//        this.home = home;
//    }
}
