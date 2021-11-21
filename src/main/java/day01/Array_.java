package day01;

import java.util.Scanner;

public class Array_ {
    public static void main(String[] args) {
        // 변수만쓰면
        // 1. 코드가 길어진다. 변수1개당 데이터 1개밖에 못넣는다.
        // 2. 반드시 그 변수이름을 알아야한다 -> 관리가 힘들어진다.
        // * 3. 일괄처리(1번부터 10번까지)가 안되서 다 한개씩 불러야한다.
        //-> 데이터에 번호 부여하자. 데이터를 이름이 아닌 번호로 관리하자.

        // 흩어진 데이터를, 나란히 줄세워서, 번호를 부여하자.
        // 이름==변수는 안쓴다. -> 묶음{ } 을  int [] 대괄호쳐서 묶음이라고 알려준다.
        // -> 배열을 쓰는 순간 0,1,2번으로 쓴다.
//        int[] numbers = {10, 20, 30};

//        System.out.println(numbers[0]);
//        System.out.println(numbers[1]);
//        System.out.println(numbers[2]);

        // 특정원소 접근시 하나의 변수처럼 사용하면 됨.
        // 일괄처리 -> 반복문으로 부여된 숫자에 접근해서
//        for (int i = 0; i < numbers.length; i++) {
//            System.out.println(numbers[i]);
//        }

        // 배열 문제 풀이
//        //arr배열에 10~100까지 정하고 출력,
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 100};
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }

        // 2번 출력 by 2중포문
//        for (int j = 0; j < 2; j++) {
//            for (int i = 0; i < arr.length; i++) {
//                System.out.println(arr[i]);
//            }
//        }


        // 5개 입력받아 출력
        // -> 5개를 입력받으려면, 길이5짜리를 미리 만들어야한다. -> 아무값이나 들어가도 되긴 한다. 다시 넣어줄 것이기 때문에
        // 주의!!) arr2 {};로 만들면 0개짜리 배열로 길이는 생성할때 고정되어버린다.
        int[] arr2 = {1, 2, 3, 4, 5};
        //int[] arr3 = new int[5];
//        int[] arr4 = {};
        Scanner sc = new Scanner(System.in);
//        arr2[0] = Integer.parseInt(sc.nextLine());
//        arr2[1] = Integer.parseInt(sc.nextLine());
//        arr2[2] = Integer.parseInt(sc.nextLine());
//        arr2[3] = Integer.parseInt(sc.nextLine());
//        arr2[4] = Integer.parseInt(sc.nextLine());
//        for (int i = 0; i < arr2.length; i++) {
//            arr2[i] = Integer.parseInt(sc.nextLine());
//        }
//        for (int i = 0; i < arr2.length; i++) {
//            System.out.println(arr2[i]);

        // 배열의 값은 미리 넣어놓는경우보다, 추후에 정해지는 것이 훨씬 많다.
        // -> 길이는 예상 + 0으로 갯수맞추기 -> 자바문법으로 new type[length];
//        String[] arr3 = new String[10];
//        for (int i = 0; i < arr3.length; i++) {
//            System.out.println(arr3[i]);
//        }

//      //5개의 문장을 받아보고, 거꾸로 출력(index를 반대로)
        String[] arr4 = new String[5];
        Array_.Input(sc, arr4);


    }

    private static void Input(Scanner sc, String[] arr4) {
        for (int i = 0; i < arr4.length; i++) {
            arr4[i] = sc.nextLine();
        }
        for (int i = arr4.length-1; i >=0 ; i--) {
            System.out.println(arr4[i]);
        }
    }
}
