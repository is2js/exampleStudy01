package day06;

public class Car {
    String model;
    int velo;

    public Car(String model, int velo) {
        this.model = model;
        this.velo = velo;
    }

    // 필수(공통) 기능
    public void run() {
        System.out.println(velo + "km로 " + model + "이/가 달립니다.");
    }

    // 추가기능 3개 추가 -> 모든 자동차가 특정기능들을 다 사용하게 됨 -> 하위 카테고리(상속한 새class)로 분류시키자.
    //    public void autoDriving() {
    //        System.out.println("자율주행모드");
    //    }
    //
    //    public void fuelEfficiency() {
    //        System.out.println("연비모드");
    //    }
    //    public void turbo() {
    //        System.out.println("터보모드");
    //    }
}
