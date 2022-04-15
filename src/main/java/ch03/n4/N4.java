package ch03.n4;

import java.lang.reflect.InvocationTargetException;

public class N4 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IntSequence number = IntSequence.of(1, 2, 3, 4, 5);
        System.out.println(number.getClass().getMethod("rest").invoke(number));//do not use
        IntSequence constNumber = IntSequence.constant(1);
        System.out.println(number.next());
        System.out.println(number.next());
        System.out.println(number.next());
        System.out.println(number.next());
        System.out.println(number.next());
        System.out.println(constNumber.next());
        System.out.println(constNumber.next());
        System.out.println(constNumber.next());
    }
}
