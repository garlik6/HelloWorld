 package ch02.n4;

    public class N4 {
        void swap(int a, int b) {
            int temp = a;
            a = b;
            b = temp;
            // a and b are copies of the original values.
            // The changes we made here won't be visible to the caller.
        }

        class IntHolder {
            public int value = 0;
        }

        void swap(IntHolder a, IntHolder b) {
            // Although a and b are copies, they are copies *of a reference*.
            // That means they point at the same object as in the caller,
            // and changes made to the object will be visible in both places.
            int temp = a.value;
            a.value = b.value;
            b.value = temp;
        }
    }


