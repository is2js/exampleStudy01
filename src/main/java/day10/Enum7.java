package day10;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Enum7 {
    public static void main(String[] args) {
        //
        System.out.println(Program.findGroup("우아한 테크코스"));
    }
}
// 1. Program -> 정해진 list들의 name줘서 group화 및 기능제공 2 -> list그룹화 이후, 부여한 그룹명 검색
enum Program {
    EDUCATION("교육용 프로그램", Arrays.asList("우아한 테크코스", "삼성 소프트웨어", "서울42", "패스트 캠퍼스")),
    FOR_JOB("취업용 프로그램", Arrays.asList("국비지원 아카데미", "우아한 테크캠프", "프로그래머스"));

    private final String name;
    private final List<String> list;

    Program(String name, List<String> list) {
        this.name = name;
        this.list = list;
    }

    public static Program findGroup(String name) {
        return Arrays.stream(Program.values())
                .filter(e -> e.list.stream().anyMatch(element -> element.equals(name)))
                .filter(hasNameInGroup(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 프로그램입니다."));
    }

	private static Predicate<Program> hasNameInGroup(String name) {
		return e -> e.list.stream().anyMatch(element -> element.equals(name));
	}

}
