package ch01.exer;

import java.util.ArrayList;
import java.util.Scanner;

public class N15 {
    public static void main(String[] args) {

        int num = getNum();
        ArrayList<ArrayList<Long>> triangle = getTriangle(num);
        printTriangle(num, triangle);

    }
    public static int getLen(int num, ArrayList<ArrayList<Long>> triangle)
    {   int len = 0;
        for (int i = 0; i < num; i++) {
            len += String.valueOf(triangle.get(num-1).get(i)).length();
        }
        len = len + num * 2;
        return len;
    }
    public static void printTriangle(int num, ArrayList<ArrayList<Long>> triangle) {
        for (int i = 0; i < num; i++) {

            int offset =  (getLen(num, triangle)-getLen(i+1, triangle))/2;
            for (int j = 0; j < offset; j++) {
                System.out.print(" ");
            }
            System.out.print(triangle.get(i).toString());
            for (int j = 0; j < offset; j++) {
                System.out.print(" ");
            }

         System.out.println('|');
//         System.out.println(getLen(i+1,triangle));

        }
    }

    public static ArrayList<ArrayList<Long>> getTriangle(int num) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<>();
        for (int i = 0; i < num; i++){
            triangle.add( new ArrayList<>());
            for (int j = 0; j < i+1; j++) {
                triangle.get(i).add(1L);
            }
            for (int j = 1; j < i; j++) {
                triangle.get(i).set(j,triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
            }
        }
        return triangle;
    }

    public static int getNum() {
        Scanner in = new Scanner(System.in);
        while(!in.hasNextInt())
        {
            in.next();
        }
        return in.nextInt();
    }
}
