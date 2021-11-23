/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 给定一个数组，这个数组中有一个数字只出现一次，其他都是三次，找到这个数字
 * </p>
 * @author zhangyulei
 * @version :OnceNumber.java v1.0 2021/10/11 5:26 下午 zhangyulei Exp $
 */
public class OnceNumber {

    public static void main(String[] args) {
        int[] nums = { 2, 2, 2, 3, 4, 4, 4 };
        System.out.println(new OnceNumber().usualNumber(nums));
    }

    public int get(int[] nums) {
        int a = 0, b = a;
        for (int num : nums) {
            a = ~b & (a ^ num);
            b = ~a & (b ^ num);
        }
        return a;
    }

    //将整数的各个数位上的加起来，然后对3取余，若结果为0，则待求数字在该位上是0；
    //若结果为1，则待求数字在该位上是1.

    public int singleNumber(int[] nums) {
        //java的int整型为32位
        int[] arr = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                arr[i] += (num >> (31 - i)) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + arr[i] % 3;
        }
        return res;
    }

    public int usualNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        return map.entrySet().stream().filter(integerIntegerEntry -> integerIntegerEntry.getValue() == 1).findFirst()
                .get().getKey();
    }
}
