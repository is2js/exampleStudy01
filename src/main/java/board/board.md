# 게시판 만들기
1. 1강
   1. 핵심로직(Board)에서 no main -> runPrompt, runBoard() method에서 무한반복 입력기 돌리기
      1. 참고로 미션은.. Application.java에서
      2. 실행 기능을 가진 Main 클래스 만들어서 main메소드로 Board객체를 만들어 핵심로직 인스턴스 method 돌리기
      3. if분기별로 처리하기 -> **`help -> add(추가) -> list(조회)`** 순... -> 맨 마지막 분기에 안걸리는 예외처리하기
2. 2강
   1. update시 입력받는 게시물번호는 index가 아니라 고유번호다 -> 고유번호도 데이터로서 add할 떄,넣어줘야한다.
      1. 앞에께 삭제되더라도 땡겨가는 일은 없어야한다.
   2. 고유번호 ->  autouincrement 구현 -> 그것을 저장하는 자료구조도 또 구현
   3. update시 입력받은 게시물고유번호로 -> 3 list의 index를 찾아야하는데
      1. my)list.indexOf(value)로 index를 찾을 수 있지만
      2. 강사는 어떤 값이 배열 어디에 위치(index)를 알 수없으니 for문으로 돌아서 검색해야 된다고 한다.
   4. update시 게시물을 찾다가 없을 수 도 있다. -> targetIndex에 초기화값(-1)이 그대로 있을 것이다 `초기화 값 활용`!!
      1. my) **수정(삭제)시 해당 데이터를 찾아야하며, index검색은 `찾았을 때 반복문밖에 챙겨둘 & 못찾을 것 대비 int -1`의 지역변수를 하나 만들어놓는다.** 
      2. **`for문으로 배열속 값 찾기`시에 -> 찾았는데 없을 때, 초기화 값으로 확인해주는 sense 활용~!!**
3. 3강
   1. 조회는 리팩토링 하기도 전에 미리 함수로 짜서 -> 수정 등에서 보여지개 한다.
   2. 삭제는 수정과 비슷하지만 쉽다.
      1. 수정, 삭제는 고유번호 선택하여 검색 -> 반복문통해 찾아 해당 index 없으면 초기화값활용해서 확인한 뒤 없다는 멘트 -> 수정/삭제후 원래 조회 띄워주기
      2. 수정/삭제시 고유번호 검색부분(getIndexOfArticle)도 중복으로 리팩토리 대상이다
      3. **리팩토링으로 for문 break가 메소드 안으로 왔다면 return으로 바로 변경해줄 것!!!**
         1. **메소드 안으로 들어왔다면, for에서 받검색시 찾았을 때 받아주는 변수도 필요없다 찾으면 바로 return**
4. 4강
   1. 데이터가 2개이상 표현되는 단위는 무조건 묶은 class로 생각한다.
      1. class생성후 add부터 고쳐나간다.
      2. 묶더라도 저장은 class의 객체를 Arraylist에 순서대로 해줘야한다.
   2. new 클래스( 정의안해준, 생성자, 넣은 빨간줄); -> ctrl+. -> 생성자 편하게 추가해주기?
## 기능 목록

## 기능 요구 사항

1. 요구사항 1->2->3->4
```
- 게시물 데이터가 분리되어 있어 관리하기가 어렵습니다. 하나의 게시물을 표현하는 여러 데이터를 묶어서 구조화 해주세요.
- 힌트 : class와 객체를 생각해주세요.
   ```