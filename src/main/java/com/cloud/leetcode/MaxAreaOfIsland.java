/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :MaxAreaOfIsland.java v1.0 2021/11/14 5:04 下午 zhangyulei Exp $
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                         { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                         { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                         { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };
        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    result.add(currArea(grid, i, j));
                }
            }
        }
        return result.stream().max(Comparator.comparingInt(o -> o)).orElse(0);
    }

    public int currArea(int[][] grid, int i, int j) {
        if (!(0 <= i && i < grid.length && 0 <= j && j < grid[0].length)) {
            //说明越界，但是此时也是一个岛屿走向网格之外，周长+1
            return 0;
        }
        //如果此时不是1
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        //还需要加上自己
        return currArea(grid, i + 1, j) + currArea(grid, i - 1, j) + currArea(grid, i, j + 1) + currArea(grid, i, j - 1)
               + 1;
    }
}
