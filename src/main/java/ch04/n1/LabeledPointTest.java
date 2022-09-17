package ch04.n1;

import ch04.n1.LabeledPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabeledPointTest {
    LabeledPoint labeledPoint = new LabeledPoint("a",1,1);

    @Test
    void getLabel() {
        assertEquals("a", labeledPoint.getLabel());
        Point p = new LabeledPoint("a",1,1);
        System.out.println(labeledPoint.x);
    }


}