package ch02.n2;

import java.util.Random;
import java.util.Scanner;

public class N2 {

    public static void main(String[] args) {


        Scanner n = new Scanner(System.in);
        int num = n.nextInt(); // меняет поля(setRadix) -> мутатор 4

        Random r = new Random();
        num = r.nextInt(); // accessor
    }
}
