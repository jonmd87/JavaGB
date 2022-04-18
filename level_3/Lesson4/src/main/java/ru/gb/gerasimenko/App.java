package ru.gb.gerasimenko;

public class App {
    private static int number = 0;
    private static volatile boolean ready = false; //смотри ответ 1


    /*
    Что произойдет в классе T
    1Вариант: Запустится поток/крутится цикл while/затем в основном потоке переменным присвоятся новые значения
                и напечатается 42 программа заверщится
    2Вариант: Запустится поток Т затем в основном потоке переменная ready получит новое значение а переменная number
                не успеет получить новое значение и напечатается 0

     */
    static class T extends Thread {
        @Override
        public void run() {
            while (!ready) {
                ;
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T().start();
        number = 42;
        Thread.sleep(10);  // происходит зависание программы смотри ответ 1
        ready = true;
    }
}

/*
1 Потому-что поток Т создает(потоки имеют свою память) у себя в памяти копию переменной ready, и даже если в основном потоке
  происходит изменение переменной ready поток T это не обнаружит и цикл будет бесконечно крутится.
  как решить эту проблему? Необходимо переменую ready обьявить volatile
2 volatile запрешает кэширование в потоке, запрешает менять порядок инструкций google->[JavaMemoryModel, happens before]
    https://habr.com/ru/post/510454/ -->JMM
    https://habr.com/ru/post/133981/ -->happens before
 */
