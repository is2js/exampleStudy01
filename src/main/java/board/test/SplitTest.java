package board.test;

import board.BoardArticle;

public class SplitTest {
    public static void main(String[] args) {

        // 1. txt를 불러와 어디서 사용할지 고민해야하는데, 어디서 사용하든 txt한줄 -> 객체로 받아와야한다.
        // -> 일단 저장한 `설명: + 객체변수 + 구분자(,)` String을 복사해서가져와  다시 쪼개는 것부터 연습해야한다.
		// -> 한줄짜리 target String을 객체로 바꾸는 연습
		String target = "id:1,title:안녕하세요,body:내용1입니다.,memberId:1,regDate:2021.12.08,hit:20";
		// String[] properties_arr = target.split(",");
        // for (String s : properties_arr) {
            // System.out.println(s);
		// }
		// id:1
		// title:안녕하세요
		// body:내용1입니다.
		// 	memberId:1
		// regDate:2021.12.08
		// hit:20

		//2. 배열의 각 요소에서, 다시 설명하는 부분도 split후 뒷부분만 필요하다.
		// List<String> collect1 = Arrays.stream(properties_arr)
		// 	.map(string -> string.split(":")[1])
		// 	.collect(Collectors.toList());
        // System.out.println(collect1);

		//3.my) 종합
		// List<String> collect = Arrays.stream(target.split(","))
		// 	.map(p -> p.split(":")[1])
		// 	.collect(Collectors.toList());
        // System.out.println(collect);

		//4. 각 요소들로 Board객체를 만들어는데 조금 다른방식으로 만든다.
		// -> 생성자에 파라미터를 직접 넣어주는 방식이 아니라,
		// -> **일단 생성해놓고, 개별로 인스턴스 변수(객체별 관리값)들을 확인해서 입력해주는 방식으로 객체 완성**
		// 1)   기존: 생성시 파라미터 다 집어넣어 완성 -> 그대로 두면서
		// 2)  `빈 객체`를 생성할 수 있게, `노 파라미터 생성자`를 정의하면서 빈 객체 생성
		//    `빈 객체`에다가 일일이 `빈객체.변수 = 값 하나씩 할당해줘서 완성`하기
		// new BoardArticle(collect.get(0) , collect.get(1)... )

		// 5. 파라미터에 값 넣으라고 빨간줄이 뜨지만... 바로 값을 넣어주진 못한다.
		// -> **노 파라미터 생성자를 추가한다!!!!!!!!!**
		// --> ctrl + .  -> [create constructor]
		// BoardArticle boardArticle = new BoardArticle();
		// 6. 빈 생성자 완성 -> 밖에서 관리 변수(인스턴스변수들) 채워주기
		// -> 하나씩 체크해서 넣어주면 된다.
		// -> String[] 배열을 돌면서,, split:의 앞부분[0]으로 해당 데이터 맞는지 equals 검사를..
		// -> 맞으면 <형변환>과 함께 [1]넣어주기.. 작전..
		// -> 설명부의 [0]를 챙겨야한다.. -> stream으로 하기엔 무리가 있다.

		// Arrays.stream(target.split(","))
		// 	.map(p -> p.split(":"))
		// 	.filter(arr -> arr[0].equals(변수명))
		// 	.forEach(boardArticle.변수 = arr[1])


        // 변수 데이터갯수만큼 돔 + 빈 객체를 생성부터가 시작로직임.
		// -> 빈객체 생성 -> txt split해서 채워주기 -> 객체 완성
		BoardArticle boardArticle = new BoardArticle();
		String[] properties_arr = target.split(",");
		// -> 7. 변수갯수만큼 분기가 만들어서 확인해야하기 때문에.. 너무 길어짐 -> 메소드로 뺄 뜻.
        for (String properties : properties_arr) {
			String[] property = properties.split(":");
			if (property[0].equals("id")) {
				boardArticle.id = Integer.parseInt(property[1]);
			}
			if (property[0].equals("title")) {
				boardArticle.title = property[1];
			}
			if (property[0].equals("body")) {
				boardArticle.body = property[1];
			}
			if (property[0].equals("regDate")) {
				boardArticle.regDate = property[1];
			}
			if (property[0].equals("memberId")) {
				boardArticle.memberId = Integer.parseInt(property[1]);
			}
			if (property[0].equals("hit")) {
				boardArticle.hit = Integer.parseInt(property[1]);
			}
		}

        // 8. 반복문이 끝나고나서야 객체가 완성이 된다.
		// -> 완성된 객체를 출력해본다.
        System.out.println(boardArticle.id);
        System.out.println(boardArticle.title);

		//9.  [빈객체 생성 -> txt split해서 채워주기 -> 객체 완성]의 로직을 복사해서
		// -> FileManager클래스에 복붙한 뒤, 메서드로 만들어준다. -> 10.
	}
}
