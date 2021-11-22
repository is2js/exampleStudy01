package day04;

import java.util.ArrayList;
import java.util.Scanner;

// 데이터 프로그램만,, 9월 주말로 이동: https://codepen.io/chataejin/pen/bGRWqLw
// 그전까진 10월 평일: https://codepen.io/chataejin/live/ZEJYMVE
// 다시 데이터프로그램 ArrayList부터는 10월 평일로 이동

public class Application_arraylist {
    // 3. 메소드의 공용 변수라고 판단된 순간 -> static으로 빼자. -> 메소드들 파라미터에서 빼고 그냥 공용변수로서 클래스내에서 막쓴다.
    // my) class내 모든 메서드 막사용 -> 파라미터로 받지말고 static으로 빼서 막사용하자.
    // my) 모든 객체가 공용관리 결과값 -> static으로 빼자.
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) {
        //2. 각 분기별 공통 -> 메소드로 뺐을 때 파라미터 공통 -> 메소드들 공통이면, 객체단위가 아닌, 클래스단위 static변수로 뺀다.
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> data = new ArrayList<>();

        while (true) {
            System.out.print("명령을 입력해주세요: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            if (command.equals("help")) {
                //1. 잘라내기 -> printHelp() 메소드로 빼기
                printHelp();
                continue;
            }
            if (command.equals("add")) {
                addData();
                continue;
            }
            if (command.equals("read")) {
                if (data.size() == 0) {
                    System.out.println("저장된 데이터가 없습니다.");
                    continue;
                }
                readData();
                continue;
            }

            if (command.equals("update")) {
                updateData();
                continue;
            }
            if (command.equals("delete")) {
                deleteData();
                continue;
            }

            System.out.println("잘못된 명령어 입니다.");
        }
    }

    private static void readData() {
        System.out.print("[ ");
        for (String element : data) {
            System.out.print(element + " ");
        }
        System.out.println(" ]");
    }

    private static void addData() {
        System.out.print("저장할 값을 입력해주세요 : ");
        data.add(scanner.nextLine());
        int curr_index = data.size() - 1;
        System.out.println(curr_index + "번째 데이터 " + data.get(curr_index) + "이/가 저장되었습니다.");
    }

    private static void updateData() {
        System.out.print("몇번째 데이터(index)를 수정하시겠습니까? : ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("어떤 값으로 수정하시겠습니까? : ");
        String newData = scanner.nextLine();
        data.set(index, newData);
        System.out.println(data.get(index) + "로 값이 수정되었습니다.");
    }

    private static void deleteData() {
        System.out.print("몇번째 데이터(index)를 삭제하시겠습니까? : ");
        int index = Integer.parseInt(scanner.nextLine());
        data.remove(index);
        System.out.println("삭제가 완료되었습니다.");
    }

    private static void printHelp() {
        System.out.println("help: 도움말");
        System.out.println("add: 데이터 추가");
        System.out.println("read: 데이터 조회");
        System.out.println("update: 데이터 수정");
        System.out.println("delete: 데이터삭제");
        System.out.println("exit: 프로그램종료");
    }
}
