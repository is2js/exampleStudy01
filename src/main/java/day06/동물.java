package day06;

public class 동물 {
    void 숨쉬다() {
        System.out.println("숨을 쉽니다.");
    }


}

class 강아지 extends 동물 {
    @Override
    void 숨쉬다() {
        System.out.println("멍멍 하면서 숨쉬다.");
    }

    public void 멍멍() {
        System.out.println("멍멍");
    }
}

class 고양이 extends 동물 {
    @Override
    void 숨쉬다() {
        System.out.println("야옹 하면서 숨쉬다.");
    }

    public void 야옹() {
        System.out.println("야옹");
    }
}
