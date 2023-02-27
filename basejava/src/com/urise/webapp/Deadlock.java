package com.urise.webapp;

public class Deadlock {
    final Object lock1 = "lock1";
    final Object lock2 = "lock2";

    public void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Synchronize " + lock1.toString());
            synchronized (lock1) {
                System.out.println("Keep " + lock1);
                System.out.println("Synchronize " + lock2.toString());
                synchronized (lock2) {
                    System.out.println("Keep " + lock2);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        deadlock.deadLock(deadlock.lock1, deadlock.lock2);
        deadlock.deadLock(deadlock.lock2, deadlock.lock1);
    }
}
