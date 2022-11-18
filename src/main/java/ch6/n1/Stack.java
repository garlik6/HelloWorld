package ch6.n1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Stack<E> {
    private final ArrayList<E> arrayList = new ArrayList<>();
    private int size = 0;

    public void push(E element) {
        size++;
        arrayList.add(element);
    }

    private static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> newSet = new HashSet<>();
        newSet.addAll(s1);
        newSet.addAll(s2);
        return newSet;
    }

    private static <E> Set<? super E> addAllFromFirstToSecond(Set<? extends E> s1, Set<? super E> s2) {
        s2.addAll(s1);
        return s2;
    }

    public static void main(String[] args) {
        Set<Integer> integers = Set.of(1,2,3);
        Set<Number> doubles = Set.of(1.0,2.0);
        Set<? super Number> set = addAllFromFirstToSecond(integers,doubles);
    }
    public E pop() {
        if(size == 0)
            return null;
        return arrayList.remove(--size);
    }
}
