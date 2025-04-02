package org.wigs.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Problem:
 * Design a system where multiple threads can deposit and withdraw money from a shared bank account safely.
 * Ensure no race conditions occur.
 */
class BankAccount {
    private double balance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(double balance) {
        this.balance = balance;
        System.out.println("Initial balance : " + getBalance());
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
            System.out.println("Current balance : " + getBalance());
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
                System.out.println("Current balance : " + getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds!");
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(() -> {
            account.deposit(500);
            account.withdraw(700);
        }, "User1");

        Thread t2 = new Thread(() -> {
            account.deposit(300);
            account.withdraw(1000);
        }, "User2");

        t1.start();
        t2.start();
    }
}

