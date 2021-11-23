/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *给定一个含有n个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 1，2，2，3，3，4
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 *
 * </p>
 *
 * 使用滑动窗口解决，其实也是双指针
 * @author zhangyulei
 * @version :MinSubArrayLen.java v1.0 2021/10/15 2:39 下午 zhangyulei Exp $
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        //        int[] nums = { 1, 4, 4 };
        int target = 7;
        System.out.println(new MinSubArrayLen().minSubArrayLen(target, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = 0;
        int curr = 0;
        int ans = Integer.MAX_VALUE;
        while (end < nums.length) {
            curr += nums[end];
            while(curr >= target){
                ans = Math.min(ans, end - start + 1);
                curr -= nums[start];
                start++;
            }
            end++;
        }

        return ans != Integer.MAX_VALUE ? ans : 0;
    }

}