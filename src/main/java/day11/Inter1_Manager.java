package day11;

//<자동분기X 다형성만 이용>
// 1. Manage Class : static 객체 생성 method의 retypeType에 interface다형성을 이용함. 분기는 파라미터로 받아서 직접 if로함.
// -> 1) 스태틱 of,from처럼 (다형성으로 들어온 하위카테고리들의 구현체들) 객체생성 메서드 (return new 구현체객체 생성)
// -> 2) retype타입에 Inteface다형성을 이용해서 -> if분기별 return new 구현체 객체하게 한다. -> 분기는 파라미터가 결정한다.
// -> 3) 파라미터문자열 -> if로 내부에서 직접 분기
// -> 4) Interface + 통일강제메서드 일단 구현되어. 빈 코드를 채울 메서드를 정의 (public abstract 생략 -> 접근자 생략하자)
// -> 5)구현체(통일강제메서드명 + 구현은 자기맘대로 해서 사용할 하위카테고리Class) 구현
// -> 6) 외부에서는 Manager.스태틱구현체택1생성메소드 -> 구현체객체.통일강제메서드() 로  구현체마다 다르게 사용
// 이름강제메서드(사용처엔 코드비워둔, I에 일단구현해놓은,) 구현체 중
public class Inter1_Manager {
    public static void main(String[] args) {
		//6. Manager의 static객체생성메서드를 외부에선 스태틱으로 부르되, 다형성 중 객체를 선택할 파라미터를 전해준다.
		Parseable 구현체1 = Inter1_Manager.getParser("구현체1");
		//7. 구현체는 구현한 메서드를 사용한다.
		구현체1.parse("파일이름");
	}


	//1) 스태틱메서드로 하위카테고리 구현체들의 객체를 생성하게 한다.
	// -> 이 때, retype타입에 Inteface다형성을 이용해서 -> if분기별 return 구현체 객체하게 한다. -> 분기는 파라미터가 결정한다.
	public static Parseable getParser(String type) {
		//2) if분기는 파라미터로 결졍되도록한다.
		if (type.equals("구현체1")) {
			return new 구현체1();
		}
		if (type.equals("구현체2")) {
			return new 구현체2();
		}
		return new 구현체3();
	}

}

//4) Interface는 항상 일단구현될 강제통일 된 메서드(추상메서드, public abstract생략-> 접근자생략)을 구현한다.
interface Parseable {
	void parse(String fileName);
}

//5) 구현체(class, 다형성 적용될 하위카테고리)들은 interface의 통일강제메서드를 impl하도록 한다.
class 구현체1 implements Parseable {
	@Override
	public void parse(String fileName) {
        System.out.println("구현체 1로 채운 코드입니다.");
	}
}
class 구현체2 implements Parseable {
	@Override
	public void parse(String fileName) {
        System.out.println("구현체 2로 채운 코드입니다.");
	}
}
class 구현체3 implements Parseable {
	@Override
	public void parse(String fileName) {
        System.out.println("구현체 3로 채운 코드입니다.");
	}
}

