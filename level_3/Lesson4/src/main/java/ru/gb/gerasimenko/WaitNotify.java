package ru.gb.gerasimenko;

public class WaitNotify {
    public static void main(String[] args) {
        Object monitor = new Object();

        new Thread(() ->{
            synchronized (monitor) {
                try {
                    System.out.println("one");
                    monitor.wait();
                    System.out.println("three");
                    monitor.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() ->{
            synchronized (monitor) {
                try {
                    System.out.println("two");
                    monitor.notify();
                    monitor.wait();
                    System.out.println("four");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
