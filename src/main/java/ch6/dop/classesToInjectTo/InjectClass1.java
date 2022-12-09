package ch6.dop.classesToInjectTo;

import ch6.dop.container.Cont;

public class InjectClass1<T extends Comparable<? super T>>{
    Cont<T> field;
}
