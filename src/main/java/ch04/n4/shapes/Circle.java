package ch04.n4.shapes;

import ch04.n1.Point;
import ch04.n4.Shape;

public class Circle extends Shape implements Cloneable {
    private double radius;
    public Circle(Point point, double radius) {
        super(point);
        this.radius = radius;
    }


    @Override
    public Point getCenter() {
        return new Point(point.getX(), point.getY());
    }

    @Override
    public Circle clone() {
        Circle clone = (Circle) super.clone();
        return clone;
    }
}
