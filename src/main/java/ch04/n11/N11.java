package ch04.n11;

import java.lang.reflect.Field;

public class N11 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.System");
        Field field = clazz.getDeclaredField("out");
        System.out.println(field.get(null));
        field.getType().getMethod("println", String.class)
                .invoke(field.get(null), "Hello, World");
    }
}
