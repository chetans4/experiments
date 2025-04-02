package org.wigs.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Problem:
 * Build a thread pool that processes stock market orders asynchronously.
 */
class OrderProcessor implements Runnable {
    private final String order;

    public OrderProcessor(String order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " processing: " + order);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.execute(new OrderProcessor("Order #" + i));
        }

        executor.shutdown();
    }
}

