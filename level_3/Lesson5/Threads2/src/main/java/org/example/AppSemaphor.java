package org.example;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class AppSemaphor {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(4);
        Runnable r = () -> {
            try  {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " get semaphore");
                Thread.sleep(new Random().nextInt(1000));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
                System.out.println("\t\t" + Thread.currentThread().getName() + " leave semaphore");
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}
