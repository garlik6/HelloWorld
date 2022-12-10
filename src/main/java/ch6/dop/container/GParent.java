package ch6.dop.container;

import ch03.n1.Employee;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public class GParent<G> {
    public G gfield;

    public static void main(String[] args) throws NoSuchFieldException {
        GParent<Integer> g = new GParent<>();
        System.out.println(Arrays.toString(((ParameterizedType) g.getClass().getDeclaredField("gfield").getGenericType()).getActualTypeArguments()));
//        String[] strings = new String[10];
//        Object[] objects = strings;
//        objects[0] = new Employee("dd",12);
    }
}
