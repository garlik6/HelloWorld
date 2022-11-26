package ch6.n23;

import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class N23 {
    public static void main(String[] args) {
        Class<?> a = ArrayList.class;
        Type[] t = a.getTypeParameters();
    }
}
