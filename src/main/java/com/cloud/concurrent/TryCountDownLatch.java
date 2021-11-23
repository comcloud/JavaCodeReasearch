package com.cloud.concurrent;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @ClassName TryCountDownLatch
 * @Author rayss
 * @Datetime 2021/6/27 11:56 下午
 */

public class TryCountDownLatch implements Runnable {

    private final int sequence;

    private static final CountDownLatch LATCH = new CountDownLatch(10);

    public TryCountDownLatch(int sequence){
        this.sequence = sequence;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.execute(new TryCountDownLatch(i));
        }
        System.out.println("All Thread Wait " + System.currentTimeMillis());
        LATCH.await();
        System.out.println("All Thread Completed " + System.currentTimeMillis());
        pool.shutdown();

    }

    @SneakyThrows
    @Override
    public void run() {
        TimeUnit.SECONDS.sleep(new Random().nextInt(3) * 100);
        System.out.println("current sequence is " + sequence);
        LATCH.countDown();
    }
}
