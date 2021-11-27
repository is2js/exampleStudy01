package day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortObjectWithMethod3 {
    public static void main(String[] args) {
        사람2 사람1 = new 사람2("홍길동", 20);
        사람2 사람2 = new 사람2("유관순", 17);
        사람2 사람3 = new 사람2("을지문덕", 21);
        사람2 사람4 = new 사람2("이순신", 30);
        사람2 사람5 = new 사람2("강감찬", 28);

        ArrayList<사람2> 사람2들 = new ArrayList<>();

        사람2들.add(사람1);
        사람2들.add(사람2);
        사람2들.add(사람3);
        사람2들.add(사람4);
        사람2들.add(사람5);

        //1. 자바가 Comparator(인터페이스)를 제공해준다.
        // Collections.sort(사람2들, ???) -> alt + F12 -> F12
        // sort(List<T> list, Comparator<? super T> c)
        // -> sort의 2번째 인자로는 Comparator라는 인터페이스를 받는다?
        // -> Comparator 구현체(Class)를 정의하고, 그 통일된 메소드명을 구현할 때, 원하는 코드를 작성한 뒤
        // -> 구현체 객체를 넣어주면, 원하는 코드를 내부에 작동시킬 수 있다.
        // -> 2.
        // Collections.sort(사람2들, )

        //5. interface구현체 생성 -> compare구현 (객체.나이 기준 오름차순 정렬되게) -> Interface 구현체 객체 자리에 비교기준을 넣어준다.
        RealComparator realComparator = new RealComparator();
        Collections.sort(사람2들, realComparator);
        for (사람2 사람 : 사람2들) {
            사람.자기소개();
        }
    }
}

//2. 인터페이스 구현체를 인자로 받는 곳의 Interface(Comparator)의 구현체(Class)를 만들자.
// -> ide이용해서 자동으로 구현하자. + <T> 모든 객체를 받기위해? 받지 않기 위해? 비교할 객체 타입도 정해주자.
// -> 실제로 제네릭 안적어주면 모든 객체를 저렇게 받도록 정의된다...
// @Override
// public int compare(Object o1, Object o2) {
//     return 0;
// }
class RealComparator implements Comparator<사람2> {
    // 3. 메소드를 구현해서 내가 원하는 코드를 집어넣자. compare는 2객체의 비교기준을 넣어주는데
    // cf) 숫자비교: > <  / 문자열사전순비교:  (기준)compareTo(비교) > 0 크다  = 0 같다 <0 작다
    @Override
    public int compare(사람2 o1, 사람2 o2) {
        //4. 여기서 따지는 비교기준 코드를 따지면 된다.
        // 1) 순서대로 인자가 주어진다.
        // 2) 최종 int로 반환해야하는데,`0이상 int 반환시 swap`될 예정이니
        // -> `return -1;을 swap안되는 기준`, `return 1;`을 바뀌는 기준으로 `o1, o2, if분기문`을 작성한다.
        // if (o1.나이 > o2.나이) { // int 오름차순
        // if (o1.나이 < o2.나이) {  // int 내림차순
        // if (o1.이름.compareTo(o2.이름) > 0 ) { // 문자열 오름차순
        if (o1.이름.compareTo(o2.이름) < 0 ) { // 문자열 내림차순
            //왼쪽께 크면 스왑 -> 큰 것을 오른족으로 -> 나이 기준 오름차순정렬하자~
            return 1;
        }
        return -1;
    }
}



class 사람2 {
    String 이름;
    int 나이;

    public 사람2(String 이름, int 나이) {
        this.이름 = 이름;
        this.나이 = 나이;
    }

    void 자기소개() {
        System.out.printf("안녕하세요 저는 %s이고, 나이 %d살 입니다.\n", this.이름, this.나이);
    }
}

