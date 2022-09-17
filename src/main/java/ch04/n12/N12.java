package ch04.n12;

import ch04.n1.Point;

public class N12 {
    public static void main(String[] args) {
        Point point = new Point(0, 1);

        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            point.getY();
        }
        System.out.println("Time:" + (System.currentTimeMillis() - time));

        try {
            long timeRef = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                point.getClass().getMethod("getY").invoke(point);
            }
            System.out.println("TimeReflection:" + (System.currentTimeMillis() - timeRef));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
