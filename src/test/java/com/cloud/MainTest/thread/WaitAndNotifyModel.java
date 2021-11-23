package com.cloud.MainTest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 等待唤醒模式
 *
 * @version v1.0
 * @ClassName WaitAndNotifyModel
 * @Author rayss
 * @Datetime 2021/8/15 7:02 下午
 */

public class WaitAndNotifyModel {
    public static void main(String[] args) throws InterruptedException {
        long timeoutTime = 3000L;
        setTimeout(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + "\t$");
            }
        }, timeoutTime);
        for (int i = 0; i < 100000; i++) {
            System.out.print(i == 0 ? "\n" + i + "\t" : i + "\t");
        }
        System.out.println();
        LATCH.await();
    }

    private static final CountDownLatch LATCH = new CountDownLatch(5);

    private static void setTimeout(Fun fun, long milliseconds) {
        Thread newThread = new Thread(() -> {
            LATCH.countDown();
            try {
                TimeUnit.MILLISECONDS.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fun.command();
        });
        newThread.start();
    }

    @FunctionalInterface
    private interface Fun {
        void command();
    }
}
