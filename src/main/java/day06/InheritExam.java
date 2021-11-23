package day06;

public class InheritExam {
    public static void main(String[] args) {
        //0. 객체 생성필요시 -> [Car.java]class파일 만들기 -> 데이터들 + 생성자 + 필수기능 넣기
        Car 싼타페 = new Car("싼타페", 120);
        Car 아반떼 = new Car("아반떼", 100);

        싼타페.run();
        아반떼.run();
        //1. 추가 기능(method)을 가진 자동차(객체)를 만들고 싶다
        // 1) 자율주행 [기능] 자동차
        // 2) 자주는 안되지만, 고연비 [기능] 자동차
        // 3) 터보엔진 [기능] 자동차

        //2. class에다가 추가기능을 method로 작성해본다.
        // -> 그냥 해당 class에 3개 다 작성했떠니 -> 3개다 기능이 가능해져버린다.
        // -> 한 class에.. 기능인 method를 모두 작성해버리면 안된다.
        // --> 가능한 것만 작성되도록 구체적으로 기능을 가지려면? --> 기본(공통) 기능의 class를 상속하면서 특정 기능을 가진 클래스를 새로 작성한다.
        // -> my) <구체적 기능을 가진 하위카테고리> 를 만드려면, 바로 작성하면 모두 기능 가져버림. no 하위 카테고리 -> 공통기능 카테고리 상속한 따로 특정 기능 class를 만들어준다.

        //3.[AutoDrivingCar.java] 자율주행기능을 가진 자동차(공통기능, Car) class 만들기
        //4.[SuperCar.java] 터보기능을 가진 자동차(공통기능, Car) class 만들기
        // -> 특정기능을 가진 하위카테고리 만들기 -> 상속한 class 새로 만들고 새 기능 정의해주기
        AutoDrivingCar k9 = new AutoDrivingCar("k9", 1000);

        k9.autoDriving();

        SupercCar 람보르기니 = new SupercCar("람보르기니", 2000);
        람보르기니.turbo();


    }
}
