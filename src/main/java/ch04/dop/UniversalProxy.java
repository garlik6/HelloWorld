package ch04.dop;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UniversalProxy {

    public static Object proxyLog(Object o) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return new ByteBuddy()
                .subclass(o.getClass())
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of((Object proxy, Method m, Object[] args) -> {
                    Object result = m.invoke(o, args);
                    System.out.println(o + "." + m.getName() + "(" + Arrays.toString(args) + ")" + "=" + result);
                    return result;
                }))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .getDeclaredConstructor().newInstance();
    }
}