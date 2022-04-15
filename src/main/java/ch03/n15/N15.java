package ch03.n15;

import ch03.n4.IntSequence;

import java.util.Random;

public class N15 {

    public static IntSequence randomInts(int low, int high) {
        return new IntSequence.RandomSequence(low,high);
    }

    public static void main(String[] args) {
        IntSequence dieTosses = randomInts(1, 100);
        for (int i = 0; i < 100; i++) System.out.println(dieTosses.next());
    }
}
