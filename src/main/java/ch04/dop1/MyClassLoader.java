package ch04.dop1;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super();
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
