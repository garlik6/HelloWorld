package ch7.n17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N17 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List list1 = (List) list;
//        list1.add(22.0);
//        // a lot of code...
//        String str = list.get(0);

        List<String> list2 = Collections.checkedList(new ArrayList<>(), String.class);
        List list3 = (List) list2;
        list3.add(22.0);
//        List<String> list = Collections.EMPTY_LIST;
        List<String> list = Collections.emptyList();
        System.out.println(list);
    }
}
