package ru.gb.gerasimenko;

// способы создания Threads
// Исключения довятся только в своем потоке

public class CreateThreads {
    public static void main(String[] args) {
        final T1 t1 = new T1();
        t1.start();
        final Thread t2 = new Thread(new T2());
        t2.start();

        // make anonymous class
        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3(anonymous) Do useful work");
            }
        });
        t3.start();

        // лямда
        final Thread t4 = new Thread(()-> System.out.println("T4(lambda) Do useful work"));
        t4.start();
    }
}
    class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("T1(Threads) Do useful work");
        }
    }

    class T2 implements Runnable {
        @Override
        public void run() {
            System.out.println("T2(Runnable) Do useful work");
        }
    }

