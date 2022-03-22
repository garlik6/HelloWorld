package ch01.exer;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class N14 {

    public static ArrayList<ArrayList<Integer>> getSquare()
    {
        ArrayList<ArrayList<Integer>> square;
        square = new ArrayList<>();
        String input1 = "1 2 3\r" +
                        "3 4 5\r" +
                        "6 7 8\r";

        String input2 = "16 3 2 13\r5 10 11 8\r9 6 7 12\r4 15 14 1\r";
        ArrayList<Integer> firstLine = new ArrayList<>();
        Scanner in = new Scanner(input2);
        in.useDelimiter("[\n,\r]");
        String str = "";
        if (in.hasNext())
            str = in.next();
        Scanner sc = new Scanner(str);
        while (sc.hasNextInt()){
            int i = sc.nextInt();
            firstLine.add(i);
        }
        sc.close();
        square.add(firstLine);
        int size = firstLine.size();
        int i = 1;
        while (i < size)
        {
            str = "";
            ArrayList<Integer> line = new ArrayList<>();
            if (in.hasNext())
                str = in.next();
            sc = new Scanner(str);
            while (sc.hasNextInt())
            {
                line.add(sc.nextInt());
            }
            if (line.size() == size)
            {
                square.add(line);
                i++;
            } else {
                System.out.println("wrong size");
            }
            sc.close();
        }
        return square;
    }

    public static void printSquare(ArrayList<ArrayList<Integer>> square)
    {
        for (int i = 0; i < square.size();i++)
        {
            System.out.println(square.get(i).toString());
        }
    }
    public static boolean checkForMagic(ArrayList<ArrayList<Integer>> square)
    {
        int n = square.size();
        int magicSum = n*(n*n+1)/2;
        if (!checkDiagonals(square))
            return false;
        for (int i = 0; i < n;i++) {
            if (rowSum(square, i) != magicSum)
                return false;
            if (columnSum(square, i) != magicSum)
                return false;
        }
        return true;
    }
    public static int rowSum(ArrayList<ArrayList<Integer>> square, int number)
    {
        int sum = 0;
        for (int i : square.get(number))
            sum +=i;
        return sum;
    }
    public static int columnSum(ArrayList<ArrayList<Integer>> square, int number)
    {
        int sum = 0;
        for (ArrayList<Integer> i : square)
            sum +=i.get(number);
        return sum;
    }

    public static boolean checkDiagonals(ArrayList<ArrayList<Integer>> square)
    {
        int n = square.size();
        int magicSum = n*(n*n+1)/2;
        int sum = 0;
        for(int i = 0; i< n; i++)
            sum += square.get(i).get(i);
        if (sum != magicSum)
            return false;
        sum = 0;
        for(int i = 0; i < n; i++)
            sum += square.get(i).get(n-i-1);
        return sum == magicSum;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> square = getSquare();
        System.out.printf("Square is magic :%b\n",checkForMagic(square));
        printSquare(square);
    }
}
