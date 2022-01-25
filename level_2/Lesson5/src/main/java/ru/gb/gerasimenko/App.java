package ru.gb.gerasimenko;

public class App
{
    static final int size = 10_000_000;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        fillArr();
        System.out.println("ONE THREAD");
        MyThread single = new MyThread(arr);
        long a = System.currentTimeMillis();
        try {
            single.run();
            single.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Time = " + (System.currentTimeMillis() - a));


        System.out.println("FEW THREADS");
        ProgressiveCounting progressiveCounting = new ProgressiveCounting(arr, 2); // можно менять переменную quantity
        a = System.currentTimeMillis();
        progressiveCounting.createThreads(); // создаем нити и массивы
        try {
            progressiveCounting.startAllThreads();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        progressiveCounting.collectArray();
        System.out.println("Time = " + (System.currentTimeMillis() - a));
    }


    private static void fillArr() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0F;
        }
    }
}
