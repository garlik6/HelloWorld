package ch7.n5;

import java.util.*;

public class N5 {
    public static void swap(List<?> list, int i, int j) {
        if (list instanceof RandomAccess) {
            Collections.swap(list,i,j);
        } else {
            swapHelper(list,i,j);
        }
    }
    private static <T> void swapHelper(List<T> list, int i, int j) {
        int min = Math.min(i,j);
        int max = Math.max(i,j);
        ListIterator<T> iterator = list.listIterator(min);
        ListIterator<T> iterator1 = list.listIterator(max);
        T element = iterator.next();
        T element1 = iterator1.next();
        iterator.set(element1);
        iterator1.set(element);
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        swap(list,1,3);
        System.out.println(list);
    }
}
