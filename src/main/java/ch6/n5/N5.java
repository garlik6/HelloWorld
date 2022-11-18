package ch6.n5;

import ch6.n7.Pair;

import java.util.ArrayList;

public class N5 {
///home/grigory/IdeaProjects/HelloWorld/src/main/java/ch6/n5/N5.java:13:34
//java: incompatible types: inference variable T has incompatible bounds
//    upper bounds: java.lang.Double,java.lang.Object
//    lower bounds: java.lang.Integer,java.lang.Double

    public static <T> T[] swap(int i, int j, T... values) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        return values;
    }

    public static <E extends Comparable<? super E>> Pair<E> firstLast(ArrayList<? extends E> arrayList) {
        return new Pair<>(arrayList.get(0), arrayList.get(arrayList.size() - 1));
    }

    public static <E extends Comparable<? super E>> E max(ArrayList<? extends E> arrayList) {
        E max = arrayList.get(0);
        for (E e : arrayList) {
            if (e.compareTo(max) > 0) {
                max = e;
            }
        }
        return max;
    }

    public static <E extends Comparable<? super E>> E min(ArrayList<? extends E> arrayList) {
        E min = arrayList.get(0);
        for (E e : arrayList) {
            if (e.compareTo(min) < 0) {
                min = e;
            }
        }
        return min;
    }

    public static <E extends Comparable<? super E>> Pair<E> minMax(ArrayList<? extends E> arrayList) {
        return new Pair<>(min(arrayList), max(arrayList));
    }

    public static void main(String[] args) {
        Double[] result = N5.swap(0, 1, 1.5, 2.0, 3.0);
    }
}
