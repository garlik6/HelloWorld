package ch6.n22;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class N22 {

    public static <V, T extends Throwable> V doWork(Callable<V> c, Supplier<T> constr) throws T {
        try {
            return c.call();
        } catch (Throwable realEx) {
            T ex = constr.get();
            ex.initCause(realEx);
            throw ex;
        }
    }

    public static void main(String[] args) {
        doWork(() -> {throw new RuntimeException("alshflsjdh");}, RuntimeException::new);
    }
}
