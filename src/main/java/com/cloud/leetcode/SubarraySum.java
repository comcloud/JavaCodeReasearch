/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数
 * </p>
 * @author zhangyulei
 * @version :SubarraySum.java v1.0 2021/10/19 4:19 下午 zhangyulei Exp $
 */
public class SubarraySum {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 3, 3 };
        int k = 3;
        System.out.println(new SubarraySum().subarraySum(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        //key : 前缀和；value : 出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        //表示前缀和
        int preSum = 0;
        //最开始为0，也是表示一种情况
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            //给次数添加preSum - k 前缀和已经出现的次数 s[j] - s[i] = k
            count += map.getOrDefault(preSum - k, 0);
            map.merge(preSum, 1, Integer::sum);
        }
        return count;
    }
}
