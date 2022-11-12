package ch5.n1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class N1 {
    public static void main(String[] args) throws FileNotFoundException {

        printSumOfValues("src/main/java/ch5/n1/file.txt");
        printSumOfValues("aaaaaaaabbbbbbbbbbbbbbbbbbbbccccccccccccccccc");
        printSumOfValues("file.txt");
    }

    public static ArrayList<Double> readValues(String filename) throws FileNotFoundException, InputMismatchException{
        File file = new File(filename);
        ArrayList<Double> arrayList = new ArrayList<>();
        double f;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                f = scanner.nextDouble();
//                System.out.println(f);
                arrayList.add(f);
            }
            return arrayList;
        }
    }

    public static double sumOfValues(String filename) throws FileNotFoundException, InputMismatchException {
        return readValues(filename).stream().reduce(0D, Double::sum);
    }

    public static void printSumOfValues(String filename){
        try {
            System.out.println(" sum = " + sumOfValues(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file with that name, please try another one");;
        } catch (InputMismatchException e) {
            System.out.println("It seems like file contains non-double values, please try another one");
        }
    }
}
