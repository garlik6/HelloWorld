package ch04.dop.clasex;

public interface C {
    default int sum(int a, int b) {
        return a + b;
    }
}
