package ch02.n15;

import java.io.*;
import java.util.ArrayList;

public class Invoice implements Serializable {
    private static class Item implements Serializable{ // Item is nested inside Invoice
        String description;
        int quantity;
        double unitPrice;

        double price() { return quantity * unitPrice; }
        public String toString() {
            return quantity + " x " + description + " @ $" + unitPrice + " each";
        }
    }



    private final ArrayList<Item> items = new ArrayList<>();

    public void addItem(String description, int quantity, double unitPrice) {
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);
    }

    public void print() throws IOException {
        print(System.out);
    }

    public void print(OutputStream stream) throws IOException {
        double total = 0;
        for (Item item : items) {
            stream.write((item.toString() + "\n").getBytes());
            total += item.price();
        }
        stream.write((total + "\n").getBytes());
    }

    public void print(ObjectOutputStream oos) throws IOException {

        oos.writeObject(this);
    }
}