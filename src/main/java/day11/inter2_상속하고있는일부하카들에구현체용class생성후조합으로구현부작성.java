package day11;

public class inter2_상속하고있는일부하카들에구현체용class생성후조합으로구현부작성 {
    // 0. 설명
    // 이미 Chracter --상속--> 하카들(짱구, 도라에몽, 해리포터) 중 도라에몽+해리포터에만 기능을 추가하고싶다
    // -> 상위class에 넣으면 전부다 상속되서 짱구도 기능 가지게 됨
    // -> 하위class들이 추가상속은 불가능함.
    // -> 1) 하위class들이 Interface를 구현해서 강제통일메서드를 @Override하되(구현한 하카class들만 기능추가됨)
    // -> 2) 실제구현은 Interface 추가구현 구현체Class생성 + 기능정의 -> 하카Class들내부에서 객체생성(조합)후 추가구현체.메서드()로 구현시킴
    // --> 구현부는 각각되는게 아니라 추가구현체Class에서 정의한 것을 그대로 사용하게 된다.

    public static void main(String[] args) {
        // 10. 추가기능을 [조합]으로서 구현용Class객체내부생성후 구현부에 사용된 것을 테스트해보자.
        Doraemong 도라에몽 = new Doraemong("이름", 30);
        Zzanggu 짱구 = new Zzanggu("이름", 30);
        도라에몽.teleport();
        도라에몽.fly();


    }
}
// 1. 캐릭터 -> 하카들(짱구, 도라에몽, 해리포터) 존재
class Character {
    String name;
    int age;

    public Character(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Zzanggu extends Character {
    public Zzanggu(String name, int age) {
        super(name, age);
    }
}

// class Doraemong extends Character {
// 	public Doraemong(String name, int age) {
// 		super(name, age);
// 	}
// }

// 2. 도라에몽이 추가 기능을 더하려고 상속하고 싶으나, 추가상속안되니 -> 인터페이스 구현
// -> interface 정의 -> 강제통일 메서드 구현 -> 구현을 직접안하고, 추가 구현체에게 맡긴다.

class Doraemong extends Character implements Magical{ // 3. 자동생성으로 인터페이스 생성후, 추가기능 정의

    //7. 이미 상속중인 놈들은, 구현체용 Class객체를 생성하여 사용하는 [조합]으로 추가기능을 상속한다.
    MagicalImpl magicCompose  = new MagicalImpl();


    public Doraemong(String name, int age) {
        super(name, age);
    }
    //4. 자동으로 강제통일메서드 오버라이딩 -> BUt 구현은 직접안한다.
    @Override
    public void teleport() {
        //8.  [조합]으로 쓰일 구현용 구현체Class 객체로, 받아먹어서 구현
        magicCompose.teleport();
    }

    @Override
    public void fly() {
        //9.  [조합]으로 쓰일 구현용 구현체Class 객체로, 받아먹어서 구현
        magicCompose.teleport();
    }
}
//5. 상속중인 놈들이 상속처럼 받아먹기 위해, [조합]으로 쓰일 구현용 구현체Class를 추가로 생성한다.
class MagicalImpl implements Magical {
    @Override
    public void teleport() {
        System.out.println("[조합]으로 쓰일 구현용 구현체Class에서 텔레포트 구현");
    }

    @Override
    public void fly() {
        System.out.println("[조합]으로 쓰일 구현용 구현체Class에서 나는기능 구현");
    }
}

