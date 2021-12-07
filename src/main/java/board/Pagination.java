package board;

public class Pagination {
    int pageCountPerBlock = 5;
    int itemCountPerPage = 3;
    int currentPageNo = 1;

    //4. 변수는 정보처리(계산식)를 못한다. 생성될때 딱 한번한다.
    // -> 정보처리, 계산 등은 **메서드가 한다.**
    // -> 아래 5개의 연산처리, 계산은 메서드에서 그때그때마다 해줘야한다.
    // -> 메서드로 바꿔줘야한다.
    // int currentBlockNo = (int) Math.ceil((double) currentPageNo / pageCountPerBlock);
    // 1) [while문에서 매번 호출되어 업데이트 되던 변수]는 사라진다. 반복문 밖에서는 의미가 없다. -> int currentBlockNo =
    // 2) [while문에서 매번 호출되어 계산 -> 할당하는 식]은 메서드에서 return하게 한다. -> (int) Math.ceil((double) currentPageNo / pageCountPerBlock);
    // -> 기존 get변수명()으로 작성해주자. ->  변수명은 복사 -> = 이후부터 메서드 추출 기능 이용하면 편함. but private -> public으로 수정해줘야함. 인스턴스 메서드는 밖에서 많이 씀.
    // getCurrentBlockNo();


    // int startPageNoInBlock = getStartPageNoInBlock();
    // int endPageNoInBlock = getEndPageNoInBlock();
    // int startIndex = getStartIndex();
    // int endIndex = getEndIndex();


    //5. getCurrentBlockNo() 작성
    public int getCurrentBlockNo() {
        //my) 인스턴스 메소드는, 인스턴스메소드를 파라미터로 안받아도, 인변을 편하게 사용해서 정의해도된다.
        return (int)Math.ceil((double)currentPageNo / pageCountPerBlock);
    }
    //6. 나머지 4개 [반복문속 매번호출로 업데이트되던 변수] -> 밖에선 메서드로 바꾸자.
    // -> 기존변수가 필요한 곳에서는, 메서드를 호출해주면 알아서 채워질 것이다.
    // -> 메서드화가 끝났으면, 각 변수들이 필요했던 곳 (list..) 가서 메서드로 값을 가져오자.
    public int getStartPageNoInBlock() {
        return pageCountPerBlock * (getCurrentBlockNo() - 1) + 1;
    }
    public int getEndPageNoInBlock() {
        return getStartPageNoInBlock() + (pageCountPerBlock - 1);
    }

    public int getStartIndex() {
        return itemCountPerPage * (currentPageNo - 1);
    }

    public int getEndIndex() {
        return getStartIndex() + itemCountPerPage;
    }
}


