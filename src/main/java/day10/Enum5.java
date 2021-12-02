package day10;

import java.util.Arrays;
import java.util.function.Function;

public class Enum5 {
    public static void main(String[] args) {
        // 똑같은데 함수형인페를 걍 Function으로 사용하는 경우

		System.out.println((SaleMoney.findSale("공급액").calculate(1000L)));

    }
}

enum SaleMoney {

	ORIGIN("매출액", origin -> origin ),
	SUPPLY("공급액", origin -> Math.round(origin.doubleValue()) * 10/11 ),
	VAT("부가세", origin -> origin /11),
	NOTHING("없음", origin -> 0L);

	private final String name;
	private final Function<Long, Long> function;

	SaleMoney(String name, Function<Long, Long> function) {
		this.name = name;
		this.function = function;
	}

	public Long calculate(Long money){
		return function.apply(money);
	}

	public static SaleMoney findSale(String name) {
		return Arrays.stream(SaleMoney.values())
			.filter(e->e.name.equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException());
	}

}
