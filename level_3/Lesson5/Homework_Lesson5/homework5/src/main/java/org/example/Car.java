package org.example;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch cdl;
    private CyclicBarrier barrier;
    private Semaphore semaphore;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier barrier, CountDownLatch cdl, Semaphore semaphore) {
        this.race = race;
        this.speed = speed;
        this.cdl = cdl;
        this.barrier = barrier;
        this.semaphore = semaphore;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            barrier.await();
            System.out.println(this.name + " готов");
            cdl.countDown();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this, this.semaphore);
        }
    }
}