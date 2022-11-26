package ch6.n22;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class N22 {

    public static <V, T extends Throwable> V doWork(Callable<V> c, Function<String, T> constr, String msg) throws T {
        try {
            return c.call();
        } catch (Throwable realEx) {
            T ex = constr.apply(msg);
            ex.initCause(realEx);
            throw ex;
        }
    }

    public static void main(String[] args) throws Exception {
        doWork(() -> {
                    throw new RuntimeException("alshflsjdh");
                },
                Exception::new,
                "ldsjflsdkjflk");
    }
}
