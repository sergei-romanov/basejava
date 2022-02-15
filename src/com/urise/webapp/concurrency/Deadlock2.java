package com.urise.webapp.concurrency;

import static java.lang.Thread.sleep;

public class Deadlock2 {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Deadlock.lock1) {
                System.out.println("Thread1: Попытка захватить монитор объекта lock1");
                System.out.println("Thread1: монитор объекта lock1 захвачен");
                System.out.println("Thread1: Попытка захватить монитор объекта lock2");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Deadlock.lock2) {
                    System.out.println("Thread1: монитор объекта lock1 и lock2 захвачены");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (Deadlock.lock2) {
                System.out.println("Thread2: Попытка захватить монитор объекта lock2");
                System.out.println("Thread2: монитор объекта lock2 захвачен");
                System.out.println("Thread2: Попытка захватить монитор объекта lock1");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Deadlock.lock1) {
                    System.out.println("Thread2: монитор объекта lock1 и lock2 захвачены");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}

