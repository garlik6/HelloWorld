package ch02.n10;
import java.util.ArrayList;
import java.util.Random;
public class RandomElement {

    private static final Random generator = new Random();

    public static int getRandomElement(int[] array)
    {
        int index = generator.nextInt(array.length - 1);
        return array[index];
    }
    public static int getRandomElement(ArrayList<Integer> arrayList)
    {
        int index = generator.nextInt(arrayList.size() - 1);
        return arrayList.get(index);
    }
}
