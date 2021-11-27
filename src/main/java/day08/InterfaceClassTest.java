package day08;

public class InterfaceClassTest {
    public static void main(String[] args) {
        A a = new A();
        a.test();

    }
}
interface test {
    int func1();
}

abstract class Test {
    abstract void func1();
    abstract void func2();
    abstract void func3();
}
class A {
    void test() {
        B b = new B();
        int result = b.func1();
        System.out.println(result);
    }
}

class B implements test {
    public int func1(){
        return 1;
    }
}

interface TestInterface {
    void func1();
    void func2();
    void func3();
}
