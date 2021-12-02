package day10;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Enum4 {
    public static void main(String[] args) {
		double fisrtValue = Double.parseDouble("3");
		double secondValue = Double.parseDouble("2");
		Double result = Operator.findOperator("/").calculate(fisrtValue, secondValue);
        System.out.println(result);
	}
}
// 4. 람다식을 매핑시켜서 사용함. -> 실제사용시  외부에서, Enum.스태틱finder메서드로 Enum객체 반환 후-> Enum객체.람다(가덮어쓴)호출용인페구현메서드() 필요할 듯.
// 1) 원하는 람다식의 파라미터 -> 반환값에 맞는 함수형 인페를 고르고 변수로 선언함. cf) 람다식 -> 함수형인페.지정메서드()를 오버라이딩 구현해놓은 것.
// 2) <public, not static - Enum객체마다 호출할 람다호출용인페구현메서드 구현> 원하는 람다식의 파라미터 -> 반환값을 선언부로 하고, 파라미터로 함수형인페 속 내장메소드를 호출후 return하는 메소드 구현
// 3) 외부에서는 스태틱finder로 찾은 enum객체.람다호출용인페구현메서드()로 람다식을 호출시킨다
enum Operator {
	plus("+", (a,b)-> (a+b)),
	minus("-", (a,b)-> (a-b)),
	multiply("*", (a,b)-> (a*b)),
	divide("/", (a,b)-> (a/b));

	private final String operator;
	//1) 원하는 람다식 타입의 함수형인페 변수를 선언함.
	// 2개 똑같은 건데, BinaryOperator는 타입1개로 통일한 것. -> 둘다 호출은 apply임.
    // public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    // }
    private final BiFunction<Double, Double, Double> biFunction;

	Operator(String operator,
		BiFunction<Double, Double, Double> biFunction) {
		this.operator = operator;
		this.biFunction = biFunction;
	}

	//2)
	public Double calculate(Double a, Double b) {
		return this.biFunction.apply(a, b);
	}

	//3) 외부에서는 스태틱finder메서드로 해당Enum객체를 반환받아, 람다식(가덮어쓴)용 인페구현메서드를 쓴다.
	public static Operator findOperator(String operator){
		return Arrays.stream ( Operator.values() )
			.filter( e -> e.operator.equals(operator))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException());
	}
	

}
