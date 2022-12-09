package ch6.dop.classesToInjectTo;

import ch6.dop.container.Cont;

public class InjectClass2 {
    Cont<? extends Number> field1;
    Cont<? super Integer> field2;
    Cont<?> field3;

    public void setField1(Cont<? extends Number> field1) {
        this.field1 = field1;
    }
    public void setField2(Cont<? super Integer> field2) {
        this.field2 = field2;
    }
    public void setField3(Cont<?> field3) {
        this.field3 = field3;
    }
}
