package ru.gb.gerasimenko;

public class Volatile {
    private volatile long v; //если убрать volatile то выводы будут 0 или -1, если volatile всегда будет -1

    public void method() {
        this.v = -1;
    }

    public void method1() {
        System.out.println(v);
    }

    public static void main(String[] args) throws InterruptedException {
        final Volatile vol = new Volatile();
        final Thread t1 = new Thread(()->vol.method());
        final Thread t2 = new Thread(()-> vol.method1());

        t1.start();
        t2.start();
    }
}
