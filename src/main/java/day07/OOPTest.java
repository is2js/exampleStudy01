package day07;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OOPTest {
    public static void main(String[] args) {
        //4. 개념(class)를 만들고, 기능(method)를 만들었다면, 실행해볼 수 있다.
        // - cf) new 클래스명까지 입력후   ctrl+alt+v로 만들어보자~~
        강아지 강아지1 = new 강아지();
        강아지1.짖다();
        고양이 고양이1 = new 고양이();
        고양이1.울다();
        // 5. 이제 개별기능말고, 공통기능을 추가해보자. -> 숨쉬다() 메소드를 추가해보자.
        //8. 공통기능을 수행해보자.
        강아지1.숨쉬다();
        고양이1.숨쉬다();
        //9. 2개의 클래스를 사용하다보면, 똑같은 기능을 할 수밖에 없다.
        // -> 그 이유는 상위카테고리 "동물"이라는 것이 존재하기 때문이다.
        // -> **처음에는 따로 만들어서 관리하다보니까, 공통기능이 보여서, 상위카테리로 묶이는지 보고 -> class를 만들고, 내부에 공통기능을 만들어묶는다.**

        //11. 상위카테고리로 공통기능을 구현한다.
        // -> 공통기능(숨쉬다)만 할 거면, 굳이... 하위카테고리로 공통기능을 할 필요는 없다?
        동물 동물1 = new 동물();
        동물1.숨쉬다();
        동물 동물2 = new 동물();
        동물2.숨쉬다();

        //12. 동물이 할 수 있는 것(동물의 메소드)를 다 할 수 있으면(메소드 가지고 있으면),
        // 강아지도, 고양이도 동물(상위카테고리)로 볼 수 있다.

        // 13. 이제 강아지, 고양이를 동물로만 보고 숨만 쉬게 하고 싶다면?
        // -> 공통기능만 할거면, 상위카테고리를 이용한 다형성으로 일괄처리가 가능해진다.
        // -> 14. 닭class도 추가해보자.
        //16.
        닭 닭1 = new 닭();
        닭1.숨쉬다();
        //17. 공통기능만 할거면! 똑같은 행위만 할 거면! -> 개별접근말고 일괄처리 할 수 있다. by 상위카테고리를 이용한 다형성 + 반복문으로 일괄처리
        // 1) 다형성안쓰고 공통기능 ->  강아지1.숨쉬다() , 고양이1.숨쉬다(), ... 하마1.숨쉬다()
        강아지1.숨쉬다();
        고양이1.숨쉬다();
        닭1.숨쉬다();
        // 2) 다형성을써서 공통기능을 일괄로 처리하기 -> 일괄처리는 반복문을 통해서 한다.
        // -> [상속으로 만든 상위카테고리]를 이용한 다형성 + 반복문으로 일괄처리
        // ->  "동물"들아. 숨쉬어!() 로 하고 싶다!

        System.out.println("==================");
        //18. 그럴려면, **java에게 각 하위카테고리마다 -> 상위카테고리를 알려줘야한다. by extends **
        // -> 1) 상속으로 상위카테고리-하위카테고리 개념을 java에게 알려줘야한다. -> 처리하고 오기19.
        //20. 2) 상위카테고리라면, 다형성 개념을 이용해서, 하위카테고리 객체를 상카에 담을 수 있다.
        동물 동물11 = new 강아지();
        동물 동물22 = new 고양이();
        동물 동물33 = new 닭();
        //21. 3) 상카 참조변수 1가지 type에 담았다면 ->  같은type의 배열 -> 반복문을 통해 일괄처리가 가능하다.

        //22. **오버라이딩을 통해, 상속받은 공통기능을 다르게 구현할 수 있다!**
        동물[] 동물들 = {동물11, 동물22, 동물33};
        for (동물 동물 : 동물들) {
            동물.숨쉬다();
        }
    }
}

//10. **공통기능 -> 상위카테고리로 class를 만들고, 공통기능을 넣어서 묶는다.**
class 동물 {
    void 숨쉬다() {
        System.out.println("숨쉬다");
    }
}


// 1. 고양이 강아지 개념을 빈 class로 만든다.
// -> 고양이 : 야옹 할 수 있따. / 강아지: 멍멍 할 수 있다.
// class 강아지 {
// 19.각 하위카테고리들이 상위카테고리를 extends상속하게 함으로써, 자바에게, 상위카테고리가 있음을 알려준다.
class 강아지 extends 동물 {
    // 2. 강아지만의 기능 짖다() -> "멍멍" 을 만들어주자.
    void 짖다() {
        System.out.println("멍멍~");
    }
    // 6. 공통기능 추가
    // 23. 공통기능을 상속받게 되었지만, 다르게 구현할 수 있다. - 메서드이름만 같으면 공통기능-일괄처리는 유지된다.
    @Override
    void 숨쉬다() {
        System.out.println("숨쉬다~헥헥헥");
    }
}

// class 고양이 {
//19.
class 고양이 extends 동물 {
    //3. 고양이는 기능이 짖기보다는 울다 -> "야옹"으로
    void 울다() {
        System.out.println("야옹~");
    }
    //7. 공통기능 추가
    //24.
    @Override
    void 숨쉬다() {
        System.out.println("숨쉬다~ㄴ갸르르릉");
    }
}

//15. 다형성의 장점 강조를 위해 닭 클래스 생성
// -> 공통기능 + 개별기능 가지도록 할 작성할 것.
// class 닭 {
//19.
class 닭 extends 동물 {
    //25.

    @Override
    void 숨쉬다() {
        System.out.println("숨쉬다~ 짹짹짹");
    }

    void 울다() {
        System.out.println("꼬끼오~");
    }

}