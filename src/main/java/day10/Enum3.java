package day10;

import java.util.Arrays;

public class Enum3 {
    public static void main(String[] args) {
        //
		// System.out.println((LottoPrize.getRank(4).SIX.getPrize(LottoPrize.getRank(4))));
    }
}
// 3. 분기별 결과값을 매핑 + stream으로 일치하는 분기를 검색해서 반환하는 메서드 제공
enum LottoPrize {
    THREE(3, 5000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    BONUS(5, 3_000_000),
    SIX(6, 2_000_000_000);

    private final int matchingNumbers;
    private final int prize;

    LottoPrize(int matchingNumbers, int prize) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
    }

	// get검색은 파라미터로 받은 값을 stream으로 enum.values()중 1개 분기를 찾아 -> ENum.상수객체로 반환한다.
    public static LottoPrize getRank(int numberOfMatch) {
		return Arrays.stream(LottoPrize.values())
			.filter(e -> e.matchingNumbers == numberOfMatch)
			.findFirst()
			.orElseThrow(()->new IllegalArgumentException("3개 미만으로 일치합니다."));
    }


	public int getPrize() {
		return this.prize;
	}
}
