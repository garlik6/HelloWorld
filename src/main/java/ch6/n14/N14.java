package ch6.n14;

import java.util.ArrayList;

public class N14 {
    public static void closeAll(ArrayList<? extends AutoCloseable> elems)
            throws Exception {
        Exception exception = null;
        for (AutoCloseable elem : elems) {
            try {
                elem.close();
            } catch (Exception e) {
                if (exception != null) {
                    e.addSuppressed(exception);
                }
                exception = e;
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    public static void main(String[] args) throws Exception {
        AutoCloseable a = () -> {
            System.out.println("1111");
            throw new RuntimeException("message");
        };
        ArrayList<AutoCloseable> list = new ArrayList<>();
        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);
        closeAll(list);
    }
}
