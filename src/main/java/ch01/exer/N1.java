package ch01.exer;
import java.util.Scanner;
public class N1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(!in.hasNextInt())
        {
            in.next();
        }
        int num = in.nextInt();
        System.out.println(num);
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toOctalString(num));
        System.out.println(Integer.toHexString(num));
        System.out.printf("%A", (float)num);
    }
}
