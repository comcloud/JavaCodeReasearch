package com.cloud.MainTest.cacheline;

import java.util.concurrent.CountDownLatch;

/**
 * 了解这个必须要先有这个基础知识：缓存行(cache line)，以及数据一致性策略和每次缓存行读取一块是64字节(也就是说
 * 在cpu去读取内存中数据时候，每次都是读取一块64字节，然后都会放到缓存区中)
 * 这个小程序是相较于01而言，我们会发现，01中的执行效率会比这个慢
 * 01中每次读取的时候，都是把数组中两个数据都读取，因为这俩相加并没有64字节，那么开启两个线程
 * 这两个线程分别对数组中对两个T来进行变量更改，因为要保证数据一致性(缓存一致性协议)，一个CPU中的一个线程更改其中一个元素时候
 * 必须要去通知另一个CPU去内存重新读来获取正确数据，那么也就是说01时间大多浪费在来CPU互相通知上
 * 但是02中每个数组中的元素单个超过64，并且正好把要更改的数据作为一块读取，那么线程对其中的数据进行更改时候
 * 不会再去浪费时间去互相通知
 * 这个主要是就是体现缓存行
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/3/11 10:26 上午
 */
public class CacheLinePadding02 {
    private static final int COUNT = 1_0000_0000;

    private static class T{
        private long p0,p1,p2,p3,p4,p5,p6,p7;
        public volatile long x = 0L;
        private long p8,p9,p10,p11,p12,p13,p14;
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
