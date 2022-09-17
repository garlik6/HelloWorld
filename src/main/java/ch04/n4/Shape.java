package ch04.n4;

import ch04.n1.Point;

abstract public class Shape implements Cloneable{
    protected Point point;

    public Shape(Point point) {
        this.point = new Point(point.getX(), point.getY());
    }

    public void moveBy (double dx, double dy){
        point.changeX(dx);
        point.changeY(dy);
    }

    public abstract Point getCenter();

    @Override
    public Shape clone() {
        try {
            Shape clone = (Shape) super.clone();
            clone.point = new Point(point.getX(), point.getY());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
