package day09;

public class BubbleSort {
    public static void main(String[] args) {
        // 1. 거품정렬
        int[] arr = {2, 4, 1, 3, 7, 6, 8, 5};

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 2. 개념: i, i+1을 비교해서 큰것을 i+1자리에 보낸다.(swap)
        // -> 한 루프돌면, n자리에 젤 큰수가 들어간다. -> 다음루프는 i+1이 n-1까지만(i는 n-2까지)검사하고 swap한다.
        // -> 이짓을 i=0 ~ i=n-1까지,  i=0 ~ n-2, ... i=0 ~1까지
        // -> 즉, i의 시작번호는0 고정, 끝번호 1부터 n-1까지를 돌린다.
        // 3. i+1이 등장하는 순간 i의 끝번호는n은 n-1 -> n-2가 되도록 수정
        // for (int i = 0; i < arr.length; i++) {
        // for (int i = 0; i < arr.length - 1; i++) {
        //     if (arr[i] > arr[i + 1]) {
        //         // 4. 교체알고리즘 / / \
        //         int temp = arr[i];
        //         arr[i] = arr[i + 1];
        //         arr[i + 1] = temp;
        //     }
        // }
        // 4. 한루프 돌려주고 결과를 찍어보자.
        // for (int i : arr) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        // 2 4 1 3 7 6 8 5
        // 2 1 3 4 6 7 5 8
        // -> 5. 거품정렬 1루프는, 검사하는 동안 제일 큰놈을 오른쪽으로 보내다가
        // --> 중간에 더 큰놈이 나타나면 그놈을 보내므로, [[1루프당 제일큰놈1개씩만 맨끝으로 처리]]돈다.
        // --> 그렇다면, 첫번째 자리(0) 제외 n-1번-> n-2번-> ... -> 1번째 자리를 채우려면,  n-1-1+1 -> [n-1번을 반복]해야한다.
        // 6. 일단 한번 더 돌려보자. n-1다음의 자리인 n-2번째에 제일 큰수가 위치하게 될 것이다.
        // for (int i = 0; i < arr.length - 1; i++) {
        //     if (arr[i] > arr[i + 1]) {
        //         int temp = arr[i];
        //         arr[i] = arr[i + 1];
        //         arr[i + 1] = temp;
        //     }
        // }
        // for (int i : arr) {
        //     System.out.print(i + " ");
        // 2 4 1 3 7 6 8 5 : 원본
        // 2 1 3 4 6 7 5 8 : n-1채움
        // 1 2 3 4 6 5 7 8 : n-1, n-2까지 정렬되어 채움

        // 7. 거품정렬은 n개라면, n-1번을 반복해야지, 마지막 0번째 제외 다 정렬되서 , 최종정렬된다.
        // -> n-1번을 반복문으로 돌리자.
        for (int j = 1; j <= arr.length-1; j++) {
            //8. n-1번을 매번 끝까지 돌리지말고, i끝번호를 1개씩 줄어들도록 수정해보자.
            // -> j=1시작이라서.. 첨 -0되어야하니까... 등차수열 잘해야할 듯..-(j-1)
            // for (int i = 0; i < arr.length - 1; i++) {
            for (int i = 0; i < arr.length - 1 -(j-1); i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 9. 거품정렬로 내림차순을 만드려면?
        // -> 비교는 i, i+1로 계속하되, 작은 것을 i+1로 보내면 -> 맨끝에 작은 값들이 1개씩 적립될 것이다.
        for (int j = 1; j <= arr.length-1; j++) {
            for (int i = 0; i < arr.length - 1 -(j-1); i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
