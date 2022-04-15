package ch03.n4;

import java.util.Random;

public interface IntSequence {
    default boolean hasNext() {return  true;}

    int next();

    static IntSequence constant(int number){
        return () -> number;
    }

    static IntSequence of(int first, int... values) {

        int number = first * (int) Math.pow(10, values.length);
        for (int i = 0; i < values.length - 1; i++) {
            number += first + values[i] * (int) Math.pow(10, values.length - i - 1);
        }
        int finalNumber = number;
        return new IntSequence() {
            private int number = finalNumber;

            @Override
            public boolean hasNext() {
                return number != 0;
            }

            @Override
            public int next() {
                int result = number % 10;
                number /= 10;
                return result;
            }

            public int rest() {
                return number;
            }
        };
    }

    class RandomSequence implements IntSequence {
        int low;
        int high;
        private static final Random generator = new Random();

        public RandomSequence(int low, int high){
            this.low = low;
            this.high = high;
        }

        public int next() {
            return low + generator.nextInt(high - low + 1);
        }

        public boolean hasNext() { return true; }
    }
}
