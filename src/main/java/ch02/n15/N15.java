package ch02.n15;

public class N15 {
    public static void main(String[] args){
        Invoice invoice = new Invoice();
        invoice.addItem("a", 2, 1);
        invoice.addItem("b", 1, 2);
        invoice.addItem("c", 1, 3);
        invoice.print();
    }
}
