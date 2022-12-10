package ch6.dop.classesToInjectTo;

import ch6.dop.container.Cont;
import ch6.dop.container.Generic;

public class InjectClass1  {
    Cont<? extends Comparable<String>>field1;
    public void setField2(Generic<? extends Number, ? super Integer> field2) {
        this.field2 = field2;
    }
    Generic<? extends Number, ? super Integer> field2;
    public void setField(Cont<? extends Comparable<String>> field) {
        this.field1 = field;
    }
}
