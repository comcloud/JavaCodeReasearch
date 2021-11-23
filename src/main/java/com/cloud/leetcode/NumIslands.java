/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :NumIslands.java v1.0 2021/11/14 5:39 下午 zhangyulei Exp $
 */
public class NumIslands {

    public static void main(String[] args) {
        char[][] grid = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
                          { '0', '0', '0', '0', '0' } };
        System.out.println(new NumIslands().numIslands(grid));
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>();
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        PriorityBlockingQueue<Runnable> priorityBlockingQueue = new PriorityBlockingQueue<>();
        DelayQueue<Delayed> delayQueue = new DelayQueue<>();
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<Runnable>();
        ConcurrentLinkedQueue<Runnable> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.SECONDS, deque);

    }

    public int numIslands(char[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (!(0 <= i && i < grid.length && 0 <= j && j < grid[0].length)) {
            //说明越界，但是此时也是一个岛屿走向网格之外，周长+1
            return;
        }
        //如果不为1
        if (grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
