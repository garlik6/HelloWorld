package ch6.n1;

import java.util.ArrayList;
import java.util.List;

public class N1 {
    public static void main(String[] args) {
        List<?>[] lists = new List<?>[1];
        lists[0] = List.of("1");
        List<?> list = lists[0];

        List<Object> list1 = new ArrayList<>();
        list1.add(new String("dlsfjsdh"));

        Stack<Number> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3.0);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
