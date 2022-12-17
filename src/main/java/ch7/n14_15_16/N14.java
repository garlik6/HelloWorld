package ch7.n14_15_16;

import ch7.n13.Cash;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

public class N14 {

    public static List<Integer> getIntegerList(IntFunction<Integer> f) {

        Cash<Integer,Integer> cash = new Cash<>(10);

        return Collections.unmodifiableList(
                new AbstractList<>() {
                    @Override
                    public Integer get(int index) {
                        if (cash.containsKey(index)){
                            return cash.get(index);
                        } else {
                            Integer result = f.apply(index);
                            cash.put(index,result);
                            return result;
                        }
                    }
                    @Override
                    public int size() {
                        throw new UnsupportedOperationException();
                    }
                }
        );
    }
}
