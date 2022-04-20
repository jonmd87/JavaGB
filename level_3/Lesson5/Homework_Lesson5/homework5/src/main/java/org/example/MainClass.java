package org.example;


import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);   // My additional variable
        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT); // My additional variable
        Semaphore semaphore = new Semaphore(CARS_COUNT / 2, true); // My additional variable
        ArrayBlockingQueue<Car> carsArray = new ArrayBlockingQueue<>(CARS_COUNT); // My additional variable

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdl, barrier, semaphore, carsArray);
        }

        System.out.println("\t\tВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            cdl.await();
            System.out.println("\t\tВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("\n!!!!!!!!!!!!!!!!! " + carsArray.take().getName() + " WIN\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (carsArray.size() != CARS_COUNT - 1);
        System.out.println("\t\tВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}