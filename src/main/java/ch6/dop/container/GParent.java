package ch6.dop.container;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public class GParent<G> {
    public G gfield;

    public static void main(String[] args) throws NoSuchFieldException {
        GParent<Integer> g = new GParent<>();
        System.out.println(Arrays.toString(((ParameterizedType) g.getClass().getDeclaredField("gfield").getGenericType()).getActualTypeArguments()));
    }
}
