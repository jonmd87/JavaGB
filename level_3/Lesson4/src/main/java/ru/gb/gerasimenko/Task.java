package ru.gb.gerasimenko;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {
    private final Object monitor = new Object();
    private volatile char letter = 'A';

    public static void main(String[] args) {
        Task task = new Task();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            char temp = (char) ('A' + i);  // не взял переменную letter потому что в некоторых случаях letter успевает измениться
            service.execute(() -> {
                task.printLetter(temp);
            });
        }
        service.shutdown();
    }

    public void printLetter(Character ch) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != ch) {
                        monitor.wait();
                    }
                    System.out.print(ch);
                    increaseLetter();
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void increaseLetter() {
        if (letter == 'C') {
            letter = 'A';
        } else {
            letter++;
        }
    }
}
