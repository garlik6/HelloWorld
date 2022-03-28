package ch01;

public class N4 {
    public static void main(String[] args) {
        double minPosDouble = Math.nextUp(0.0);
        double maxPosDouble = Double.MAX_VALUE;
        double theBiggestPosDouble = Double.POSITIVE_INFINITY;
        System.out.println("Min positive double value: " + minPosDouble);
        System.out.println("Max positive double value: " + maxPosDouble);
        System.out.println(theBiggestPosDouble > maxPosDouble);
    }
}
