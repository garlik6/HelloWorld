package ch6.n15;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Mapper {
    public static <T,R> ArrayList<R> map(ArrayList<? extends T> list, Function<? super T,? extends R> function) {
       ArrayList<R> result = new ArrayList<>();
       for(T t: list){
           result.add(function.apply(t));
       }
       return result;
    }

    public static void main(String[] args) {
        Function<Object, Integer> f = Object::hashCode;
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        ArrayList<Number> a = map(l, f);
    }
}
