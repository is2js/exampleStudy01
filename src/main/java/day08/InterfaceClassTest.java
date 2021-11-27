package day08;

public class InterfaceClassTest {
    public static void main(String[] args) {
        A a = new A();
        a.test();
    }
}

//8. 하카class 메소드명 통일 계약을 위해 func1 -> func으로 변경해서 이름 통일계약만들기
interface test {
    // int func1();
    int func();
}

abstract class Test {
    abstract void func1();
    abstract void func2();
    abstract void func3();
}


class A {
    void test() {
        // 2. 이제 A는 Bclass의존 메서드func1()사용 에 벗어나서 Cclass의존하여 C메서드(func3)으로 바꾸고 싶다고 가정한다.
        // -> 의존메서드를 다른class의 것으로 바꾼다면,
        // B b = new B();
        // int result = b.func1();

        // 3. -> func도 이름이 바뀌며.. 코드도 새로짜야한다.
        // -> B와 C가 메소드를 제공하는 방식이 다르기 때문이다.
        // C c = new C();
        // int result = c.func3();

        // 4. 이제 A는 C class말고 더 좋은 D class가 생겼다고 해서 거기에 메서드에 의존한다고 한다.
        // 6. A-> Dclass의 메소드를 의존해서 사용 -> 또 호출하는 코드가 달라진다.
        // -> 의존 class가 바뀔때마다, class제공기능을 사용하려면, 다 까보고 코드를 바꿔야한다.
        D d = new D();
        // int result = d.func4();
        //10. 자 메서드명 통일계약을 했으니, 객체가 바뀌어도, 메서드는 func()으로 통일되서 호출된다.
        int result = d.func();
        System.out.println(result);

        // 7. 예를 들어
        //  A(충전기개발회사) 다른 핸드폰(B,C,D)의 충전기 타입(method)을 의존하여 개발하고 싶다면?
        //  각 핸드폰별(하카별) 공통 충전기타입(method명 통일 -> 공통기능으로 가지도록 상카 뽑고 -> 다형성으로 일괄처리하면 -> 하카별 내부 분기처리)을 만들어서, 객체종류(핸드폰 종류만)만 바꿔 개발할 수 있게 해야한다.
        // 1) A가 의존할 수 있는 모든 메서드 class들이 func명을 통일한다.
        // -> 통일하자고 해도 func명 통일 안하는 놈 꼭있다 -> 약속으로 강제하도록 interface쓰라고 하자
        // -> [interface라는 계약서]에 [기능명, func명]을 통일한다.
        // -> 계약 따르는 class만 implements로 [계약을 따른다]
        // -> 8.~9. 메서드명통일 계약interface 만들고, 하카별로 동의하도록 implements 하고 오자.


    }
}


class B implements test {
    //9. 자 메서드명 통일 계약(interface)따르도록 수정하자.
    // public int func1() {
    public int func() {
        return 1;
    }
}
// 1. class C를 만들고, 인터페이스 구현없이 func3()을 가지고 있다고 가정하자.
// class C {
//9. 자 메서드명 통일 계약(interface)따르도록 수정하자.
class C implements test {
    // public int func3() {
    public int func() {
        return 3;
    }
}

//5.
// class D {
//9. 자 메서드명 통일 계약(interface)따르도록 수정하자.
class D implements test {
    // public int func4() {
    public int func() {
        return 4;
    }
}

interface TestInterface {
    void func1();

    void func2();

    void func3();
}
