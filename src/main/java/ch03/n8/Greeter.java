package ch03.n8;

public class Greeter implements Runnable {
    private int n;
    private String target;

    public Greeter(int n, String target){
        this.n = n;
        this.target = target;
    }

    public static void runTogether(Runnable... tasks){
        for (Runnable task:tasks
             ) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public static void runInOrder(Runnable... tasks){
        for (Runnable task:tasks
        ) {
            Thread thread = new Thread(task);
            thread.run();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.println("Hello " + target);
        }
    }
}
