package ch03.n1;
import java.util.Random;
public class N1 {
    public static void main(String[] args) {
        Employee[] array = new Employee[10];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Employee(Integer.toHexString(i*generator.nextInt(100, 1000)), 100);
        }
        for (Measurable item : array){
            ((Employee) item).raiseSalary(generator.nextDouble(100));
        }
        System.out.println(Measurable.average(array));
        System.out.println(((Employee) Measurable.largest(array)).getName());
    }
}

