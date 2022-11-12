package ch04.dop2;

import ch02.n6.Point;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        int x = 1;
        String s = "aaaa";
        String[] sa = new String[]{"llll","llllll"};
        Object[] arguments = new Object[]{x, s, sa};

        A a =  objectMapper.map(arguments, A::new);
        System.out.print(a);

    }
}
