package day10;

import java.util.Arrays;
import java.util.List;

public class Enum2 {
    public static void main(String[] args) {
        //
        System.out.println(Winner.WINNER.isWinner("ㄹㄹ)"));
        System.out.println(Winner.isContains("패배", "bb"));
        System.out.println(Winner.isContains("승리", "ㄹㄹ"));
    }
}
//1. <이미 정해진 list> 데이터 groupby를 List변수명 대신 이넘상수값 - 문자열도매핑해서 - 그룹별 List를 관리으로 할 수 있음.
// -> 원본list를 빼내려면 getter로 필요한 듯. cf) 람다식은, 메서드로 구현해서 인페.메소드() 사용하게함.
// -> 원본list를 빼내기보단, 각 group별로, list를 이용한 기능 결과값(contains, size 등) 도출 public 메서드를 추가 제공한다.
enum Winner {
    WINNER("승리", Arrays.asList("ㅋㅋㅋ","ㄹㄹ","ㅁㅁ")),
    LOSER("패배", Arrays.asList("aa","bb","ccc"));

	private final String title;
	private final List<String> list;

	Winner(String winner, List<String> list) {
		this.title = winner;
		this.list = list;
	}

	// public 메서드로 groupby데이터들을 이용해서 개별group별로 기능제공
	// -> 문제는 내부분기처리를 안하고 바로.. 메서드명에 바로 그룹명도 명시해서 제공해야함
	public boolean isWinner(String name) {
		return this.list.contains(name);
	}
	public static boolean isContains(String title, String name) {
		return Arrays.stream( Winner.values())
			.filter( e -> e.title.equals(title))
			.findAny()
			.orElseThrow( () -> new IllegalArgumentException())
			.list.contains(name);
	}


}
