package ch5.n8;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    private final ReentrantLock lock;

    public Lock(ReentrantLock lock) {
        this.lock = lock;
    }

    public AutoCloseable makeAutoCloseable() {
        lock.lock();
        return () -> {
            if (lock.isLocked())
                lock.unlock();
        };
    }

    public void unlock() {
        lock.unlock();
    }

    public void lock() {
        lock.lock();
    }
}

