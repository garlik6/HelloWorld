package ch03.n15;

import ch03.FilteringIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ch03.FilteringIterator.fromIterator;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>() {{
            add("abcd");
            add("acde");
            add("ab");
            add("a");
            add("tt");
            add("tttttbtttttttt");
            add("tttttt");
            add("b");
        }};


        FilteringIterator<String> fIterator = FilteringIterator.fromIterator(strings.iterator());


        while (fIterator.hasNext()) {
            System.out.println(fIterator.next());
        }
        System.out.println();

        fIterator = FilteringIterator.fromIterator(strings.iterator());

        fIterator = fIterator.filter((String str) -> str.length() > 2);

        while (fIterator.hasNext()) {
            System.out.println(fIterator.next());
        }

        System.out.println();

        fIterator = FilteringIterator.fromIterator(strings.iterator());

        fIterator = fIterator.filter((String str) -> str.contains("b"));

        while (fIterator.hasNext()) {
            System.out.println(fIterator.next());
        }
        System.out.println();


        fIterator = FilteringIterator.fromIterator(strings.iterator());

        fIterator = fIterator.filter((String str) -> str.contains("b"));

        fIterator.hasNext();
        fIterator.hasNext();
        fIterator.hasNext();

        System.out.println(fIterator.next());
        System.out.println(fIterator.next());
        fIterator.hasNext();
        fIterator.hasNext();
        fIterator.hasNext();
        System.out.println(fIterator.next());
        fIterator.hasNext();
        fIterator.hasNext();
        fIterator.hasNext();
        System.out.println(fIterator.next());
        System.out.println(fIterator.next());

    }
}
