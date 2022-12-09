package ch01.sec01;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class HelloWorld {
    public static void main(String[] args) {
        frequencySort("adljhkdjhgsjdfhldksg;hgnvald;fj");
    }

    public static String frequencySort(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : s.toCharArray()) {
            hm.merge(c, 1, (a, b) -> a += 1);
        }
        Comparator<Map.Entry<Character, Integer>> comp = (e1, e2) -> {
            int a = e1.getValue() - e2.getValue();
            if (a != 0) {
                return a;
            } else {
                return e1.getKey() - e2.getKey();
            }
        };
        TreeSet<Map.Entry<Character, Integer>> treeSet =
                new TreeSet<>(comp);
        treeSet.addAll(hm.entrySet());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> e : treeSet) {
            sb.append(String.valueOf(e.getKey()).repeat(Math.max(0, e.getValue())));
        }
        return sb.toString();
    }
}
