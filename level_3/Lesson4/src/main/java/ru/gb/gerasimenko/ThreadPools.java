package ru.gb.gerasimenko;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPools {
    public static void main(String[] args) {
        /*
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
         создает пул из десяти потоков и все задачи будут распеределены между этими 10ю потоками
        final ExecutorService executorService = Executors.newCashedThreadPool();
        создает пул потоков JM коплятор сам добавляет потоки по мере необходимости
        обязательно остановить все потоки созданные newCashedThreadPool
        executorService.shutdown();

         */
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(()-> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown(); // вызываем метод для закрытия потоков
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) { // ждем 1 сек если потоки не закрылись
                executorService.shutdownNow(); // закрываем принудительно все потоки(нет гарантии что они закроются правильно)
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class C implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
