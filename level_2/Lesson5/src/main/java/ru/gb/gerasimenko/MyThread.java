package ru.gb.gerasimenko;

public class MyThread extends Thread{
    private float[] arr;

    public MyThread(float[] arr) {
        this.arr = new float[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
    }

    @Override
    public void run() {
        for (int i = 0; i < this.arr.length; i++) {
           this.arr[i] = (float)(this.arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public float[] getArray() {
        return this.arr;
    }
}
