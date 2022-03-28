package ch01;

import java.util.Scanner;

public class N7 {
    static final int MAX = Integer.MAX_VALUE;

    public static int addUnsigned(int a, int b)
    {

        if ((a - MAX > 0 && b - MAX > 0) && (Integer.compareUnsigned(a-MAX,MAX-b) > 0 || Integer.compareUnsigned(b-MAX,MAX-a) > 0)) {
            throw new ArithmeticException("add: > " + 2L * MAX);
        }
//        if (Integer.compareUnsigned(a,MAX) > 0 && Integer.compareUnsigned(b,MAX) < 0 || Integer.compareUnsigned(a,MAX) < 0 && Integer.compareUnsigned(b,MAX) > 0) {
//            if (Integer.compareUnsigned(a + b,MAX) > 0) {
//                throw new ArithmeticException("[2] sum: > " + 2L * MAX);
//            }
//        }
        return a + b;
    }

    public static int multiplyUnsignedInt(int a, int b) {

        if (a == 0 || b == 0) {
            return 0;
        }


        if ((Integer.compareUnsigned(a,MAX) > 0) && Integer.compareUnsigned(b,MAX) > 0) {
            throw new ArithmeticException("prod: > " + 2L * MAX);
        }

        int result = a;

        for (int i = 1; i < b; i++) {
            if ((Integer.compareUnsigned(a,MAX+(MAX-b)) >= 0) || (Integer.compareUnsigned(b,MAX+(MAX-a)) >= 0)) {
                throw new ArithmeticException("prod: > " + 2L * MAX);
            }
            result = result+a;
        }

        return result;
    }

    public static int subtractUnsignedInt(int a, int b) {
        if (Integer.compareUnsigned(a,b) < 0) {
            throw new ArithmeticException("diff: < 0");
        }

        return a - b;
    }

    public static int divideUnsignedInt(int a, int b) {

        if (b == 0) {
            throw new ArithmeticException("dev: /0 ");
        }
        int res = 0;
        while (a >= b) {
            res++;
            a = subtractUnsignedInt(a, b);
        }
        return res;
    }

    public static int remainderUnsignedInt(int a, int b) {

        if (b == -MAX) {
            throw new ArithmeticException("remainder: /0 ");
        }

        while (a >= b) {
            a = a - b;
        }

        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(!in.hasNextLong() && in.nextLong()>=0)
        {
            in.next();
        }
        int num1 = (int) in.nextLong();
        while(!in.hasNextLong() && in.nextLong()>=0)
        {
            in.next();
        }
        int num2 = (int)in.nextLong();
        int sum = addUnsigned(num1 ,num2) ;
        int diff = subtractUnsignedInt(num1,num2);
        int prod = multiplyUnsignedInt(num1,num2);
        int quotient = divideUnsignedInt(num1,num2);
        System.out.println("quotient reference:" + Integer.divideUnsigned(num1,num2));
        int remainder = remainderUnsignedInt(num1,num2);
        System.out.println("remainder reference:" + Integer.remainderUnsigned(num1,num2));
        System.out.printf("sum %s diff %s prod %s quotient %s remainder %s", Integer.toUnsignedString(sum), Integer.toUnsignedString(diff), Integer.toUnsignedString(prod), Integer.toUnsignedString(quotient), Integer.toUnsignedString(remainder));
    }

}
