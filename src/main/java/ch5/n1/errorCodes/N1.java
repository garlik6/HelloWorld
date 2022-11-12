package ch5.n1.errorCodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class N1 {
    static int errnum;

    public static void main(String[] args) {
        errnum = 0;
        printSumOfValues("src/main/java/ch5/n1/file.txt");
        printSumOfValues("aaaaaaaabbbbbbbbbbbbbbbbbbbbccccccccccccccccc");
        printSumOfValues("file.txt");
    }

    public static ArrayList<Double> readValues(String filename) {
        File file = new File(filename);
        ArrayList<Double> arrayList = new ArrayList<>();
        double f;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                try {
                    f = scanner.nextDouble();
                } catch (InputMismatchException e) {
                    errnum = 1;
                    return null;
                }
                arrayList.add(f);
            }
            return arrayList;
        } catch (FileNotFoundException ex) {
            errnum = 2;
            return null;
        }
    }

    public static double sumOfValues(String filename) {
        ArrayList<Double> arrayList = readValues(filename);
        if (errnum != 0) {
            return -1;
        }
        return arrayList.stream().reduce(0D, Double::sum);
    }

    public static void printSumOfValues(String filename) {
        System.out.println(" sum = " + sumOfValues(filename));
        if (errnum == 0)
            return;
        if (errnum == 1)
            System.out.println("Can't find file with that name, please try another one");
        ;
        if (errnum == 2)
            System.out.println("It seems like file contains non-double values, please try another one");
    }
}
