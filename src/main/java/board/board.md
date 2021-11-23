# 게시판 만들기
1. 1강
   1. 핵심로직(Board)에서 no main -> runPrompt, runBoard method에서 무한반복 입력기 돌리기
      1. 참고로 미션은.. Application.java에서
         1. [현구막](https://github.com/Hyeon9mak/java-subway-path-precourse/blob/Hyeon9mak/src/main/java/subway/Application.java), [준서](https://github.com/KJunseo/java-subway-path-precourse/blob/KJunseo/src/main/java/subway/Application.java), [빙해](https://github.com/binghe819/java-subway-path-precourse/blob/binghe/src/main/java/subway/Application.java)  [빙해뷰폴더](https://github.com/binghe819/java-subway-path-precourse/tree/binghe/src/main/java/subway/view)
            ```java
               - 준서
            final Scanner scanner = new Scanner(System.in);
            InputView inputView = new InputView(scanner);
            SubwayPath subwayPath = new SubwayPath(inputView);
             boolean running = true;
             while (running) {
             running = subwayPath.run();
             }
               - 빙해
            final Scanner scanner = new Scanner(System.in);
            InputView.scanner = scanner;
            initSetting();
            MainView mainView = new MainView();
            mainView.setVisible();
               - 현구막
            final Scanner scanner = new Scanner(System.in);
            UserInput.giveScanner(scanner);
            SubwayPath subwayPath = SubwayPath.newSubwayPath();
            subwayPath.start();
            ```
   
      2. 실행 기능을 가진 Main 클래스 만들어서 main메소드로 Board객체를 만들어 핵심로직 인스턴스 method 돌리기
      3. if분기별로 처리하기 -> help -> add(추가) -> list(조회) 순... -> 맨 마지막 분기에 안걸리는 예외처리하기
   2. 

## 기능 목록

## 기능 요구 사항

1. 요구사항
```
// 명령어를 입력해주세요 : (출력) help(입력)
// add  : 게시물 등록 (출력)
// list : 게시물 목록 조회 (출력)

// 명령어를 입력해주세요 : (출력) add(입력)
// 제목을 입력해주세요 : (출력) 안녕하세요(입력)
// 내용을 입력해주세요 : (출력) 반갑습니다(입력)
// 게시물이 저장되었습니다. (출력)

// 명령어를 입력해주세요 : (출력) list(입력)
// 번호 : 1(출력)
// 제목 : 안녕하세요(출력)
// 내용 : 반갑습니다(출력)
// ====================================(출력)

// 명령어를 입력해주세요 : (출력) add(입력)
// 제목을 입력해주세요 : (출력) 안녕하세요2(입력)
// 내용을 입력해주세요 : (출력) 반갑습니다2(입력)
// 게시물이 저장되었습니다. (출력)

// 명령어를 입력해주세요 : (출력) list(입력)
// 번호 : 1(출력)
// 제목 : 안녕하세요(출력)
// 내용 : 반갑습니다(출력)
// ====================================(출력)
// 번호 : 2(출력)
// 제목 : 안녕하세요2(출력)
// 내용 : 반갑습니다2(출력)
// ====================================(출력)
   ```