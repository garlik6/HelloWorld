package ch6.dop;

import ch6.dop.classesToInjectTo.InjectClass1;
import ch6.dop.classesToInjectTo.InjectClass2;
import ch6.dop.classesToInjectTo.InjectPoint.InjectPoint;
import ch6.dop.container.Cont;
import ch6.dop.container.StringCont;
import org.junit.jupiter.api.Test;

class ClassSearcherTest {
    @Test
    void listClassesToInject() throws NoSuchFieldException {
        InjectPoint injectPoint = new InjectPoint();
        System.out.println(InjectSearcher.listClassesToInject(injectPoint, "field"));
    }
    @Test
    void listClassesToInject1() throws NoSuchFieldException {
        InjectClass2 injectClass2 = new InjectClass2();
        injectClass2.setField1(new Cont<Integer>());
        System.out.println(InjectSearcher.listClassesToInject(injectClass2, "field1"));
    }

    @Test
    void listClassesToInject2() throws NoSuchFieldException {
        InjectClass2 injectClass2 = new InjectClass2();
        injectClass2.setField2(new Cont<>());
        System.out.println(InjectSearcher.listClassesToInject(injectClass2, "field2"));
    }
    @Test
    void listClassesToInject4() throws NoSuchFieldException {
        InjectClass2 injectClass2 = new InjectClass2();
        injectClass2.setField2(new Cont<>());
        System.out.println(InjectSearcher.listClassesToInject(injectClass2, "field3"));
    }

    @Test
    void listClassesToInject3() throws NoSuchFieldException {
        InjectClass1 injectClass1 = new InjectClass1();
        injectClass1.setField(new StringCont());
        System.out.println(InjectSearcher.listClassesToInject(injectClass1, "field1"));
    }

    @Test
    void listClassesToInject6() throws NoSuchFieldException  {
        InjectClass1 injectClass1 = new InjectClass1();
        System.out.println(InjectSearcher.listClassesToInject(injectClass1, "field2"));
    }
    @Test
    void listClassesToInject8() throws NoSuchFieldException  {
        InjectClass2 injectClass2 = new InjectClass2();
        System.out.println(InjectSearcher.listClassesToInject(injectClass2, "employee"));
    }

    @Test
    void listClassesToInject7() throws NoSuchFieldException {
        InjectClass1 injectClass1 = new InjectClass1();
        System.out.println(InjectSearcher.listClassesToInject(injectClass1, "field2"));
    }
}