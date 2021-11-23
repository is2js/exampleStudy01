package day06;

public class AutoDrivingCar extends Car{
    // 상속했다면, 따로 선언하지 않아도 데이터(변수) + 기능(메소드)들을 다 사용할 수 잇게 된다.

    // 부모가 기본생성자 이외 생성자가 있으면, 자식은 반드시 부모 생성자를 초기화가능하게 해줘야한다.
    public AutoDrivingCar(String model, int velo) {
        super(model, velo);
    }
        public void autoDriving() {
        System.out.println("자율주행모드");
    }
}
