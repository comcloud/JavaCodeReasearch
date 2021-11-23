/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :NextPermutation.java v1.0 2021/11/1 9:34 下午 zhangyulei Exp $
 */
public class NextPermutation {

    public static void main(String[] args) {
        //5，5，2，3，4，7
        int[] nums = { 5, 4, 7, 5, 3, 2 };
        new NextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {
                int minIndex = getMinIndex(nums, i, i - 1);
                int temp = nums[i - 1];
                nums[i - 1] = nums[minIndex];
                nums[minIndex] = temp;
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        //此时说明数组是降序，那么需要改为生序
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }

    }

    private int getMinIndex(int[] nums, int start, int pre) {
        int minIndex = start;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[minIndex] > nums[i] && nums[i] > nums[pre]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
