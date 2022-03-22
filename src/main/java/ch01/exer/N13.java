package ch01.exer;
import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;
public class N13 {
    public static void main(String[] args) {
        int[] numbers = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49};
        int[] combination = new int[6];
        int number = 0;
        int randomNumber;
        Random generator = new Random();
        for(int i = 0; i < 6; i++)
        {
            do {
                randomNumber = generator.nextInt(49);
                number = numbers[randomNumber];
                numbers[randomNumber] = 0;

            } while (number == 0);
            combination[i] = number;
        }
        Arrays.sort(combination);
        System.out.println(Arrays.toString(combination));
    }

}
