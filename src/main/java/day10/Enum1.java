package day10;

//https://velog.io/@kyle/%EC%9E%90%EB%B0%94-Enum-%EA%B8%B0%EB%B3%B8-%EB%B0%8F-%ED%99%9C%EC%9A%A9
public class Enum1 {
    public static void main(String[] args) {
		//5. 내부 api
	//	1) Enum.values -> 상수 객체(문자열x)의 배열을 뽑음. print시 상수명 나옴.
		Rank[] values = Rank.values();
        for (Rank value : values) {
            //문자열이 아니라, Rank 객체 -> 각 상수Enum들이다.
            System.out.println(value);
        }
	//	2) Enum.valueOf("ENUM을 문자열로 직접 타이핑") -> 해당하는 상수 객체를 반환(거의 탐색임)
		Rank.valueOf("THREE");
		Rank.valueOf("FOUR");
		// System.out.println((Rank.valueOf("xxx"))); // exception뜸.
        // 3) Enum.객체.ordinal() -> 0부터 순서대로 정의한 순서를 반환함.
        for (Rank value : values) {
            System.out.println(value.ordinal());
        }
	}
}
// 1.매핑문자열별(분기별) -> 매핑상수(숫자) + 싱글톤변수 사용가능. -> 외부 Enum.valueOf("매핑 문자열")
enum Rank {
	//1. 그러고나서 ENUM 값마다, 매핑값or매핑람다식을 먼저 적어주자.
	THREE(3, 4_000),
	FOUR(3, 4_000),
	FIVE(3, 4_000);

	//2. 생성자가 편하게 생성되려면!!!  ENUM.상수.마다 가지는 값 변수들을 private final로 미리 생성하고 -> 생성자 자동생성하는게 편함.
	private final int match;
	private final int money;
	//3. **Enum내부에서는 <자동싱글톤>으로 변수를 생성할 수 있다. 변하지 않는 ENUM.상수와 동일하게 취급된다.**
	private int count;

	Rank(int match, int money) {
		this.match = match;
		this.money = money;
	}

	//4. 싱글톤변수라도, private 변수 -> public method로 관리한다.
	public void plusCount() {
		this.count++;
	}
}
