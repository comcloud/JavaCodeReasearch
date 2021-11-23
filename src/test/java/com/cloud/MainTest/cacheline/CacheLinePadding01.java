package com.cloud.MainTest.cacheline;

import java.util.concurrent.CountDownLatch;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/3/11 10:20 上午
 */
public class CacheLinePadding01 {

    private static final int COUNT = 1_0000_0000;

    private static class T{
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = 1;
            }
            latch.countDown();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[1].x = 1;
            }
            latch.countDown();
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();

        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);

    }
}
