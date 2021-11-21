### day02(continue, 형변환, 랜덤수, 메서드, 배열문제)
1. 반복문을 break가 아닌 for문에서 continue 특정 회차(들)만 건너띌 때
   1. 로또 번호를 뽑을 때, 중복없이 서다수 뽑을 때, `뽑은 번호다? 중복번호 뽑지말고 건너띄기`
      1. **중복제거할 때, 담으면서 + continue를 기본으로 쓰는 것 같다.**
      2. **`ex>짝수만 뽑기: 전체 중에 일부 -> 전체 중에 아닌것을 if not continue`**
      3. **`ex>중복제외 뽑기: 끝모른체 전체 중에 찰때까지 -> 전체 중에 중복이라면 if continue하면서 찰때까지 반복`**
2. 실수값을 반환받으려면, 10/3 -> `10.0`/3 으로 `최소 소수점찍힌 실수 1개`를 준다.
   1. **그러나 `변수에 담긴 int`들을 계산하는데, double실수값으로 받아야 할때는?**
      1. 직접 소수점 못찍어준다 -> **최소 1개는 소수점 찍히도록 double형변환 해줘야한다.**
3. 자동형변환은 안전유무를 따져서 작-> 큰것에 넣을 때만 자동형변환됨.
   1. 큰 -> 작은 것에 넣을 때는, 강제로 수동형변환 시켜줘야한다 (int) 큰 -> 작
4. **난수뽑기 자바Api 2개**
   1. Math.random() + (int)  : 0~0.999 default로 0~9, 0~99, 0~999
      1. (int) 담에 결과값 전체를 괄호쳐줘야함.
   2. new Random().nextInt(n) :  0~n-1까지의 난수 1개 발생.
5. 메서드: 중복되는 코드(기능)를 재활용하며 값 바꿔쓰기
   1. 변수: 중복되는 값을 재활용하며 바꿔쓰기
   2. 주의: 메서드안에 메서드를 만들순 X 클래스가 메서드 모음집임.
6. List가 아닌 배열에 원하는 원소만 모을 때, **원하는 원소의 갯수를 먼저 따로 세서, 담을 배열의 갯수를 미리 알고 있어야**하므로, 
   1. 짝수만 for문으로 확인하면서 담고 싶을 때 -> **짝수 갯수 확인용으로 for문을 먼저 한번 돈다.ㅠ**
   2. 문제는 또 다시.. 원본배열돌면서 짝수일 때, 줄어든길이의 배열에 순서대로 넣어야하는데, add가 안되므로 **0부터 추가할때마다 index변수+1씩하면서 채워줘야한다.**
      1. `List`는 빈 List에 .add만 해도 `빈배열초기화` + `알아서 순서대로 추가`
7. 


### day01(스캐너, 반복문, 응용구구단, 배열)
1. **숫자입력도 nextLine()으로 먼저 받고 -> parseInt -> int 처리(nextInt()ㄴㄴ)**
2. while 반복문은 if에서 파생된 것으로 **조건변수를 이용하며, 보통은 증가**시킨다.
   1. 조건변수는 while문에서는 미리 while문 위에 생성한 뒤, 반복문마다 update되어야한다.
   4. 또다른 방법: while (true) 무한반복문 ~~+ if break를 써서 종료시킨다.
      1. my) if확인 직전에 update가 되어야~~하므로,
         1. update는 자체처리후 맨 마지막에
         2. if검사는 while (true)에 시작하자마자 주기
      2. 내부에 if가 들어가야하므로 또 조건변수가 필요하긴 하다. 둘중에 편한거 쓰기.
   
3. 응용구구단: 구구단도 일단 기본 구구단 작성해놓고 변수로 바꾸기
4. 배열: 변수와 달리 **숫자를 부여해놓고 `반복문 속 숫자로 일괄처리가 가능`해지는 것이 제일 큰 특징**
   1. for의 작성은 for () {} 부터 만들어놓고 조건식을 적자.
   2. i <= 9보다는 **i < 10을 권장하는 이유는 10이 배열의 길이를 의미**하니까
   3. **일반배열은 초기화시 배열길이가 결정되기 때문에, 입력받아 넣을 예정이라고 해서`{};`로 0개짜리 배열로 초기화하면 안된다.**
      1. **배열에 저장할 거면, 생성시부터 몇개짜리배열인지 알려줘야한다.**
   4. **입력도 출력처럼 for문으로 배열길이만큼 돌면서 nextLine()대기**시켜주면 되는데, 100개가 넘어간다면?
      1. **배열 길이 정해졌다면, 배열 초기화는 `new type[length];`**
         1. **int는 0으로채워짐 / String은 null로 채워져잇음.ㅋ**
   5. 거꾸로 출력하려면  length-1 ~ 0까지... `for (int i = arr4.length-1 ; i >=0 ; i--) {`
      1. 여기서는 시작시 `n-1` index와  `>=0` 등호를 이용해서 끝점을 나타내줌. 
      2. 증가시에는 `0` index와   `< 길이`와 부등호만


