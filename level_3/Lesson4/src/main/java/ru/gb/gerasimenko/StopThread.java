package ru.gb.gerasimenko;

public class StopThread {

    public static void main(String[] args) {
        final Thread thread = new Thread(()->{
          while (!Thread.currentThread().isInterrupted()){
              try {
                  Thread.sleep(10000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
                  Thread.currentThread().interrupt(); //без этой строчки цикл = вечный
                  /*
                  зачем Thread.currentThread().interrupt() затем что когда поток thread засыпает а
                  в основном потоке вызывается thread.interrupt(); и выбрасывается исключение
                  то флаг interrupted сново выставляется в false
                  поэтому необходимо вызвать Thread.currentThread().interrupt();
                   */
              }
          }
            System.out.println("Close resources.");
        });
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
