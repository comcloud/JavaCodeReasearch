/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Search.java v1.0 2021/11/2 9:29 下午 zhangyulei Exp $
 */
public class Search {

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 6, 0, 1, 2 };
        int target = 0;
        System.out.println(new Search().searchTo(nums, target));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int searchTo(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //因为数组旋转，所以先判断左边有序还是右边有序
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;

    }
}
