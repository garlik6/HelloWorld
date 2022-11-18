package ch6.n2;

import java.util.Arrays;
import java.util.EmptyStackException;

public class OStack {
//    Both techniques for eliminating the generic array creation have their
//    adherents. The first is more readable: the array is declared to be of type E[],
//    clearly indicating that it contains only E instances. It is also more concise: in a
//    typical generic class, you read from the array at many points in the code; the first
//    technique requires only a single cast (where the array is created), while the
//    second requires a separate cast each time an array element is read. Thus, the first
//    technique is preferable and more commonly used in practice. It does, however,
//    cause heap pollution (Item 32): the runtime type of the array does not match its
//    compile-time type (unless E happens to be Object). This makes some
//    programmers sufficiently queasy that they opt for the second technique, though
//    the heap pollution is harmless in this situation.
    public class Stack<E> {
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;
        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }
        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }
        public E pop() {
            if (size == 0)
                throw new EmptyStackException();
            // push requires elements to be of type E, so cast is correct
            @SuppressWarnings("unchecked")
            E result = (E) elements[--size];
            elements[size] = null; // Eliminate obsolete reference
            return result;
        }
        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size)
                elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
