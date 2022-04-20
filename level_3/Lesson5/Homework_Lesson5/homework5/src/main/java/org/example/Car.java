package org.example;

import java.util.concurrent.*;

public class Car implements Runnable {
    static {
        CARS_COUNT = 0;
    }
    private CountDownLatch cdl;
    private CyclicBarrier barrier;
    private Semaphore semaphore;

    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private ArrayBlockingQueue<Car> carsArray;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdl, CyclicBarrier barrier, Semaphore semaphore, ArrayBlockingQueue<Car> carsArray) {
        this.race = race;
        this.speed = speed;
        this.cdl = cdl;
        this.barrier = barrier;
        this.semaphore = semaphore;
        this.carsArray = carsArray;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer count = 0;  // My additional variable
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            count++;
            if (count == this.race.getNumberOfTracks()) {
                try {
                    carsArray.put(this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}