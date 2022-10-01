package ch04.dop1;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader cl1 = ClassLoader.getSystemClassLoader().loadClass("ch04.dop1.Person").getClassLoader();
        ClassLoader cl2  = Main.class.getClassLoader().loadClass("ch04.dop1.Person").getClassLoader();


        MyClassLoader classLoader1 = new MyClassLoader();

        Class<?> person1 = classLoader1.loadClass("ch04.dop1.Person");
        Class<?> person2 = ClassLoader.getSystemClassLoader().loadClass("ch04.dop1.Person");

        Object p1 = person1.getConstructor().newInstance();
        Object p2 = person2.getConstructor().newInstance();


        p1 = p2;
    }
}
