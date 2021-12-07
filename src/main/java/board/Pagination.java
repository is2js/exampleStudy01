package board;

public class Pagination {
    // 1. PagingTest대신 클래스를 새로 하나 만든다. main은 필요없다.
    // -> pagination관련된 변수들을 일단 다 가져온다.
    // -> 여기다 관련 변수, 메서드 몰빵 -> main에서는 인스턴스 생성해서 씀)

    // 2. 핵심 변수들을 가져온다. -> 스캐너 + 테스트데이터들은 필요없다. (Board에 있고, 여깄는 것들은 Board에서 인스턴스만들어서 쓸것임)
    int pageCountPerBlock = 5;
    int itemCountPerPage = 3;
    int currentPageNo = 1;
    // 3. while (true)안 일지라도,  현재 페이지 번호 -> 종속적으로 만들어진 변수들도, 일단 다 가져온다
    // -> while (true)빼고.. 같은 레벨로 복사해온다.
    int currentBlockNo = (int) Math.ceil((double) currentPageNo / pageCountPerBlock);
    int startPageNoInBlock = pageCountPerBlock * (currentBlockNo - 1) + 1;
    int endPageNoInBlock = startPageNoInBlock + (pageCountPerBlock - 1);
    int startIndex = itemCountPerPage * (currentPageNo - 1);
    int endIndex = startIndex + itemCountPerPage;
    // 4. Board 내부에 pagination객체를 만들어준다. -> 5.
}
