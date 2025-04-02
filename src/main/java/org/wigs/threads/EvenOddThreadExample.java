package org.wigs.threads;

class NumberPrinter {
    private int number = 1;
    private final int limit = 100;

    public synchronized void printEven() {
        while (number <= limit) {
            while (number % 2 != 0) { // Wait if the number is odd
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getId()+"-"+Thread.currentThread().getName()+" Even: " + number);
            number++;
            notify(); // Notify the other thread
        }
    }

    public synchronized void printOdd() {
        while (number <= limit) {
            while (number % 2 == 0) { // Wait if the number is even
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getId()+"-"+Thread.currentThread().getName()+" Odd: " + number);
            number++;
            notify(); // Notify the other thread
        }
    }
}

public class EvenOddThreadExample {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

        Thread evenThread = new Thread(printer::printEven);
        Thread oddThread = new Thread(printer::printOdd);

        evenThread.start();
        oddThread.start();
    }
}

