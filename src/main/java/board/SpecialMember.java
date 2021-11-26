package board;

public class SpecialMember extends Member {
    // 4. 우수회원은 기능추가가 아닌 변수만 일단추가되었다.
    // **my) 기능추가가 아닌 `변수추가`시에도 하카Class를 생성하며, `무변화Class도 하카Class로 추가 생성`해줘야 instanceof로 분기해서 관리할 수 있다.**
    int point;

    //5. 생성자도 상속4개 -> 추가한 5개 변수를 한꺼번에 만들어주자.
    // -> intellij에서 알아서 만들어줌.
    public SpecialMember(int id, String loginId, String loginPw, String nickname, int point) {
        super(id, loginId, loginPw, nickname);
        this.point = point;
    }
}
