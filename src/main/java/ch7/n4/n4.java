package ch7.n4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class n4 {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(2);
        for (Integer integer : integers) {
            integers.remove(1);
        }

        // ways to solve:
        // using iterator directly
        // not removing during iteration
        // use remove if
        // using streams
        // ConcurrentHashMap
        // using ConcurrentHashMap
        // CopyOnWriteArrayList
    }
}
