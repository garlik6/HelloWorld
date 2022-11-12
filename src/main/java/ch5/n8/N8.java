package ch5.n8;

import java.util.concurrent.locks.ReentrantLock;

public class N8 {

    public static void main(String[] args) {
        ReentrantLock rl = new ReentrantLock();
        Lock lock = new Lock(rl);
        Thread thread1 = new Thread(() -> printLock(lock, 1));
        Thread thread2 = new Thread(() -> printLock(lock, 0));
        thread1.start();
        thread2.start();

//        Thread thread1 = new Thread(N8::print);
//        Thread thread2 = new Thread(N8::print);
//        thread1.start();
//        thread2.start();
    }

    private static void printLock(Lock lock, int number) {
        try (AutoCloseable autoCloseableLock = lock.makeAutoCloseable()) {
            print(number);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(int number) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName() + " " + number);
        }
    }
}
