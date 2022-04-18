package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppLock {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    counter.inc();
                }
            }
        };
        final Thread t1 = new Thread(r);
        final Thread t2 = new Thread(r);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("counter.getCount() = "+ counter.getCount() );
    }
}

class Counter {
    int count = 0;
    Lock lock = new ReentrantLock();

    public void inc() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
