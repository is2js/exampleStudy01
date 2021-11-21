package day02;

import java.util.ArrayList;
import java.util.List;

public class 배열문제 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        showAllNumberInArray(arr); // 배열 전부 출력

//        List<Integer> evenNumbers = getEvenNumbers(arr);// 배열 넘기면 짝수만 뽑아서 반환.
        int[] evenNumbers = getEvenNumbers(arr);// 배열 넘기면 짝수만 뽑아서 반환.
        for (int even :  evenNumbers) {
            System.out.print(even + " ");
        }
        System.out.println();


    }

//    private static List<Integer> getEvenNumbers(int[] arr) {
    private static int[] getEvenNumbers(int[] arr) {
        // 1. List안쓸꺼면 원하는 원소의 갯수를 먼저 따로 세서, 담을 배열의 갯수를 미리 알고 있어야함 ㅠ
        //List<Integer> evenNumbers = new ArrayList<>();
        int count = 0;
        for (int ele : arr) {
            if (ele % 2  == 0) {
                count += 1;
            }
        }

        int[] evenNumbers = new int[count];

        int evenNumberIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!(arr[i] % 2 == 0)) {
                continue;
            }
            //evenNumbers.add(arr[i]);
            // 2. 배열을 도는 것도 아닌데,, 어떻게 순서대로 채우지.. 건너띈거를?
            // i는 arr 탐색용임..
            // -> 따로 챙겨서 0부터 넣으면서.. 하나씩 증가시켜야함.. 찾을때마다. -> 3. evenNumberIndex
            evenNumbers[evenNumberIndex] = arr[i];
            evenNumberIndex++;
        }
        return evenNumbers;
    }

    private static void showAllNumberInArray(int[] arr) {
        for (int i = 0 ; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
