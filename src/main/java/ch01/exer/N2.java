package ch01.exer;
import java.util.Scanner;
public class N2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(!in.hasNextInt())
        {
            in.next();
        }
        int num = in.nextInt();
        System.out.println((num % 360 + 360) % 360);
        System.out.println(Math.floorMod(num,360));
    }
}
