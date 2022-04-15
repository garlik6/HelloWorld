package ch03.n13;

public class RunnableInOder {

    static public Runnable runRunnableInOrder(Runnable[] array){
        return () -> {
            for (Runnable a: array
                 ) {
                Thread thread = new Thread(a);
                thread.run();
            }
        };
    }
}
