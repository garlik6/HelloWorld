package ch01;

public class N5 {
    public static void main(String[] args) {
        double num1 = Integer.MAX_VALUE + 1;
        int num2 = (int) num1;
//        int num2 = Math.toIntExact(num1);
        System.out.println(num2);

    }
}
