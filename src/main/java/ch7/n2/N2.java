package ch7.n2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class N2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("eeeeee");
        System.out.println(strings);
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).toUpperCase());
        }
        System.out.println(strings);
        strings.add("a;sdjfl;sdjf");
        System.out.println(strings);
        ListIterator<String> stringIterator = strings.listIterator();
        String currentString;
        while (stringIterator.hasNext()) {
            currentString = stringIterator.next();
            stringIterator.set(currentString.toUpperCase());
        }
        System.out.println(strings);
        strings.add("a;sdjfl;sdjf");
        System.out.println(strings);
        strings.replaceAll(String::toUpperCase);
        System.out.println(strings);
    }
}
