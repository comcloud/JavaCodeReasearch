/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Arrays;

/**
 * <p>
 *     对半分，找到左右两边各自的和相等的位置，没有返回-1
 *1,7,3,6,5,6
 * </p>
 * @author zhangyulei
 * @version :PivotIndex.java v1.0 2021/10/19 6:07 下午 zhangyulei Exp $
 */
public class PivotIndex {

    public static void main(String[] args) {
        int[] nums = { 2, 1, -1 };
        System.out.println(new PivotIndex().pivotIndexTo(nums));
    }

    public int pivotIndexTo(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
