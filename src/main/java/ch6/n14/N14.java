package ch6.n14;

import java.util.ArrayList;

public class N14 {
    public static void closeAll(ArrayList<? extends AutoCloseable> elems)
            throws CloseFailureException {
        Exception exception = null;
        for (AutoCloseable elem : elems) {
            try {
                elem.close();
            } catch (Exception e) {
                if (exception != null) {
                    exception.addSuppressed(e);
                } else {
                    exception = e;
                }
            }
        }
        if (exception != null) {
            throw new CloseFailureException("failed to close " +
                    (exception.getSuppressed().length + 1) +
                    " out of " +
                    elems.size() +
                    " elements ", exception);
        }
    }

    public static void main(String[] args) throws Exception {
        AutoCloseable a = () -> {
            System.out.println("1111");
        };
        ArrayList<AutoCloseable> list = new ArrayList<>();
        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);
        closeAll(list);
    }
}
