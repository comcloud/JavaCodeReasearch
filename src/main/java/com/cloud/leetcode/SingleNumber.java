/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :SingleNumber.java v1.0 2021/10/27 2:33 下午 zhangyulei Exp $
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 2, 1 };
        System.out.println(new SingleNumber().singleNumber(nums));
    }

    public int singleNumberTo(int[] nums) {
        int singleNumber = 0;
        for (int num : nums) {
            singleNumber ^= num;
        }
        return singleNumber;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        return map.entrySet().stream().filter(integerIntegerEntry -> integerIntegerEntry.getValue() == 1).findFirst()
                .get().getKey();
    }
}
