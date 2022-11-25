package ch6.n21;

import ch6.n20.N20;

import java.util.ArrayList;
import java.util.List;

public class N21 {
    public static void main(String[] args) {
        List<String>[] result = N21.construct(10);
        List<String> list = result[0];
        list.add("String");
    }
    private static <T> List<T>[] construct(int i) {
        ArrayList<T> list = new ArrayList<>();
        return N20.repeat(i, list);
    }
}
