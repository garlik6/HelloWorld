package ch03.n7;

import java.util.ArrayList;
import java.util.Comparator;

public class N7 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("666666");
        list.add("1");
        list.add("22");
        list.add("333");
        list.add("4444");
        list.add("55555");
        int timesShuffled = LuckySort.luckySort(list, Comparator.comparingInt(String::length));
        System.out.println(list);
        System.out.println(timesShuffled);
    }
}
