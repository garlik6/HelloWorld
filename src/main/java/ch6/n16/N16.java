package ch6.n16;

import java.util.ArrayList;
import java.util.List;

public class N16 {
    static void foo(List<? extends Animal> list) {
        int i = list.size();
        Animal a = list.get(0);
    }

    public static void main(String[] args) {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat());
        foo(list);
    }
}
