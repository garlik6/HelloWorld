package ch6.n7;

public class Pair<E extends Comparable<? super E>> {
    private final E first;
    private final E second;

    public E getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

    public E max() {
        return first.compareTo(second) > 0 ? first : second;
    }

    public E min() {
        return first.compareTo(second) < 0 ? first : second;
    }

    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }
}
