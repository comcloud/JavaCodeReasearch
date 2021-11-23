package com.cloud.MainTest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @ClassName ProAndCon_4
 * @Author rayss
 * @Datetime 2021/4/23 11:30 上午
 */

public class ProAndCon_4 {
    private static int count = 0;

    //是否为满信号量，这个实际上就是标识着是否还可以生产
    private static final Semaphore NOT_FULL = new Semaphore(10);
    //是否为空信号量，表示着是否还可以继续消费
    private static final Semaphore NOT_EMPTY = new Semaphore(0);
    //用来消费者与生产者同步
    private static final Semaphore MUTEX = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new ProAndCon_4.Product(), "pro-1 : ").start();
        new Thread(new ProAndCon_4.Consumer(), "con-1 : ").start();
        new Thread(new ProAndCon_4.Product(), "pro-2 : ").start();
        new Thread(new ProAndCon_4.Consumer(), "con-2 : ").start();
        new Thread(new ProAndCon_4.Product(), "pro-3 : ").start();
        new Thread(new ProAndCon_4.Consumer(), "con-3 : ").start();
    }

    private static class Consumer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1L);
                try {
                    NOT_EMPTY.acquire();
                    MUTEX.acquire();
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + --count);
                } finally {
                    MUTEX.release();
                    NOT_FULL.release();
                }
            }
        }
    }

    private static class Product implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1L);
                try {
                    NOT_FULL.acquire();
                    MUTEX.acquire();
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + ++count);
                } finally {
                    NOT_EMPTY.release();
                    MUTEX.release();

                }
            }
        }
    }
}
