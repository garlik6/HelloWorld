package ch04.n13;
import java.lang.reflect.Method;
import java.util.function.DoubleFunction;

public class N13 {
    public static void main(String[] args) {
        try {
            print1(Math.class.getMethod("sqrt", double.class), 9, 16, 1);
            print2(Double::toHexString, 9, 16, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void print1(Method m, double lowerLimit, double upperLimit, double step) throws Exception {
        System.out.println(m.getDeclaringClass().getSimpleName() + "." + m.getName() + "(double obj)");
        for (double i = lowerLimit; i <= upperLimit; i += step) {
            System.out.println(i + " -> " + m.invoke(null, i));
        }
    }

    public static void print2(DoubleFunction<Object> f, double lowerLimit, double upperLimit, double step) throws Exception {
        for (double i = lowerLimit; i <= upperLimit; i += step) {
            System.out.println(i + " -> " + f.apply(i));
        }
    }
}
