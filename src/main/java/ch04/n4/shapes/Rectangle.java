package ch04.n4.shapes;

import ch04.n1.Point;
import ch04.n4.Shape;

public class Rectangle extends Shape implements Cloneable {

    private double width;
    private double height;

    public Rectangle(Point topLeft, double width, double height) {
        super(topLeft);
        this.width = width;
        this.height = height;
    }

    @Override
    public Point getCenter() {
        return new Point(point.getX() + width, point.getY() - height);
    }

    @Override
    public Rectangle clone() {
        Rectangle clone = (Rectangle) super.clone();
        return clone;
    }
}
