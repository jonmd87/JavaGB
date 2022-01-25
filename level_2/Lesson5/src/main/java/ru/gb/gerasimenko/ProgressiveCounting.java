package ru.gb.gerasimenko;

public class ProgressiveCounting {
    private int quantity;
    private float arr[];
    private MyThread threads[];

    public ProgressiveCounting(float[] arr, int quantity) {
        this.quantity = quantity;
        this.arr = new float[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, this.arr.length);
    }

    public void createThreads() {
        this.threads = new MyThread[quantity];
        int lenght = this.arr.length / quantity;
        int start = 0;
        for (int i = 0; i < quantity; i++) {
            if (i == quantity - 1) {
                lenght = this.arr.length - start;
            }
            float[] temp = new float[lenght];
            System.arraycopy(this.arr, start, temp, 0, lenght);
            start += lenght;
            threads[i] = new MyThread(temp);
        }
    }

    public void startAllThreads() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }

    public void collectArray() {
        int start = 0;
        for (int i = 0; i < this.threads.length; i++) {
            System.arraycopy(threads[i].getArray(), 0, this.arr, start, threads[i].getArray().length);
            start += threads[i].getArray().length;
        }
    }
}
