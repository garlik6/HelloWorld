package ch04.n8;

import ch02.n1617.Queue;
import ch04.n1.Point;

import java.util.LinkedList;

public class Main {
        public static void main(String[] args) {

            Class<?>[] objects = {
                    String[].class,
                    LinkedList.class,
                    Object.class,
                    Queue.Iterator.class,
                    int.class,
                    Point.class,
                    Integer.class
            };


            for (Class<?> obj : objects) {
                System.out.println("--------------> Name: " + obj.getName());
                printInfo(obj);
            }
        }

        private static void printInfo(Class<?> someClass) {
            System.out.println(
                    "toString:  " + someClass.toString() + '\n'
                            + "toGenericString:  " + someClass.toGenericString() + '\n'
                            + "descriptorString:  " + someClass.descriptorString() + '\n'
                            + "getCanonicalName:  " + someClass.getCanonicalName() + '\n'
                            + "getSimpleName:  " + someClass.getSimpleName() + '\n'
                            + "getTypeName:  " + someClass.getTypeName() + '\n'
            );
        }
}
