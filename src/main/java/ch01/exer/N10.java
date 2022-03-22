package ch01.exer;
import java.util.random.RandomGenerator;
public class N10 {
    public static void main(String[] args) {
        long num = RandomGenerator.getDefault().nextLong(4738381338321616896L,Long.MAX_VALUE);
        num = (long)Math.pow(36,12);
        System.out.println(Long.toString(num,10));
        System.out.println(Long.toString(num,10).length());
        System.out.println(Long.toString(num, 36));
        System.out.println(Long.toString(num, 36).length());
    }
}
