package ch03.n1;

public interface Identified {
    default int getId() { return Math.abs(hashCode()); }
}