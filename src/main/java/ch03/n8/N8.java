package ch03.n8;public class N8 {
    public static void main(String[] args) {
        Runnable task1 = new Greeter(100, "User1");
        Runnable task2 = new Greeter(100, "there");


//        Thread thread1 = new Thread(task1);
//        Thread thread2 = new Thread(task2);
//        thread1.start();
//        thread2.start();
//
//
        Greeter.runTogether(task1, task2);


//        Greeter.runInOrder(task1,task2,task1,task2);
    }
}
