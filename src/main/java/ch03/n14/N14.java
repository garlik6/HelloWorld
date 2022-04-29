package ch03.n14;

import ch03.n1.Employee;
import ch03.n1.Measurable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class N14 {
    public static void main(String[] args) {
        Employee[] array = new Employee[10];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Employee(Integer.toString(i*generator.nextInt(100, 1000), 100), 100);
        }
        for (Measurable item : array){
            ((Employee) item).raiseSalary(generator.nextDouble(100));
        }


        Comparator<Employee> comp = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName);
        Arrays.sort(array,comp);
        System.out.println(Arrays.toString(array));

//        comp = Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary);
        comp.reversed();
        Arrays.sort(array,comp);
        System.out.println(Arrays.toString(array));

    }
}
