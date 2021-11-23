/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *  计算岛屿的周长，那么就是二维数据中没碰到一个1就+4-2，对一周进行回溯运算
 * </p>
 * @author zhangyulei
 * @version :IslandPerimeter.java v1.0 2021/11/14 3:36 下午 zhangyulei Exp $
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        int[][] grid = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, }};
        System.out.println(new IslandPerimeter().islandPerimeter(grid));
    }

    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //只有一个岛屿
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    public int dfs(int[][] grid, int i, int j) {
        if (!(0 <= i && i < grid.length && 0 <= j && j < grid[0].length)) {
            //说明越界，但是此时也是一个岛屿走向网格之外，周长+1
            return 1;
        }
        //因为调用此方法肯定是从岛屿过来，所以如果此时此位置为0表示从岛屿到水域位置，则直接返回1，表示周长1
        if (grid[i][j] == 0) {
            return 1;
        }
        //如果这里不等于1，说明已经走过
        if (grid[i][j] != 1) {
            return 0;
        }
        //将此位置置为2，标记为已经走过
        grid[i][j] = 2;
        return dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
    }
}
