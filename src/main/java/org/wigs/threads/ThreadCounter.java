package org.wigs.threads;

class Counter {
    private int count = 1;  // Shared counter
    private int threadTurn = 0; // Controls which thread should execute
    private final int MAX_COUNT = 10; // Counter limit
    private final Object lock = new Object();

    public void printCounter(int threadId) {
        synchronized (lock) {
            while (count <= MAX_COUNT) {
                while (threadTurn != threadId) {
                    try {
                        lock.wait(); // Wait until it's this thread's turn
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count > MAX_COUNT) break; // Stop if counter exceeds max limit

                System.out.println("Thread-" + threadId + " -> " + count);
                count++;
                threadTurn = (threadTurn + 1) % 3; // Switch to next thread
                lock.notifyAll(); // Wake up all waiting threads
            }
        }
    }
}

public class ThreadCounter {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Three threads executing the counter in order
        Thread t1 = new Thread(() -> counter.printCounter(0));
        Thread t2 = new Thread(() -> counter.printCounter(1));
        Thread t3 = new Thread(() -> counter.printCounter(2));

        t1.start();
        t2.start();
        t3.start();
    }
}
