package ch01.exer;

import java.util.Scanner;

public class N3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int num3 = in.nextInt();
        if(num1 > num2)
        {
            if (num3 > num1)
                System.out.println(num3);
            else
                System.out.println(num1);
        }
        else {
            if (num3 > num2)
                System.out.println(num3);
            else
                System.out.println(num2);

            System.out.println(Math.max(num1,Math.max(num2,num3)));
        }
    }

}
