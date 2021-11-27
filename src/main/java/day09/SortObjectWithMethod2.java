package day09;

import java.util.ArrayList;
import java.util.Arrays;

public class SortObjectWithMethod2 {
    public static void main(String[] args) {
        // 3. 자료구조는 ArrayList
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 4, 1, 3, 7));
        numbers.add(6);
        numbers.add(8);
        numbers.add(5); // 2 4 1 3 7 6 8 5

        // 5. 우리가 만든 유틸메소드 Sortor.sort()를 이용해서 정렬해보자.
        // Sortor.sort(numbers);
        //
        // for (Integer number : numbers) {
        //     System.out.print(number + " ");
        // }
        // System.out.println();

        // 6. 객체는 그래도 아직 안될것이다. -> java가 미리 사람type의 객체를 알순없으니 이름만 정해둔, 미완성구현이라고 생각하자.
        // -> 자바 왈 "내가 바뀔수있는 코드를 비워두긴 했지만, 인터페이스로 메서드명은 통일해놨어, 아무렇게 말고 제시한 메서드명을 implements한 뒤 만들어서
        // 넣어줘"
        // -> 자바 왈 "sort()에 필요한 Comparator(컴패래이터) 인터페이스안에다가 메서드명을 강제해놨어. -> implements한 뒤 메서드명으로 써서
        // 코드를 넣어줘"



        //18. 이제 2번째 인자에 Interface를 가지는 Sortor.sort(,)를 이용해서 numbers정렬해보자.
        // 1) 2번째 인자를 안넣으면 에러남.
        // Sortor.sort(numbers, ); -> interface구현체class (하카class) 객체가 필요
        // -> 구현체는 내부에서 이미 강제해야하는 메소드 + 그 구현부를 제공사답게 구현해놓은 상태다.
         Sortor.sort(numbers, new ComparatorImpl());
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }
}

//19. ==================== 아래 sort메소드구현까지는 java가 정의해놓은 부분이라서 사실상 못본다===============================
// -> 우리가 관려할 부분은 Interface구현체class를 만들고, 정해진이름을 가진 메소드 부분에 원하는 코드 작성해서 넣어준다.

// 1. Collections를 모방해서 .sort()등을 만들어보자.
class Sortor {
    // 2. 바로바로 main에서 쓰려면 static(공유목적, 유틸메소드)을 붙혀주자.
    // ->  파라미터로 정렬대상을 arraylist<Integer> 를 받을 예정

    // -> 8. 7-2) java는 sort()의 비교 기준코드(if (numbers.get(i) > numbers.get(i + 1)) )를 일단 비워두었다.
    // ->    비교기준은 sort(,)메서드의 2번째 인자로 코드가, [컴패래이터-인터페이스로 구현예정인 새class의 객체]를 준다.
    // ->    java입장에서는 만약, sort()에 기준이 없는 객체가 1번째 인자로 들어오면, 2번째 인자로 정해진 Comparator구현객체 -> i통일메서드
    // compare()를 이용해서 구현된 대로 기준을 삶을 것이다.
    // static void sort(ArrayList<Integer> numbers) {
    static void sort(ArrayList<Integer> numbers, MyComparator myComparator) {
        // 4. 이전에 작성한 거품정렬코드를 가져와서 수정한다.
        for (int j = 1; j <= numbers.size() - 1; j++) {
            for (int i = 0; i < numbers.size() - 1 - (j - 1); i++) {
                // 9. 7-3) 비교의 기준을 I구현Clas객체.통일메서드compare()를 호출해서 만들어준다.
                // if (myComparator.compare( 들어온객체1i, 들어온객체2i+1 ) { 의 느낌으로 들어갈 것이다.
                // if (numbers.get(i) > numbers.get(i + 1)) {
                if (myComparator.compare(numbers.get(i), numbers.get(i + 1))) {
                    int temp = numbers.get(i);
                    numbers.set(i, numbers.get(i + 1)); // 배열의 대입대신, array의 set(index, value)를 이용한다.
                    numbers.set(i + 1, temp);
                }
            }
        }
    }
}
// ==================== 위 sort메소드구현까지는 java가 정의해놓은 부분이라서 사실상 못본다===============================


// 7. sort()의 내부에는 컴패레이터로 메서드명을 강제통일한 인터페이스가 있다. 인터페이스를 가상으로 만들어볼 것이다.
// -> 7-1) 구현후 강제로 써야할 메서드 이름을 정의해줘야하는데, [일단 new 클래스]부터 만든다.
// class MyComparator {
// 11. 추상메서드가 온 순간 -> class를 interface로 수정해주자.
interface MyComparator {
    // 10. sort()내부에 쓸, 정렬기준메소드를 compare로 강제했으니, 강제한대로 생성해주자.
    // -> Interface를 만들에정이다. -> 추상메서드로 메서드명만 선언해준다.
    // -> 이 때, if ()안에 들어가 t/f를 반환해야하는 메서드이므로 반환타입을 void가 아닌  boolean으로 수정해서 선언해주자.
    // abstract boolean compare();
    // 12. 현재  numbers(integer arraylist)를 정렬기준을 제시한다고 가정하면,
    // -> int 2개를 인자로 받아야한다. -> 거품정렬if문의 빨간줄이 사라짐. -> interface의 메소드명만 정해줫을 뿐인데 사용할 수있게됨.
    abstract boolean compare(int int1, int int2);
}

// 13. sort의 2번째 인자 MyComparator myComparator 자리에는, 메서드명통일을 약속지키는 메서드제공자들을 다형성으로 담을 수 있다.
// -> 상속처럼, 인터페이스 구현도, 다형성으로서 다 담을 수 잇음. -> 일괄 파라미터 입력됨.

// 14. 이제 Interface를 구현해서, 동일한 메서드명을 강제사용(미완성구현or변경구현)하는 classs를 만들어서
// -> InterfaceType 변수 에 해당 메서드용 객체를 제공해서 사용하도록 해보자.
// -> **[InterfaceType 변수]자리는 그것을 구현하여 메소드이름규정을 지켜 적용시켜주는, 여러종류의 하카Class 객체들이 온다.**
// --> interface구현 같은이름으로 기능 메소드를 제공할 하카Class(혹은 Impl 구현체)만들기~
class ComparatorImpl implements MyComparator {
    // 15. I구현했으면, 강제로 메서드 일단 구현해야함 -> ide자동생성으로 하자.
    @Override
    public boolean compare(int int1, int int2) {
        // 16. 현재상황(거품정렬)에서는 뭐가더 크냐?를 따져야하는 부분이다.
        // -> 밑에는 swap코드가 있으니, 만족시키는 상황에서 true를 리턴시키면 된다.
        // -> 즉, return true가 되는 조건은  int1 > int2 앞에께 더 큰 순간이다.
        // (추가-객체.숫자변수 비교시) if ( 객체1.age > 객체2.age) : 숫자
        // (추가-객체.문자열변수 비교시) if ( 객체1.compareTo(객체2) > 0 = 0 < 0 ) : 숫자

        if (int1 > int2) {
            return true;
        }
        return false;
    }
}

// 17. 자바가 만들어놓은 코드안에, 코드를 넣고 싶다면?, 자바가 정의해놓은 interface 형식에 맞게 구현하면 되는데
// -> 메서드명 맞춰서 작성되도록 구현체(Impl class)를 만든 뒤 -> 그 [interfaceType 변수]자리에 구현체 객체를 넣어주면 완성이다.
