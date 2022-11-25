package ch6.n18;

import java.util.function.IntFunction;

public class N18 {
    public static <T> T[] repeat(int n, T obj, IntFunction<T[]> constr) {
        T[] result = constr.apply(n);
        for (int i = 0; i < n; i++) result[i] = obj;
        return result;
    }

    public static void main(String[] args) {
        String[] array = repeat(1, "adsjfhakjlsfh", String[]::new);
        Integer[] array1 = repeat(1, 1, Integer[]::new);
    }
}
