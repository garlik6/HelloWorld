package ch7.n3;

import java.util.HashSet;
import java.util.Set;

public class N3 {

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.addAll(set2);

        Set<Integer> set3 = new HashSet<>();
        Set<Integer> set4 = new HashSet<>();

        Set<Integer> set5 = new HashSet<>();
        Set<Integer> set6 = new HashSet<>();
        set5.removeAll(set6);

        Set<Integer> set7 = new HashSet<>();
        Set<Integer> set8 = new HashSet<>();
        set7.removeAll(set8);
    }
}
