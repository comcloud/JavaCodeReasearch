/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。、
 *  0 1 0 1 0 1 0 1 0 0 1 0
 *
 *
 * </p>
 * @author zhangyulei
 * @version :FindMaxlenght.java v1.0 2021/10/19 4:54 下午 zhangyulei Exp $
 */
public class FindMaxLength {

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0};
        System.out.println(new FindMaxLength().findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        //        哈希表存储的是counter 的每个取值第一次出现的下标
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                //第一次到这个位置说明counter非1即-1，但是无论哪一个都不满足条件，都是表示多了一个0或者1，所以不要这位置的内容就可以，即i - prevIndex表示子数组长度
                int prevIndex = map.get(counter);
                //前缀和的思想，我们已经假定元素为1表示1，元素0表示-1，那么二者和为0，所以我们要找的就是想加等于0的问题
                //计算j + 1到k的子数组的元素和为  prefixSum[k] - prefixSum[j],子数组长度为k-j
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;

    }
}
