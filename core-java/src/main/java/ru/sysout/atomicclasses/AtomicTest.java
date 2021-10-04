package ru.sysout.atomicclasses;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest implements Runnable {
    AtomicInteger counter = new AtomicInteger(0);


    public void run() {
        for (int i = 0; i < 500; i++) {
            counter.getAndIncrement();
            System.out.println(counter);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        AtomicTest atomicTest = new AtomicTest();
        Thread thread1 = new Thread(atomicTest);
        Thread thread2 = new Thread(atomicTest);

        thread1.start();
        thread2.start();

        Thread.sleep(1000);

        System.out.println(atomicTest.counter);
      
    }
}
