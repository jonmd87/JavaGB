package org.example;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerSample {
    public static void main(String[] args) {
        final ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<>(8);
        new Thread(() -> {
            final Character ch = 'A';
           final Producer producer = new Producer(strings);
           for (int i = 0; i < 12; i++) {
               producer.put(Character.toString(ch + i));
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        }).start();

        new Thread(() -> {
            final Consumer consumer = new Consumer(strings);
            for (int i = 0; i < 12; i++) {
                consumer.get();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }



    static class Producer {
        private ArrayBlockingQueue<String> queue;

        public Producer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void put(String msg) {
            System.out.println("Producer put " + msg);
            try {
                this.queue.put(msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Consumer {
        private ArrayBlockingQueue<String> queue;

        public Consumer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        public String get() {
            try {
                final String s = this.queue.take();
                System.out.println("\t\tConsumer get " + s);
                return s;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
