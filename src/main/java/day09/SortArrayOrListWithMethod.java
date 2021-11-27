package day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortArrayOrListWithMethod {
    public static void main(String[] args) {
		// 1. java제공 정렬메서드 in array
		// -> 배열의 문제에 있어서만, Arrays.sort()를 쓴다.
		Integer[] arr = {2, 4, 1, 3, 7, 6, 8, 5};

		//3. Arrays.sort()는 arr[]뿐만 아니라, ArrayList도 적용이 된다.
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(2);
		numbers.add(4);
		numbers.add(1);
		numbers.add(3);
		numbers.add(7);
		numbers.add(6);
		numbers.add(8);
		numbers.add(5);


		System.out.println("정렬전>>");
		for (int i : arr) {
			System.out.print(i + " ");
		}

		Arrays.sort(arr); // 1) 오름차순

		System.out.println("정렬후>>");
		for (int i : arr) {
			System.out.print(i + " ");
		}

		// 2. 내림차순은 Collections.reverseOrder()라는 스태틱메소드?의 값을 2번째 인자로
		Arrays.sort(arr, Collections.reverseOrder());
		System.out.println("내림차순정렬후>>");
		for (int i : arr) {
			System.out.print(i + " ");
		}

        // 4. arraylist은 Collection프레임웤이라 arrays가 아닌 Coolections에 있다.
		Collections.sort(numbers);
		System.out.println("arraylist 정렬후>>");
		for (int i : numbers) {
			System.out.print(i + " ");
		}


		Collections.sort(numbers, Collections.reverseOrder());
		System.out.println("arraylist 내림차순정렬후>>");
		for (int i : numbers) {
			System.out.print(i + " ");
		}

		//5. 문자열도 사전순으로 된다.
		ArrayList<String> strings = new ArrayList<>();
		strings.add("bbb");
		strings.add("aaa");
		strings.add("ddd");
		strings.add("bbb");

		Collections.sort(strings);

        System.out.println("문자열 정렬");
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();

		Collections.sort(strings, Collections.reverseOrder());
        System.out.println("문자열 내림차순 정렬");
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
	}
}
