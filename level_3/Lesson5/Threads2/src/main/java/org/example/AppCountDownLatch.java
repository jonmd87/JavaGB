package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class AppCountDownLatch {
    public static final int THREAD_COUNT = 4;

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT); // запуск 4х потоков
        System.out.println(" START");
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    System.out.println("\t\tstarted " + Thread.currentThread().getName());
                    Thread.sleep(new Random().nextInt(1000)); // типо какая то работа
                    countDownLatch.countDown(); // уменьшается количество потоков
                    System.out.println("\t\tfinished " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        try {
            countDownLatch.await(); // пока в for все потоки не закончат работу нижняя строка не напечатется
            System.out.println("END!!!!!!!!!!!!!!!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
