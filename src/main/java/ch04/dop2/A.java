package ch04.dop2;

import java.util.Arrays;

public class A {
    int a;
    String b;
    String[] c;

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + Arrays.toString(c) +
                '}';
    }

    public A(Object[] objects) {











        this.a = (int) objects[0];
        this.b = (String) objects[1];
        this.c = (String[]) objects[2];
    }
}
