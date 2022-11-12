package ch5.n10;

import java.util.Arrays;

public class N10 {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int number) {
        if (number == 1) {
            Exception e = new Exception();
            var stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement.toString());
            }
            return 1;
        }
        return factorial(number - 1) * number;
    }

}
