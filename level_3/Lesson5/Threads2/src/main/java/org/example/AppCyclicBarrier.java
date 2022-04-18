package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AppCyclicBarrier {
    private static final int THREADS = 4;
    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(THREADS);

        for (int i = 0; i < THREADS; i++) {
            new Thread(() -> { //create new thread
                try {
                    System.out.println(Thread.currentThread().getName() + " await");
                    barrier.await(); // ЗДЕСЬ ПОТОКИ блокируются. как только заблокируется последний поток
                    // они одновременно запустятся
                    System.out.println(Thread.currentThread().getName() + " start");
                    barrier.await(); // опять соберутся все потоки в жтой точке
                    System.out.println(Thread.currentThread().getName() + " started 2");
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }
}
