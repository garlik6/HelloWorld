package ch02.n15;

import java.io.*;
import java.util.stream.Stream;

public class N15 {
    public static void main(String[] args) throws IOException {
        Invoice invoice = new Invoice();
        invoice.addItem("a", 2, 1);
        invoice.addItem("b", 1, 2);
        invoice.addItem("c", 1, 3);
        invoice.print();
        PrintStream stream = System.err;
        invoice.print(stream);
        File file = new File("file.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        invoice.print(fileOutputStream);
        invoice.print(fileOutputStream);
        invoice.print(System.out);
//        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
//        oos.writeObject(invoice);
    }
}
