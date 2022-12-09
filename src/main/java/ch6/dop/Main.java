package ch6.dop;

import ch6.dop.classesToInjectTo.InjectClass2;
import ch6.dop.classesToInjectTo.InjectPoint.InjectPoint;
import ch6.dop.container.IntCont;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        InjectPoint<Integer> injectPoint = new InjectPoint<>();
        injectPoint.setField(new IntCont());
//        TypeUtils.isAssignable()
    }
}
