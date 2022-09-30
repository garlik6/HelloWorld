package ch04.dop.example;

import ch04.dop.clasex.A;
import ch04.dop.clasex.B;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] values = new Object[1000];
        for (int i = 0; i < values.length; i++) {
            Object value = new A();
            values[i] = Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    value.getClass().getInterfaces(),
                    // Lambda expression for invocation handler
                    (Object proxy, Method m, Object[] margs) -> {
                        System.out.println(value + "." + m.getName() + Arrays.toString(margs));
                        return m.invoke(value, margs);
                    });
        }
        for (Object value : values
        ) {
            System.out.println(value.getClass().getMethod("sum").invoke(value));
        }
    }


}
