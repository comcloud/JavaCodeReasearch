package com.cloud.MainTest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @ClassName ProAndCon_3
 * @Author rayss
 * @Datetime 2021/4/23 9:01 上午
 */

public class ProAndCon_3 {
    private static int count = 0;

    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        new Thread(new ProAndCon_3.Product(),"pro-1 : ").start();
        new Thread(new ProAndCon_3.Consumer(),"con-1 : ").start();
        new Thread(new ProAndCon_3.Product(),"pro-2 : ").start();
        new Thread(new ProAndCon_3.Consumer(),"con-2 : ").start();
        new Thread(new ProAndCon_3.Product(),"pro-3 : ").start();
        new Thread(new ProAndCon_3.Consumer(),"con-3 : ").start();

    }

    private static class Consumer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1L);
                QUEUE.take();
                count--;
                System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
            }
        }
    }

    private static class Product implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1L);
                QUEUE.put(1);
                count++;
                System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
            }
        }
    }
}
