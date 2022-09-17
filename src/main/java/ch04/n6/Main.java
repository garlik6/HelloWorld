package ch04.n6;

public class Main {
    public static void main(String[] args) {
        Item i1= new Item(10);
        DiscountedItem i2 = new DiscountedItem(10,10);
        DiscountedItem i3 = new DiscountedItem(10,20);
        System.out.println(i2.equals(i1));
        System.out.println(i1.equals(i2));
        System.out.println(i2.equals(i3));
        System.out.println(i1.equals(i3));
    }
}
