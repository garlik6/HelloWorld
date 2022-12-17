package ch7.n1;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class N1 {
    public static void main(String[] args) {
        getPrimesUpTo(200);
        getPrimesUpToBitSet(200);
        getPrimesUpToBitSet(15);
        getPrimesUpToBitSet(13);
    }

    public static void getPrimesUpTo(int n) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            hashSet.add(i);
        }
        int s = 2;
        while (s * s < n) {
            for (int i = 0; i < n - s; i++) {
                hashSet.remove(s * (s + i));
            }
            s++;
            while (!hashSet.contains(s)) {
                s++;
            }
        }
        System.out.println(hashSet);
    }

    public static void getPrimesUpToBitSet(int n) {
        BitSet bitSet = new BitSet(n + 1);
        int s = 2;
        while (s * s < n) {
            for (int i = 0; s * (s + i) < n + 1; i++) {
                bitSet.set(s * (s + i), true);
            }
            s++;
            while (bitSet.get(s)) {
                s++;
            }
        }
        for (int i = 2; i <= n; i++) {
            bitSet.set(i, !bitSet.get(i));
        }
        System.out.println(bitSet);
    }
}