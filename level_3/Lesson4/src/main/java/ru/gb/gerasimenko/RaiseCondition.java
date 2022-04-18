package ru.gb.gerasimenko;

/*
что б не было raiseCondition используй synchronized а так же monitor
Object monitor = new Object();
и его также можно синхронизировать
 */

public class RaiseCondition {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        final Runnable runnable = () -> {
            for (int i = 0; i < 500000; i++) {
                counter.incrementCounter();
            }
        };
        final Thread thread = new Thread(runnable);
        final Thread thread1 = new Thread(runnable);
        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCount()); // without synchronized здесь иногда будет 1000000 чазе будет меньщее число
    }
}
    class Counter {
        private int count;

        public int getCount() {
            return count;
        }

        public synchronized void incrementCounter() {
            this.count++;
        }
    }

