package ch6.dop;

import ch6.dop.classesToInjectTo.InjectClass2;
import ch6.dop.classesToInjectTo.InjectPoint.InjectPoint;
import ch6.dop.container.StringCont;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClassSearcherTest {

    @Test
    void listClassesToInject1() throws NoSuchFieldException, IllegalAccessException {
        InjectClass2 injectClass2 = new InjectClass2();
        InjectSearcher.listClassesToInject(injectClass2, "field1");
    }

    @Test
    void listClassesToInject2() throws NoSuchFieldException, IllegalAccessException {
        InjectClass2 injectClass2 = new InjectClass2();
        InjectSearcher.listClassesToInject(injectClass2, "field2");
    }
}