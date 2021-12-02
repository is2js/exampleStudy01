package day10;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Enum8 {
    public static void main(String[] args) {
        //
		System.out.println((PASSorFAIL.findPassOrFail(false)));
    }
}

//8. 똑같은말에 대해 여러표현들을 저장해놓을 때
enum PASSorFAIL {
	PASS (Arrays.asList("Y", true, "1")),
	FAIL (Arrays.asList("N", false, "0"));

	private final List<? extends Serializable> list;

	PASSorFAIL(List<? extends Serializable> list) {
		this.list = list;
	}

	public static PASSorFAIL findPassOrFail(Object value) {
		return Arrays.stream(values())
			.filter( e -> e.list.stream().anyMatch( v -> v.equals(value)))
			.findAny()
			.orElseThrow(()->new IllegalArgumentException());
	}
}

