package day03;

import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE

// 요구사항: 데이터가 한개밖에 저장되지 않습니다. 데이터가 3개까지 저장 가능하게 해주세요.
// - 노강의 참고: https://replit.com/@chasaem/UnimportantHoneydewSymbols#Main.java
// 입출력 예시
// 명령어를 입력해주세요 : add (입력)
// 저장할 값을 입력해주세요 : aaa
// aaa가 저장되었습니다.
// 명령어를 입력해주세요 : read (입력)
// [ aaa ]
// 명령어를 입력해주세요 : add (입력)
// 저장할 값을 입력해주세요 : bbb
// bbb가 저장되었습니다.
// 명령어를 입력해주세요 : read (입력)
// [ aaa bbb ]
// 명령어를 입력해주세요 : add (입력)
// 저장할 값을 입력해주세요 : ccc
// ccc가 저장되었습니다.
// 명령어를 입력해주세요 : read (입력)
// [ aaa bbb ccc ]
// 명령어를 입력해주세요 : add (입력)
// 더 이상 저장할 수 없습니다.
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String data = null;
        // 1. 데이터 1-> 여러개 받도록  변수-> 배열로 수정해보자.
        // - 배열은 미리 갯수가 정해져있어야해서 문제에서 제시해줌.
        // - 배열초기화는 int는 0, String은 null로 차있었던 것 같다.
        // -> 주의) new 배열[] 초기화시 length때리면 N개로 뜬다! null이 3개 차있다..로..
        final int DATA_SIZE = 3;
        String[] data = new String[DATA_SIZE];
        //3. add/delete시 관리가 안되는 배열의 index를 위해 0부터 index관리함.
        int dataIndex = 0;
        
        while (true) {
            System.out.print("명령을 입력해주세요: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            if (command.equals("help")) {
                System.out.println("help: 도움말");
                System.out.println("add: 데이터 추가");
                System.out.println("read: 데이터 조회");
                System.out.println("update: 데이터 수정");
                System.out.println("delete: 데이터삭제");
                System.out.println("exit: 프로그램종료");
                continue; // if 처리 끝나고 맨 뒤의 continue..를 보면,, 그 뒤에는 예외처리. if들에 안걸리는 더러운ㄱ ㅓㅅ들이 모여있다고 생각하자.
            }
            if (command.equals("add")) {
                System.out.print("저장할 값을 입력해주세요 : ");
//                data = scanner.nextLine();
                // 2. 배열부터는 특정Index에 데이터를 넣어야하는데,
                // -> 배열은 add/delete시 index관리가 안되므로... 직접 변수로 다시 index를 관리해줘야한다.
                // -> 반복문 바깥쪽에서 int dataInndex = 0;를 선언하고 오자.
                data[dataIndex] = scanner.nextLine();
                System.out.println( dataIndex + "번째 데이터 " + data[dataIndex] + "이/가 저장되었습니다.");
                dataIndex++;
                continue;
            }
            if (command.equals("read")) {
                //3. 배열은 for문 + print( + " ")으로 출력할 수 있다.
                // -> **배열부터는 for문돌려서 null은 안돌아가므로 따로 빈배열 처리를 안해줘도 된다.**
                // -> 한다면 .length로 해주면 될듯하다.
                // 잘못주의!! new 배열초기화는 length때려도 n개로 출력됨. null이  n개 차있다는 말.
                if (data[0] == null) {
                    System.out.println("저장된 데이터가 없습니다.");
                    continue;
                }
//                System.out.println("현재 저장되어 있는 값: " + data);
                //4. 배열을 향상된 for문으로 출력해보자. add나 delete가 아닌 상황에서는 Index를 관리할 필요 없다.
                System.out.print("[ ");
                for (String element : data) {
                    System.out.print(element + " ");
                }
                System.out.println(" ]");
                continue;
            }
//            if (command.equals("update")) {
//                System.out.print("어떤 값으로 수정하시겠습니까? : ");
//                data = scanner.nextLine();
//                System.out.println(data + "로 값이 수정되었습니다.");
//                continue;
//            }
//            if (command.equals("delete")) {
//                data = null;
//                System.out.println("삭제가 완료되었습니다.");
//                continue;
//            }
            System.out.println("잘못된 명령어 입니다.");

        }
    }
}
