package com.cloud.MainTest.controller;

import com.cloud.MainTest.bean.ResizableCapacityLinkedBlockingQueue;

import java.util.concurrent.*;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/2/24 11:01 上午
 */
@SuppressWarnings("all")
public class Client {

    private static final int CORE_POOL_SIZE = 5;

    private static final int MAX_POOL_SIZE = 10;

    private static final long KEEP_ALIVE_TIME = 1L;

    private static final int QUEUE_CAPACITY = 10;

    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor executor = buildThreadPoolExecutor();
//
//        for (int i = 0; i < 10; i++) {
//            CustomRunnable worker = new CustomRunnable("" + i);
////            Future<?> future = executor.submit(worker);
//            executor.execute(worker);
//
//        }
//        //终结线程池
//        executor.shutdown();
//        while (!executor.isTerminated()) ;
//        System.out.println("finished");
        dynamicModifyExecutor();
    }

    private static ThreadPoolExecutor buildThreadPoolExecutor() {

        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ResizableCapacityLinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor, "改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();
        queue.setCapacity(100);
        threadPoolStatus(executor,"改变之后");
        Thread.currentThread().join();
    }

    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {

        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-" + name + "-" +
                "核心线程数:" + executor.getCorePoolSize() +
                " 活跃线程数:" + executor.getActiveCount() +
                " 最大线程数:" + executor.getMaximumPoolSize() +
                " 线程池活跃度：" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                " 任务完成数：" + executor.getCompletedTaskCount() +
                " 队列大小：" + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数：" + queue.size() +
                " 队列剩余大小：" + queue.remainingCapacity() +
                " 队列使用度：" + divide(queue.size(), queue.size() + queue.remainingCapacity()));
    }

    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%",
                Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }

}
