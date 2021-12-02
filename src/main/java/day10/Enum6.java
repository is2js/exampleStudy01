package day10;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Enum6 {
    public static void main(String[] args) {
        System.out.println((AggFunction.findAgg("평균").calculate(Arrays.asList(1,2,3,4,5))));
    }
}

// 6. List<Integer>용 집계함수들 저장소 by list -> {return 사설집계메서드( list )}를 이용한 Function<List<Integer>, Integer> 함수형인페 채택
enum AggFunction {
	SUM("합계", list -> { return getSum(list); }),
	AVG("평균", list -> { return getSum(list) / list.size(); });


	private final String name;
	private final Function<List<Integer>, Integer> function
		;

	AggFunction(String name,
		Function<List<Integer>, Integer> function
	) {
		this.name = name;
		this.function
			= function
		;
	}

	private static Integer getSum(List<Integer> list) {
		return list.stream()
			.reduce(Integer::sum)
			.get();
	}

	public Integer calculate(List<Integer> list) {
		return function.apply(list);
	}

	public static AggFunction findAgg(String name) {
		return Arrays.stream( AggFunction.values() )
			.filter( e -> e.name.equals(name))
			.findAny()
			.orElseThrow(()-> new IllegalArgumentException());
	}

}

