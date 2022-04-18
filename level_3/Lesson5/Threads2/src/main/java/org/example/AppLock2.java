package org.example;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AppLock2 {
    public static void main(String[] args) {
        // has two locks read and write
        // ReadLock - могут взять любое кол-во lock-ов на чтение если не занят lock на запись
        // WriteLock - один поток может лок на запись если нет потоков которые читаю
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()-> {
                lock.readLock().tryLock();
                try {
                    System.out.println("Start reading " + finalI);
                    Thread.sleep(1000);
                    System.out.println("End of reading " + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.readLock().unlock();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()-> {
                lock.writeLock().lock();
                try {
                    System.out.println("\tStart writing " + finalI);
                    Thread.sleep(1000);
                    System.out.println("\tEnd of writing " + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.writeLock().unlock();
                }
            }).start();
        }
    }
}
