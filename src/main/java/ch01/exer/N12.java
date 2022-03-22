package ch01.exer;

import java.util.ArrayList;
import java.util.Scanner;

public class N12 {
    public static void main(String[] args) {
        String str = "1 2 3\r" +
                "3 4 5\r" +
                "6 7 8\r";

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> firstLine = new ArrayList<>();
        sc.useDelimiter("[\n,\r,\r\n]");
        String str1 = sc.next();
        Scanner sc1 = new Scanner(str1);
        while (sc1.hasNextInt()){
            int i = sc1.nextInt();
            firstLine.add(i);
        }
    }
}
