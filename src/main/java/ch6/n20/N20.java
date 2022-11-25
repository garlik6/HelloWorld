package ch6.n20;

import java.lang.reflect.Array;

public class N20 {
    @SafeVarargs
    public static <T> T[] repeat(int n, T... objs) {
        T[] result;
        int length = objs.length;
        if (n == 1) {
            result = objs;
            return result;
        } else {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) Array.newInstance(objs.getClass().getComponentType(), n * length);
            result = newArray;
        }
        for (int i = 0; i < n * length; i++) {
            for (int j = 0; j < length; j++)
                result[i + j] = objs[j];
            i += length - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] array = repeat(4, 4, 4, 4);
        String[] array1 = repeat(1,"23234");
    }
}
