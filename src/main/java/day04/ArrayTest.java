package day04;

import java.util.ArrayList;

public class ArrayTest {
    public static void main(String[] args) {
        // ArrayList -> 자료구조이며, 콜렉션이며, 동적배열이다.
        // 자동으로 길이가 늘어나는 배열으로서, 추가 삭제가 편해진다(index관리안해도된다.)
        // - 자료형을 배열처럼 적긴 하는데 <>꺽쇠의 제네릭부분에 원소들의 Type을 명시해준다.
        // - 이 때, int는 Integer로 건네줘야한다!!!!!!!!
        ArrayList<Integer> list1 = new ArrayList<>(); // 오른쪽은 type만 생략가능하다. <>꺽쇠는 생략 안됨.
        ArrayList<String> list2 = new ArrayList<>(); 
        
        // 5가지 핵심 기능
        // size()
//        System.out.println(list1.size()); // 배열은 length가 정해진 길이밖에 안나오는데, arrayList는 0부터 시작해준다.
//        System.out.println(list2.size());

        // add()
        list1.add(100);
        list1.add(200);
        list1.add(300); // 하나씩 저장
        
        list2.add("사과");
        list2.add("감");
        list2.add("배");
        // cf) 저장후에는 데이터값을보기보단, size()를 수시로 찍어본다.
        System.out.println("===================");
        System.out.println(list1.size());
        System.out.println(list2.size());

        //.set(index, value);
        // - 배열이 아니므로 list1[1] = 2; 안됨!! 인덱싱 하면 안된다.
        System.out.println("===================");
        list1.set(1, null);
        list2.set(1, null);

        // .get(index)
        System.out.println("===================");
        System.out.println(list1.get(1));

        // .remove(index);
        System.out.println(list2.get(1));
        System.out.println("===================");
        list1.remove(1);
        list2.remove(1);

        System.out.println(list1.get(1));
        System.out.println(list2.get(1));

        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println("===================");

    }
}
