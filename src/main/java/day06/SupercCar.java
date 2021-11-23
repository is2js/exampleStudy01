package day06;

public class SupercCar extends Car {

    // **객체마다 불끔/켬(On/Off)을 관리하는 결과값을 관리**려면
    // 1) 인스턴스변수 boolean is기능 = false; 변수을 정의 및 초기화해준다.**
    // 2) 메서드로 + 삼항연산자? + if  this.boolean변수로 로 스위칭 해준다
    // or On메서드, Off메서드를 정의해준다.
    // 3) OnOff에 따라 특정메소드를 if문으로 달라지게 기능하게 해준다.

    boolean isTurbo = false;

    public SupercCar(String model, int velo) {
        super(model, velo);
    }

    public void turboOnOff() {
        this.isTurbo = this.isTurbo ? false : true;
    }

    public void turboOn() {
        this.isTurbo = true;
    }
    public void turboOff() {
        this.isTurbo = false;
    }

    public void turbo() {
        System.out.println("터보모드");
    }

    @Override
    public void run() {
        if (isTurbo) {
            System.out.println("부우우웅~! 슈퍼카가 터보모드로 달립니다.");
            return;
        }
        //System.out.println("슈퍼카가 일반자동차처럼 달립니다.");
        super.run(); // 재정의, 수정시 분기별로 부모꺼 그대로 사용해야한다면 super.메소드()를 활용한다.
    }
}
