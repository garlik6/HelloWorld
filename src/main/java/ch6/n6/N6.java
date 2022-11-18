package ch6.n6;

import java.util.List;

public class N6 {

    public static <E> List<E> addAllFromFirstToSecond(List<E> first, List<E> second) {
        first.addAll(second);
        return second;
    }

    public static <E> List<? super E> addAllFromFirstToSecond1(List<? extends E> first, List<? super E> second) {
        second.addAll(first);
        return second;
    }

    public static void main(String[] args) {
        List<Integer> first = List.of(1, 2, 3, 4);
        List<Integer> second = List.of(1, 2, 3, 4);
        List<Integer> integers = addAllFromFirstToSecond(first, second);
//        List<Number> i = addAllFromFirstToSecond(first, second);
        List<Number> third = List.of(1.0,2.2,3.4,4.6);
        List<? super Number> j = addAllFromFirstToSecond1(first, third);
    }
}
