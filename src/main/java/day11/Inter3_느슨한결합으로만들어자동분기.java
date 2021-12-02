package day11;

public class Inter3_느슨한결합으로만들어자동분기 {

	//1. 두 클래스 사이의 조합(강한결합)으로 한놈을 객체생성 -> 메소드 사용(조합)해야한다면,
	// -> 조합 객체를 interface객체로 받아 다형성으로 처리 -> my) 분기처리로 바꾸고 싶은 변수자리(조합객체)를 Interface 객체로 바꾸세요. -> 기존 변수의 class는 interface구현체로 바꾸세요.
	// -> 조합 메서드이름을 강제통일해서, 어느 구현체든 해당 메소드 구현토록 함 -> my) 분기별 처리로 바꾸고싶은 코드자리를 method화하고, interface에게 추상메서드로 주도록 정의한다.
	// -> 조합될 자리에 구현체 중 어느것이 와도 상관없게 된다. 구현체만 오면 된다. -> my) 인터페이스 객체자리에, 분기별로 넣고싶은 구현체를 맘대로 생성해서 넣으세요.
    public static void main(String[] args) {
		//4-5) 느슨한결합으로서 구현체 아무거나 기존 조합객체자리에 넣어서 사용한다.
		C c = new C();
		A a = new A();

		a.methodA(c);// a객체.methodA(  조합객체  b )가 들어갔었나보다..
		a.methodA(new B());

	}

}
//2. 기존 강한결합을 만들던, 조합용 class
// class B {
// 	public void methodB() {
//         System.out.println("methodB() in B classs");
// 	}

//3. 강한결합(조합)을 사용하던 class
// class A {
// 	public void methodA(B b) {
// 		b.methodB();
// 	}

//4. 구현체별 분기처리 되도록 변환해보자.
class A {
	//4-1) 조합의 객체자리를 -> interface 객체로 바꾸고 -> 4-2) 조합용Class는 interface구현해 구현체중1이 되게 만든다.
	// public void methodA(B b) {
	public void methodA(MyInterface I) {
		I.methodB();
	}
}

//4-2) 조합용Class는 interface구현해 구현체 중1이 되게 만든다.
// class B {
class B implements MyInterface {
	public void methodB() {
        System.out.println("methodB() in B classs");
	}
}

//4-3) interface를 조합메서드를 가지게 정의한다.
interface MyInterface {
	void methodB();
}

//4-4) 이제 인터페이스 객체자리에 구현체Class 아무것인나 넣어서 사용한다.
// -> 하나 만들어서 main에서 사용해보자.
class C implements MyInterface {
	@Override
	public void methodB() {
        System.out.println("methodB() in C classs");
	}
}
