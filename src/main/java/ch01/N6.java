package ch01;

import java.math.BigInteger;
import java.util.Scanner;

public class N6 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while(!in.hasNextInt())
//        {
//            in.next();
//        }
//        int num = in.nextInt();
//        BigInteger result = BigInteger.valueOf(1);
//        for(int i = 1; i <= num; i++)
//        {
//           result = result.multiply(BigInteger.valueOf(i));
//        }
//        System.out.print(result);
        BigInteger result = BigInteger.valueOf(Math.round(Math.pow(2,32)));
//        result = result.multiply(BigInteger.valueOf(8));
        System.out.println(result);

   }

}
