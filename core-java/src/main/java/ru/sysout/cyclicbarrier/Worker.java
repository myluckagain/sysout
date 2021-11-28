package ru.sysout.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class Worker implements Runnable{
    private final CyclicBarrier b1;
    private final CyclicBarrier b2;


    public Worker(CyclicBarrier b1, CyclicBarrier b2){
        this.b1=b1;
        this.b2=b2;
    }
    @Override
    public void run() {

        try {
            System.out.println("оторвать");
            b1.await();
            System.out.println("намазать");
            b2.await();
            System.out.println("приклеить");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CyclicBarrier b1=new CyclicBarrier(10, ()->{
            System.out.println("1 этап закончен");
        });
        CyclicBarrier b2=new CyclicBarrier(10, ()->{
            System.out.println("2 этап закончен");
        });

        IntStream.range(0,10).forEach((i)->
                new Thread(new Worker(b1,b2)).start());
    }
}
