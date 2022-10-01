package ch04.n10;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class N10 {
    public static void main(String[] args) {
        printMethods("[I"); // int[] class
    }

    public static void printMethods(String className) {
        Class<?> cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }
        while (cl != null) {
            for (Method m : cl.getDeclaredMethods()) {
                System.out.println(
                        Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getCanonicalName()
                                + " " + m.getName() + Arrays.toString(m.getParameters()));
            }
            cl = cl.getSuperclass();
        }
    }
}
