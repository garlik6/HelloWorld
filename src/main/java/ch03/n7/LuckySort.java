package ch03.n7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LuckySort {
    public static int luckySort(ArrayList<String> strings, Comparator<String> comp)
    {
        int i = 0;
        while (!isSorted(strings, comp)) {
            Collections.shuffle(strings);
            i++;
        }
        return i;
    }

    private static boolean isSorted(ArrayList<String> strings, Comparator<String> comp){
        for (int i = 0; i < strings.size() - 1; i++) {
            if(comp.compare(strings.get(i), strings.get(i + 1)) <= 0){
                return false;
            }
        }
        return true;
    }
}
