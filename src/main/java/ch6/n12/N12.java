package ch6.n12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class N12 {

    public static <T> void minMax(List<? extends T> elements, Comparator<? super T> comp, List<? super T> result) {
        T max = elements.get(0);
        T min = elements.get(0);
        for (T t : elements) {
            if (comp.compare(t, max) > 0) {
                max = t;
            } else if (comp.compare(t, min) < 0) {
                min = t;
            }
        }
        result.add(min);
        result.add(max);
    }

    public static <T> void maxMin(List<T> elements,
                                  Comparator<? super T> comp, List<? super T> result) {
        minMax(elements, comp, result);
        swapHelper(result, 0, 1);
    }

    private static <T> void swapHelper(List<T> result, int i, int i1) {
        result.set(i, result.set(i, result.get(i1)));
    }

    public static void main(String[] args) {
        List<Parent> parents = List.of(new Parent(2), new Parent(1));
        List<SuperParent> superParents = new ArrayList<>();
        minMax(parents, SuperParent::compareTo, superParents);

//        List<Parent> intList = new ArrayList<>();
//        List<? super Child1> unknownTypeList = intList;
//        unknownTypeList.add(new SuperParent(5)); // doesn't compile, now you should see why
    }
}
