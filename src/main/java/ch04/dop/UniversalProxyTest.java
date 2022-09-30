package ch04.dop;
import ch04.dop.clasex.A;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;


class UniversalProxyTest {

    @Test
    void proxyLog() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        A a = new A();
        A proxy = (A) UniversalProxy.proxyLog(a);
        proxy.sum(1 ,1);
        proxy.method(1,1);
    }
}