/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * </p>
 * @author zhangyulei
 * @version :NumSubarrayProductLessThanK.java v1.0 2021/10/18 5:22 下午 zhangyulei Exp $
 */
public class NumSubarrayProductLessThanK {

    public static void main(String[] args) {
        int[] nums = { 10, 5, 2, 6 };
        int k = 30;
        System.out.println(new NumSubarrayProductLessThanK().numSubarrayProductLessThanK(nums, k));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int sum = 1;
        //定义统计的变量
        int count = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum *= nums[j];
            //当乘积大于等于目标值的时候，缩小窗户，同时sum除以子数组左边的数
            while (i <= j && sum >= k) {
                sum /= nums[i++];
            }
            if (i <= j) {
                count += j - i + 1;
            }
        }
        return count;
    }

    public int numSubarrayProductLessThanKTo(int[] nums, int k) {
        /*
        *   从i～j
            s[j] - s[i] < k
            s[j] - k < s[i]
            *
        * */

        int count = 0;
        int preResult = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preResult = preResult == 0 ? num : preResult * num;

        }
        return -1;
    }
//
//    private int contains(int s1, int k, Map<Integer, Integer> map) {
//        int count = 0;
//        map.entrySet().stream().filter(entry -> s1 - entry.getKey() < k)
//                .reduce((entry1,entry2) -> entry1.getValue()+entry2.getValue());
//    }
}
