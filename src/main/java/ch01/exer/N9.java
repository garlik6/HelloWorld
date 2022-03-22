package ch01.exer;

public class N9 {
    public static void main(String[] args) {
        String s = "HellWorld";
        String s1 = "World";
        String s2 = "World";
        String s3 = s.substring(3);
        String s4 = "Wo" + "rld";
        String s5 = "1234";
        System.out.printf("%b, %b, %b, %b, %b ",s1 == s2, s1.equals(s2), s1 == s3, s1 == s4, s5 == Integer.toString(1234,10));
        System.out.println(Integer.toString(1234,10).equals(s5));
    }
}
