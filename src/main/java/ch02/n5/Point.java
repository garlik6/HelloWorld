package ch02.n5;
/**
 * class that describes point on plane (immutable version)
 * @author Grigory Bashev
 * @version 1.1
 */
public final class Point {
    private final double x;
    private final double y;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * parametrised constructor
     * @param x
     * @param y
     */
    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    /**
     * empty constructor
     */
    public Point(){
        this(0,0);
    }
    /**
     * getter for x field
     * @return double value of x coordinate
     */
    public double getX() {
        return x;
    }
    /**
     * getter for y field
     * @return double value of y coordinate
     */
    public double getY() {
        return y;
    }
    /**
     * moves point by <code>x</code> and <code>y</code> for corresponding coordinates
     * @param x
     * @param y
     * @return reference to new object
     */
    public Point translate(double x, double y){
        double newX = this.x + x;
        double newY = this.y + y;
        return new Point(newX, newY);
    }
    /**
     * scales coordinates by factor of <em>factor</em>
     * @param factor
     * @return reference to new object
     */
    public Point scale(double factor){
        return new Point(this.x * factor, this.y * factor);
    }
}
