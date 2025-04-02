package org.wigs.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Problem:
 * Simulate a real-time stock market system where producers generate stock prices and consumers process them.
 */
class StockProducer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public StockProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                queue.put(i);
                System.out.println("Produced: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class StockConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public StockConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            //For infinite time subscription to blocking queue;
//            while (true) {
            int i = 1;
            while (i <= 10) {
                Integer stock = queue.take();
                System.out.println("Consumed: " + stock);
                Thread.sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class StockMarket {
    public static void main(String[] args) {
        //Can use ConcurrentLinkedQueue also here if need high throughput,
        // but ConcurrentLinkedQueue don't have constructor with capacity like LinkedBlockingQueue;
        // ++ Use BlockingQueue for producer-consumer problems.
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        Thread producer = new Thread(new StockProducer(queue));
        Thread consumer = new Thread(new StockConsumer(queue));

        producer.start();
        consumer.start();
    }
}

