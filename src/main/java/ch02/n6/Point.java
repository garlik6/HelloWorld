package ch02.n6;


/**
 * class that describes point on plane (mutable version)
 * @author Grigory Bashev
 * @version 1.1
 */
public class Point {
    private double x;
    private double y;

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

    public Point(Object[] objects) {
        this.x = (int) objects[0];
        this.y = (int) objects[1];
    }


    /**
     * getter for x field
     * @return double value of x coordinate
     */
    public double getX()   {
        return x;

    }
    /**
     * getter for y field
     * @return double value of y coordinate
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * moves point by <code>x</code> and <code>y</code> for corresponding coordinates
     * @param x
     * @param y
     * @return reference to initial mutated object
     */
    public Point translate(double x, double y){
        this.x+=x;
        this.y+=y;
        return this;
    }

    /**
     * scales coordinates by factor of <code>factor</code>
     * @param factor
     * @return reference to initial mutated object
     */
    public Point scale(double factor){
        this.x*=factor;
        this.y*=factor;
        return this;
    }
}
