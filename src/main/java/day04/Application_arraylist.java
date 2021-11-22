package day04;

import java.util.ArrayList;
import java.util.Scanner;

//데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE

public class Application_arraylist {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1. 배열길이를 미리 정할필요없음. + add/delete시 현재 index를 관리할 필요x
//        final int DATA_SIZE = 3;
//        String[] data = new String[DATA_SIZE];
//        int dataIndex = 0;
        ArrayList<String> data= new ArrayList<>();

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
                continue;
            }
            if (command.equals("add")) {
                System.out.print("저장할 값을 입력해주세요 : ");
                //2. 해당index 할당 대신 .add를 쓴다.
//                data[dataIndex] = scanner.nextLine();
                data.add(scanner.nextLine());
                // 3. 조회는 인덱싱 data[i] 대신 -> get(i)을 쓴다....
                // -> index를 관리할 필요가 없다고 했는데...
                // -> .size()-1 의 마지막 인덱스를 이용?
                // -> index를 관리안하니.. nextLine()에 받은 값만 따로 저장해놨다가 출력해준다. (뽑아쓰진..?)
//                System.out.println( dataIndex + "번째 데이터 " + data[dataIndex] + "이/가 저장되었습니다.");
                int curr_index = data.size()-1;
                System.out.println( curr_index + "번째 데이터 " + data.get(curr_index) + "이/가 저장되었습니다.");
//                dataIndex++;
                continue;
            }
            if (command.equals("read")) {
                if (data.size() == 0) {
                    System.out.println("저장된 데이터가 없습니다.");
                    continue;
                }
                System.out.print("[ ");
                for (String element : data) {
                    System.out.print(element + " ");
                }
                System.out.println(" ]");
                continue;
            }

            if (command.equals("update")) {
                System.out.print("몇번째 데이터(index)를 수정하시겠습니까? : ");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.print("어떤 값으로 수정하시겠습니까? : ");
//                data = scanner.nextLine();
                String newData = scanner.nextLine();
                data.set(index, newData);
                System.out.println(data.get(index) + "로 값이 수정되었습니다.");
                continue;
            }
            if (command.equals("delete")) {
                //4. delete -> 원래는 target index ~ n-1까지.. 뒤에것 -> 앞으로 1칸씩 땡겨 덮어쓰기로 삭제한다?
//                data = null;
                // null로 덮어써서 삭제했던 것을... remove로
                System.out.print("몇번째 데이터(index)를 삭제하시겠습니까? : ");
                int index = Integer.parseInt(scanner.nextLine());
                data.remove(index);
                System.out.println("삭제가 완료되었습니다.");
                continue;
            }

            System.out.println("잘못된 명령어 입니다.");

        }
    }
}
