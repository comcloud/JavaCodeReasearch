package com.cloud.jdkutil;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 分支工具类ForkJoinPool
 * 这里示例分治计算1加到n的和
 *
 * @version v1.0
 * @ClassName ForkJoinPoolClient
 * @Author rayss
 * @Datetime 2021/8/16 2:22 下午
 */

public class ForkJoinPoolClient {
    public static void main(String[] args) {
        Object o = new ForkJoinPoolClient();


        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i+1;
        }
        System.out.println(sum);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(availableProcessors);

        int count = 100;
        int[] counts = new int[count];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = i + 1;
        }
        Long invoke = forkJoinPool.invoke(new SumTask(counts, 0, count));
        System.out.println(invoke);

    }

    private static class SumTask extends RecursiveTask<Long> {

        private final int[] counts;
        private final int start;
        private final int end;


        public SumTask(int[] counts, int start, int end) {
            this.start = start;
            this.end = end;
            this.counts = counts;
        }


        /**
         * 将任务分治计算，假设1+2+...+100，那么在计算时候获取到最后一步结果应该是(1 + 2 + ... + 99) + 100,但是要计算(1 + 2 + ... + 99)
         * 就需要计算(1 + 2 + ... + 98)，直到最后1 + 2就可以很好的进行运算，因为一步操作
         *
         * @return 1加到n的和
         */
        @Override
        protected Long compute() {
            int minComputeNumber = 6;
            if (end - start <= minComputeNumber) {
                long total = 0;
                for (int i = start; i < end; i++) {
                    total += counts[i];
                }
                return total;
            } else {
                int middle = (start + end) / 2;
                SumTask leftTask = new SumTask(counts, start, middle);
                SumTask rightTask = new SumTask(counts, middle + 1, end);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }
}
