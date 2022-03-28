package ch03.n1;

public interface Person {
    String getName();
    default int getId() { return 0; }
}
