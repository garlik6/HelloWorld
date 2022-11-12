package ch5.n5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class N5 {
    public static void main(String[] args) {
        Scanner in = null;
        try {
            in = new Scanner(Paths.get("file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter("output.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (in.hasNext())
            out.println(in.next().toLowerCase());
        try {
            out.close();
            in.close();
        } catch (Exception e){

        }
    }
}
