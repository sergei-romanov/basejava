package com.urise.webapp.concurrency;

public class Deadlock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    public void run() {
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
    }
}

class Thread2 extends Thread {
    public void run() {
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
    }
}
