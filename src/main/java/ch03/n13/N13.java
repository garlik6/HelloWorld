package ch03.n13;

import ch03.n8.Greeter;

public class N13 {
    public static void main(String[] args) {
        Runnable[] runnables = new Runnable[5];
        Runnable task1 = new Greeter(1, "User1");
        runnables[0] = task1;
        Runnable task2 = new Greeter(1, "there");
        runnables[1] = task2;
        Runnable task3 = new Greeter(1, "eeeeee");
        runnables[2] = task3;
        Runnable task4 = new Greeter(1, "ttttttt");
        runnables[3] = task4;
        Runnable task5 = new Greeter(1, "yyyyyyyyyy");
        runnables[4] = task5;

        Thread thread = new Thread(RunnableInOder.runRunnableInOrder(runnables));
        thread.start();

    }

}
