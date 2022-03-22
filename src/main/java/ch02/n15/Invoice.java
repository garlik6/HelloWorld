package ch02.n15;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Invoice implements Serializable {
    private static class Item { // Item is nested inside Invoice
        String description;
        int quantity;
        double unitPrice;

        double price() { return quantity * unitPrice; }
        public String toString() {
            return quantity + " x " + description + " @ $" + unitPrice + " each";
        }
    }
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException{}



    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException{
        double total = 0;
        for (Item item : items) {
            stream.write((item.toString() + "\n").getBytes());
            total += item.price();
        }
        stream.write((total + "\n").getBytes());
    }
    private void readObjectNoData()
            throws ObjectStreamException {}



    private final ArrayList<Item> items = new ArrayList<>();

    public void addItem(String description, int quantity, double unitPrice) {
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);
    }

    public void print() {
        double total = 0;
        for (Item item : items) {
            System.out.println(item);
            total += item.price();
        }
        System.out.println(total);
    }

    public void print(OutputStream stream) throws IOException {
        double total = 0;
        for (Item item : items) {
            stream.write((item.toString() + "\n").getBytes());
            total += item.price();
        }
        stream.write((total + "\n").getBytes());
    }
}