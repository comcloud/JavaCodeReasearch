/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.frequentlyused.dynamic;

import java.util.Arrays;

/**
 * <p>
 * 给定一个乱序数组，找到最大子数组让其总和最大
 * 比如-1，-2，1，0，-3
 * 最大为：1，0，最大和为1
 *
 * 动态规划解决
 * 假定f(i)为每个位置的最大值，那么会有一个f数组，这样的数组求出最大值即可
 * 计算f(i)我们需要知道f(i-1) + arr[i] 与 arr[i]的大小，那么状态转移方程就是
 * f(i) = Max(f(i - 1) + arr[i], arr[i])
 * </p>
 * @author zhangyulei
 * @version :MaxSumOfArray.java v1.0 2021/10/12 10:21 上午 zhangyulei Exp $
 */
public class MaxSumOfArray {

    public static void main(String[] args) {
        int[] arr = { -1, 3, -2, 3, 5 };
        System.out.println(new MaxSumOfArray().getMaxSumOfArray(arr));
    }

    public int getMaxSumOfArray(int[] arr) {
        int[] f = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            f[i] = getFi(arr, i);
        }
        return Arrays.stream(f).max().getAsInt();
    }

    public int getFi(int[] arr, int index) {
        if (index == 0) {
            return arr[0];
        }
        return Math.max(getFi(arr, index - 1) + arr[index], arr[index]);
    }

}
