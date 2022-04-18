package ru.gb.gerasimenko;

public class DeadLocks {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();

        DeadLock1 d1 = new DeadLock1(monitor1, monitor2);
        DeadLock2 d2 = new DeadLock2(monitor1, monitor2);

        d1.start();
        d2.start();

        try {
            d1.join();
            d2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");
    }
}

class DeadLock1 extends Thread {
    private final Object monitor1;
    private final Object monitor2;

    DeadLock1(Object monitor1, Object monitor2) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
    }

    @Override
    public void run() {
        synchronized (monitor1) {
            System.out.println("DeadLock2 working monitor1");
            synchronized (monitor2) {
                System.out.println("DeadLock2 working monitor2");
            }
        }
    }
}


class DeadLock2 extends Thread {
    private final Object monitor1;
    private final Object monitor2;

    DeadLock2(Object monitor1, Object monitor2) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
    }

    @Override
    public void run() {
        synchronized (monitor2) {
            System.out.println("DeadLock2 working monitor2");
            synchronized (monitor1) {
                System.out.println("DeadLock2 working monitor1");
            }
        }
    }
}