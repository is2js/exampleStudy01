### day08(추상메서드, 추상클래스, 인터페이스)
 - `상속과 다형성 요약`: 상카객체에 담는 다형성으로 -> `<<상카객체 배열>> or <<상카객체를 파라미터로 받는 곳>>`에 넣어 일괄처리 but 공통기능 수정사용(Override)으로 내부에서 분기별 처리
 - `추상메서드와 추상클래스`: 하위 카테고리Class별 내부 분기처리  일괄로 & [강제로 수행]하기 위해서
   - [일반class의 상속]이 아니라  상카의 [구현부삭제된 공통기능 메서드]를 반드시 오버라이딩해서 써야하는 [추상메서드 in 추상클래스]를 만들어 상속한다.

### day07(상속과 다형성 복습 in board)
1. `공통기능`을 가졌고, 일괄처리를 원한다면?
   1. `상속`을 이용한 `상위-하위카테고리` 만들기
      1. 하위카테고리는 굳이 공통기능을 구현안해도 상속되어있다.
   2. `다형성`을 이용해서 `상위카테고리 참조변수`에 `하위`카테고리 `객체를 담기`
   3. 같은 객체type(참조변수)기 때문에, 상카Class[]배열에  1type으로서 배열을 만들 수 있다.
   4. `반복문`으로 공통기능을 일괄처리하기 
      1. **오버라이딩을 통해, 상속받은 공통기능을 다르게 구현할 수 있다!**
         1. 메서드이름만 같으면 공통기능-일괄처리는 유지되기 때문에 `상속으로도 다르게 구현해도 다형성 일괄처리가 가능`하다.
2. 실전 적용시
   1. `Article`과 `Reply`객체를 `파라미터`로 받아 **동일 기능하는 메소드를 통합**하고 싶다
      1. 다형성을 위해 BaseClass, 상위카테고리Class를 생성한다.
         1. 상속되는 변수들은 지워준다. class명을 BaseXXX-> 2가지(ArticleXXX, ReplyXXX)를 뻗어나가게 수정해준다.
      2. 메소드 2개를 1개로 통합하고, `파라미터`로는 `다형성`으로서 `상카Class객체`를 받도록 수정해준다.
   2. `Member` ->  `기존`과 동일한 일반멤버(`GeneralMember`) + **`추가 변수`(point등), 메소드를 `가지면서 따로 관리하고 싶은 하카Class`를 만들고, `다형성으로 일괄처리` 하되, `분기별 달라지는 코드를 공통기능(메서드) -> 상속후 수정사용(Override) 구현`한다.**

### day06(상속, 오버라이딩, 다형성)
- 요약) 자식class: `1)상속`으로 부모 변수+메소드(기능) 물려받으면서, + `2)method추가ormethod오버라이딩`으로 `기능 추가` or `기능 수정(재정의)`한 `하위 카테고리`를 만드는 것
- 요약2) 오버라이딩시 switch변수 활용(isTurbo 등 하위카테고리만의 기능 사용유무를 관리하는 상태) 등으로 메소드 재정의, 수정 중에 , 부모꺼 그대로 사용하는 분기 일 떄, `super.메소드()` 사용.
- 요약3) 새로운기능을 위해 상속 + 새로만든 `하위class`지만, `상위class 참조변수`에 담기면서 `공통기능(method) or 수정재정의된 공통기능`까지 호출될 수 있다. -> `다형성`
  - **새로운기능을 가질 수 있는 하위카테고리들이지만, `같은 상위카테고리에서 파생된 자식들`을 `공통기능 or 수정(재정의된 공통기능)`만 사용해서 똑같이 다루기 = 일괄처리 (ex> 배열에 담아서 같은 메소드 호출)할 수 있다**
  - **공통기능 or 수정된 공통기능만 쓸 건데, 다형성 안쓰면.. 일일히  상카객체.숨쉬다(), 고양이.숨쉬다(), 강아지.숨쉬다() -> 일괄처리없이 모두 수동으로 100개 다 호출시켜줘야한다**
  - **하위개념들을 상위개념으로 다룰 수 있게 되면(다형성) -> 통일된 방식(배열->반복문->일괄처리)으로  여러객체를 다룰 수(공통기능 or `하위개념들은 자신만의 수정된 공통기능들을`) 있다.**

1. 공통 기능외에 (모두가 아닌) `추가 기능을 가진 객체`를 따로 만들고 싶다? 
2. -> 요약) **공통기능class를 `상속`한, `새class`에 새method 작성해서 사용하도록 `하위 카테고리` 만들어주기**
   1. <구체적 기능을 가진 하위카테고리> 를 만드려면, 바로 작성하면 모두 기능 가져버림. no 하위 카테고리 
   2. 공통기능 카테고리 상속한 특정 기능 class(특정기능 == 하위카테고리 class 생성)를 따로 만들어서 객체 생성해줘야한다.
      1. 상속 -> 공통 데이터묶음(인스턴스변수)  + 공통 기능(method)들을 다 받아온다.
      2. 새로운 class -> 해당 새class 객체만 해당 새 기능(method)작성한 것을 사용할 수 있음.
3. 공통 기능 상속의 이점 : **공통 기능을 수정하면 -> 상속한 하위카테고리(특정 기능 객체들)은 자동으로 수정됨**
   1. 하위 카테고리는 상속을 통해 공통변수 + 공통method를 자동으로 받았음.
   2. 자동으로 받은 상태+기능들은, **공통class 부모class 수정시 자동으로 수정됨**
      1. **그대로 물려주니까~~ 수정해도 수정된 것을 물려준다~** 
      2. **-> 반드시 공통기능/필수기능은 부모, 상위class로 만들어놓고 특수기능, 추가기능, 구체적 기능 상속한 새class로 하자~**
4. 반대로 고양이, 강아지의 공통개념들(변수들+메소드들)을 뽑아내는 것을 `추상화` -> `부모class`로 뽑아낸다고 한다.
   1. 저마다의 개성을 뽑아낸 것은 **상속후 method들(새기능)을 추가한 `자식class`**
      1. my) 추가기능을 method로 추가한 것이 `extends한 자식class` : 부모class 변수&메소드에 + `메소드 추가`한 것
      2. my) 자식들 -> 아~! 새로운 메소드 추가로 새로운 기능들 추가된 하위카테고리구나..
5. my) **자식들 -> 새로운 기능(method) 추가 뿐만 아니라 상속후 `기능(method) 수정 or재정의(Override)`한 것도 자식class다.**
   1. 만약 부모자동상속 method를 오버라이딩 안하고 정의했으면? -> `자신에게 있는 메소드가 1순위` -> 없으면 부모가 상속해준 것 찾는 것.
6. my) **상속후 메소드추가 or 재정의 뿐만 아니라 `상속후 변수(데이터) 추가`하여 활용하는 것도 `하위카테고리, 자식class`다.**
   1. **`부모에게 없던` 인스턴스 변수(객체단위로) 스위치`변수 추가`-> Override(공통(부모) 기능(메소드) 수정)시 활용할 수 있다.**
      1. boolean isTurbo = false; 추가 선언후 -> On, Off 메소드 만들기 + 삼항연산자로 Switch변수만들기
      2. @Override 부모 원래 기능에 -> Swtich변수 On일때만 새로운 기능 수행
         1. **`자식에 switch변수를 추가후 Override`해서 하는 것**은 여러메소드를 물려받은 경우, (대부분은 물려받은대로 쓰되)일부만 고쳐쓸때 유용하게 사용한다.
      3. **`@Override했는데도(수정, 재정의로 들어왔는데)` Switch변수 off로 `부모상속받은 것을 그대로 사용`해야할 때 -> `super.메소드()`;** 
         1. 재정의, 수정시 분기별로, 부모꺼 그대로 사용해야한다면 super.메소드()를 활용한다.
7. 다형성: 한 객체가 다양한 형태로(하위class지만, 상위class 참조변수에 담기면서 공통기능(method)만 호출될 때)
   1. **하위카테고리라도 `수정, 재정의된 공통기능 포함` 공통기능만을 다룬다면, 상위 카테고리로서 다루어질 수 있다.** 
      1. -> 다형성으로서 `부모class 참조변수`에 담긴다.
   2. **대신 개별 추가/수정된 기능(메소드)은 포기해야한다(에러남) -> 재정의된 공통기능은 가능**
      1. -> **부모class(상위카테고리)참조변수에 담겼다면, `하위카테고리만의 새로운 기능(method)호출이 안된다.`**


### day05(클래스, 생성자)
1. 클래스: <기능, 데이터처리를 할 수 있는> 메소드들과  <1단위에 포함되는 여러 변수 == **`데이터들의 묶음`(데이터들은 수백수천개라 묶어서써야함!)**>. 메소드들은 클래스를 통해 기능발휘할 수 있음.
   1. 클래스도 하나의 타입이 된다. Person p  -> **클래스Type의 배열도 만들 수 있다. `Person[] people = new Person[5]`**
   2. 향상된 for문에 null 객체(배열 속 대입안해줘서 null)가 있어도 에러는 안난다. 하지만 배열에 값을 초기화 안해준 것을 접근하면 에러난다.
2. 기능: 데이터가 있어야하고(in class), (in method안에서) 데이터를 처리해서 기능을 함.
   1. 데이터는 메소드(지역변수)가 아닌 class에서 변수로 만들어쓴다.
      1. 유틸변수로서 매번 똑같은 값 쓸 변수만 static으로 정의해준다.
   2. 메소드로 뺄 때, 빨간줄의 변수들 -> **매번 바뀐다. -> `파라미터`**  로 넘겨주거나 or **모든 메소드에서 동일 == 모든 객체에서도 동일하다. 지역변수 -> 공통의 `클래스변수`**가 되게 한다.
   3. 변수들의 묶음, 데이터의 묶음(변수 여러개가 1단위) -> 같이 들고 다닐려면 묶어야함. -> class
3. **메소드의 여러 파라미터 -> 객체라면 객체 1개만 받아서, 내부에서 객체.인스턴스변수를 뽑아서 처리하자. `옮길때는 통째로 옮겨야함!`**
4. **스태틱: `공유` 목적의 자원. 변하는 인스턴스가 아닌 `class에 생성되서 보관되어야` 공유됨. main등의 static 공유자원은, class속 static붙은 공유 자원밖에 못씀. -> 메인에서 개발은X**
   1. **class-> 객체1, 객체2, 객체3, ..., 객체10** -> **모든~ 객체가~ `공유`되려면, static붙은놈은 `class`안에 있어야겠다~ -> `static`** 
   2. 메인에서 개발이 아닌 -> 속성(상태)  데이터묶음 + 기능들묵음의 class로 표현하고 싶은 것을 표현해야함.
7. **클래스 static(공유)변수로 counting하는 객체 생성 갯수**
   1. 생성자는 객체초기화 외에, 객체생성시 작업코드를 넣을 수 있다. -> count++; 가능
   2. **`class설계도에 넣어놓는`** `공유`변수, `결과값을 모든 객체가 공유`하는 변수 -> `static` int count = 0;선언 가능.
   3. 그림 생각하기~
5. **객체를 만들기**
   1. **`class 파일을 새로`** 만든다!! 메인에서 정의 X  -> `default 인스턴스 변수` 데이터 묶음들 선언
   2. 메인메소드가 있는 곳으로 가서 생성하기
6. 생성자: 객체는 초기화하고 난 뒤에야 의미가 있다. -> 인스턴스변수가 10개나 된다면? -> 객체 초기화를 객체한테 맡기자.
   2. setData(여러, 파라, 미터); -> `객체 생성시마다 아래line에 도.. 객체.setData(파라,미터); 해줘야하는 번거러움` -> 생성자에서 생성과 동시에 데이터 초기화 해주자.
   3. **생성자는 객체생성시 자동호출되며, setData대신 바로 인스턴스변수들을 생성과 동시에 초기화할 수 있다.**
      1. **객체 초기화 뿐만 아니라, `객체생성시 실행되어야하는 코드`가 있으면 다 `생성자에 넣어주자`.**

### day04(ArrayList, 데저프에 적용)
1. ArrayList<E>에 제네릭: 원소들의 타입E(lement들 Type정하기 by 제네릭) int -> <`Integer`>로 기입해야함.
2. 핵심기능 5가지
   1. .size()  0부터 -> add, remove시마다 수시로 찍어주자.
   2. .add() -> 한번에 하나씩 밖에 못 넣는다.
   3. .set(index, value) -> list는  배열이 아니므로 list1[1] = 2; `인덱싱 안됨!!`
   4. .get(index) -> `인덱싱안됨.`
   5. .remove(index) -> `삭제후 알아서 index땡겨지는 것 조심`
      1. **`.remove(값)`도 가능하다! 문자열일때?**
   6. **`.indexOf(값)`도 가능하다.**
3. if조건에 `||` 사용해보자~
4. 데이터저장을 길이가 정해진 배열 -> arraylist로 바꿔보자.
   1. **갯수제한이 없어졌다.** 
   2. **add/delete마다 index관리** -> 알아서 조절된다. 
      1. add시 `.size()-1`로 제일 최근 index를 얻어서 처리해도 된다.
      2. delete시.. 일반배열이면,, i 삭제 -> i+1이 i를 덮어쓰기로 한칸씩 왼쪽으로 당기기..
      3. 맨마지막은 null?
5. main의 기능들을 메소드로 빼기
   1. 내용 잘라내기 (분기문이면 return, continue, break 제외)
   2. 함수(); 작성
   3. 자동완성
   4. **없다고 빨간줄 뜨면 파라미터로 넣어주기**
   5. **빼고 있는 거의 `모든 메소드들이 원하는 인자` -> 파라미터로 넘기지말고, `main = controller 메소드 밖`의 모든 객체, 클래스 단위(==`메소드들한테도 공용`)공용 변수 `static 클래스변수`로 빼준다.**
      1. **my) 메소드들 공용 사용이면, (like 객체마다 결과값 관리(X), `모든 객체 공유하는 결과값 -> static 변수` == )`클래스내 모든 메소드들 공용 사용 -> 파라미터로X -> static 변수로빼서 class내서 막사용`**
      2. 주로 list(data), scanner 같은...`클래스내 메소드의 공용 변수라고 판단된 순간 -> 파라미터X -> static으로 빼서 막쓰자.`


### day03(데이터 저장관리 프로그램-구조 및 1개 데이터(변수) -> 배열까지)
1. `프로그램 무한반복 입력 구조짜기` - 무한반복의 입력 -> 안내멘트 -> 종료조건까지 완성
   1. while (true) -> if ()  break; 미리작성 주석  :`무한반복시 종료조건 먼저 작성해두기` 
   2. scanner.nextLine() -> test -> 안내멘트 -> 종료조건 입력
   3. if (종료조건채우기) -> 종료 멘트 -> test
2. 각 커맨드마다 else없이 if문으로 분기한다. -> 공통코드~~ ~~없으면 분기마다 continue도 필요없다. 
   1. {}중괄호가 영역으로서 지역을 의미한다. -> 각 if {}분기문마다 공유할 수 있는 변수는 {}지역을 벗어나서 빼서 만든다.
      1. **밖에서 변수 만든다는 것: `default값으로 초기화`** -> {} {} 영역마다 할당해서 사용 -> 사실 ` = null;`로 초기화하는게 좋다.
   2. 반복문에 의한 초기화를 배제하기 위해 -> `반복문 밖`에 만든다.
3. `저장변수, 데이터 변수 등은 null로 초기화`해야,, delete나 값이 없을 경우 if로 확인해서.. 멘트 출력시 쉬워진다.
   1. `데이터 없음을 표현`하는 방법은 null로 초기화 하는 것
   2. `delete` == 데이터를 `data = null`로 넣어준다.
   3. 비어있는 데이터확인은 `if ( data == null )`로 편하게 한다.
4. **각 분기의 if들마다 안걸렸을 때의 `예외처리`를 `if ~ continue or return`에 안걸리는 `맨 뒤에서 공통 처리`해준다.**
   1. else를 쓴다면 if -> else if 들로 연결 -> 최종 else에는 예외처리
   2. **else를 안쓴다면 `if 무반종료필터링break`이후 -> if continue(return)-> if continue(return)-> ... -> `정해진 분기에 안걸린 예외`는 continue or return도 안당했음. `if분기 없이 맨마지막 공통코드`로 적어버린다.**
      1. **원하는 분기에 걸렸으면 continue에 걸려서 다음루프 이미갔음!!**
      2. **`지정한 분기에 걸리지 않은 것들 싹다==예외들` 맨 마지막에 남아.. 처리됨.**
      3. **어차피 루프끝나기 직전이라, 다음 루프가서 입력받게 될 테니 `잘못된 입력입니다.멘트`만 날려준다.**
   3. my) 모든 각 분기if문 속의 `continue; or return;` 를 보고 -> `그 뒤엔 if분기들 이외의 (if굿 ->)예외처리 or (if쓰레기->)공통코드가 있을 것`이다. 생각
   4. **분기(if break, if continue ~) -> 마지막 예외 패턴 말고 vs `if not (필수조건) continue`의 `필수조건못지킨 예외는 건너띄기`작전도 있다.**
5. 데이터 변수 -> 배열로 저장시 주의점
   1. 배열부터는 for를 통한 데이터 read를 하므로  -> if (data == null ) 같은 빈 데이터 처리는 .. 안해도된다. 
      1. **주의) 배열.length로 데이터 빈것 확인? -> 주의) new 배열[] 초기화시 `.length`때리면 `고정으로 N개`로 뜬다! null이 3개 차있다..로..**
         1. 그냥 for돌려서 0개면 아에 안돌아가니 ... 알아서 처리되도록 하자?
   2. add/delete시에는 index관리를 따로 해줘야한다. -> int index = 0부터 시작하는 변수필요


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


### day01(스캐너, 반복문, 응용구구단, 배열)
1. **숫자입력도 nextLine()으로 먼저 받고 -> parseInt -> int 처리(nextInt()ㄴㄴ)**
2. while 반복문은 if에서 파생된 것으로 **조건변수를 이용하며, 보통은 증가**시킨다.
   1. 조건변수는 while문r 에서는 미리  /ㅠ 'while문 위에 생성한 뒤, 반복문마다 update되어야한다.
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


