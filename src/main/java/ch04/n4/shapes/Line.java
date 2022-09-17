package ch04.n4.shapes;

import ch04.n1.Point;
import ch04.n4.Shape;

public class Line extends Shape implements Cloneable {
    private Point to;

    public Line(Point point, Point to) {
        super(point);
        this.to = to;
    }

    @Override
    public Point getCenter() {
        double x = (point.getX() + to.getX())/2;
        double y = (point.getY() + to.getY())/2;
        return new Point(x,y);
    }

    @Override
    public Line clone() {
        Line clone = (Line) super.clone();
        clone.to = new Point(to.getX(), to.getY());
        return clone;
    }
}
