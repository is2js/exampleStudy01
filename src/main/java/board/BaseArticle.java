package board;

//1. 이름 변경 : BaseInfo -> BaseArticle
// 하위1: Article -> BoardArticle
// 하위2: Reply -> ReplyAricle
// public class BaseInfo {
public class BaseArticle {
    int id;
    String body;
    String regDate;
    int memberId;
    String nickname;
}
