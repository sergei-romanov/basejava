package com.urise.webapp.concurrency;

public class Deadlock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();
    public static void main(String[] args) {
        Thread10 thread10 = new Thread10();
        Thread20 thread20 = new Thread20();
        thread10.start();
        thread20.start();
    }
}
class Thread10 extends Thread {
    public void run() {
        synchronized (Deadlock.lock1) {
            System.out.println("THread10: Попытка захватить монитор объекта lock1");
            System.out.println("THread10: монитор объекта lock1 захвачен");
            System.out.println("THread10: Попытка захватить монитор объекта lock2");
         /*   try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (Deadlock.lock2) {
                System.out.println("THread10: монитор объекта lock1 и lock2 захвачены");
            }
        }
    }
}
class Thread20 extends Thread {
    public void run() {
        synchronized (Deadlock.lock2) {
            System.out.println("THread20: Попытка захватить монитор объекта lock2");
            System.out.println("THread20: монитор объекта lock2 захвачен");
            System.out.println("THread20: Попытка захватить монитор объекта lock1");
           /* try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (Deadlock.lock1) {
                System.out.println("THread20: монитор объекта lock1 и lock2 захвачены");
            }
        }
    }
}
