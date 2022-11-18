package ch6.n12;

public class SuperParent implements Comparable<SuperParent>{
    int i;

    public SuperParent(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(SuperParent o) {
        return i - o.i;
    }
}
