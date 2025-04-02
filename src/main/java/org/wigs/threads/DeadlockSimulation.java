package org.wigs.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final String id;
    private double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}

class TransferTaskDeadLock implements Runnable {
    private final Account from;
    private final Account to;
    private final double amount;

    public TransferTaskDeadLock(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " locked " + from.getId());

            try { Thread.sleep(100); } catch (InterruptedException ignored) {}

            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " locked " + to.getId());
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    System.out.println("Transferred ₹" + amount + " from " + from.getId() + " to " + to.getId());
                } else {
                    System.out.println("Insufficient funds in " + from.getId());
                }
            }
        }
    }
}


class TransferTaskDeadLockFixed implements Runnable {
    private final Account from;
    private final Account to;
    private final double amount;

    public TransferTaskDeadLockFixed(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {

        Account first = from.getId().compareTo(to.getId()) < 0 ? from : to;
        System.out.println("first account : "+first);

        Account second = from.getId().compareTo(to.getId()) < 0 ? to : from;
        System.out.println("second account : "+second);

        synchronized (first) {
            System.out.println(Thread.currentThread().getName() + " locked " + first.getId());

            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + " locked " + second.getId());
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    System.out.println("Transferred ₹" + amount + " from " + from.getId() + " to " + to.getId());
                } else {
                    System.out.println("Insufficient funds in " + from.getId());
                }
            }
        }
    }
}

public class DeadlockSimulation {
    public static void main(String[] args) {
//        Account accountA = new Account("A", 5000);
//        Account accountB = new Account("B", 5000);

//        Thread t1 = new Thread(new TransferTaskDeadLock(accountA, accountB, 1000), "T1");
//        Thread t1 = new Thread(new TransferTaskDeadLockFixed(accountA, accountB, 1000), "T1");
//        Thread t2 = new Thread(new TransferTaskDeadLock(accountB, accountA, 2000), "T2");
//        Thread t2 = new Thread(new TransferTaskDeadLockFixed(accountB, accountA, 2000), "T2");

        AccountWithLock accountA = new AccountWithLock("ATL", 5000);
        AccountWithLock accountB = new AccountWithLock("BTL", 5000);

        Thread t1 = new Thread(new TransferTaskTryLock(accountA, accountB, 1000), "T1");
        Thread t2 = new Thread(new TransferTaskTryLock(accountB, accountA, 2000), "T2");

        t1.start();
        t2.start();
    }
}

class AccountWithLock {
    private final String id;
    private double balance;
    private final Lock lock = new ReentrantLock();

    public AccountWithLock(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public String getId() {
        return id;
    }
}

class TransferTaskTryLock implements Runnable {
    private final AccountWithLock from;
    private final AccountWithLock to;
    private final double amount;

    public TransferTaskTryLock(AccountWithLock from, AccountWithLock to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (true) {
            if (from.tryLock()) {
                try {
                    if (to.tryLock()) {
                        try {
                            if (from.withdraw(amount)) {
                                to.deposit(amount);
                                System.out.println("Transferred ₹" + amount + " from " + from.getId() + " to " + to.getId());
                            } else {
                                System.out.println("Insufficient funds in " + from.getId());
                            }
                            return; // Success, exit loop
                        } finally {
                            to.unlock();
                        }
                    }
                } finally {
                    from.unlock();
                }
            }
        }
    }
}
