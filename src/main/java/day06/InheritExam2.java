package day06;

public class InheritExam2 {
    public static void main(String[] args) {
        오리 오리1 = new 오리();
        오리1.날다();
        
        // 기능을 추가한 자식(하위카테고리)
        흰오리 흰오리1 = new 흰오리();
        흰오리1.날다();

        // 기능을 수정한 자식(하위카테고리)
        고무오리 고무오리1 = new 고무오리();
        고무오리1.날다();
    }
}
