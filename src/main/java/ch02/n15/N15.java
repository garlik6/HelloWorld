package ch02.n15;

import java.io.*;

public class N15 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        fileOutputStream.close();

        invoice.print(System.out);

        file = new File("file.out");
        fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        invoice.print(oos);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("file.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        invoice =  (Invoice) oin.readObject();
        System.out.println("from object stream:");
        invoice.print();
    }
}
