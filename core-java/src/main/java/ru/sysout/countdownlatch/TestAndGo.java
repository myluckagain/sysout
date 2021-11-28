package ru.sysout.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class TestAndGo implements Runnable {

    private CountDownLatch countDownLatch;

    public TestAndGo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("в процессе тестирования");
        countDownLatch.countDown();
        System.out.println("пошел домой");
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(10);

        IntStream.range(0,10).forEach((i)->
                new Thread(new TestAndGo(countDownLatch)).start());
        countDownLatch.await();
        System.out.println("все сдали, зачет окончен");
    }
}
