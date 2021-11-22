package day03;

public class LocalVariable {
    public static void main(String[] args) {
        int a;
        {
            a = 10;
        }

        {
            System.out.println(a);
        }
    }
}
