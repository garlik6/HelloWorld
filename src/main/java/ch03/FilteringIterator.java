package ch03;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public interface FilteringIterator<T> extends Iterator<T> {

    static <T> FilteringIterator<T> fromIterator(Iterator<T> iterator) {
        return new  FilteringIterator<T>() {
            T lastNode = null;
            boolean hasLastNode = false;
            @Override
            public FilteringIterator<T> filter(Predicate<T> predicate) {

                return fromIterator(new Iterator<T>() {
                    @Override
                    public boolean hasNext() {

                        if (hasLastNode) return true;

                        while (iterator.hasNext()) {
                            lastNode = iterator.next();
                            if (predicate.test(lastNode)) {
                                hasLastNode = true;
                                return true;}
                        }
                        return false;
                    }

                    @Override
                    public T next() {
                        if(hasLastNode) {
                            hasLastNode = false;
                            return lastNode;
                        } else
                        while (iterator.hasNext()) {
                            lastNode = iterator.next();
                            if (predicate.test(lastNode)) {
                                return lastNode;
                            }
                        }
                        throw new NoSuchElementException();
                    }
                });
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }

    FilteringIterator<T> filter(Predicate<T> predicate);
}
