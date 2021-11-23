/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 * 计算最大差值
 * </p>
 * @author zhangyulei
 * @version :ComputeMaxDiff.java v1.0 2021/9/2 2:19 下午 zhangyulei Exp $
 */
public class ComputeMaxDiff {

    public static void main(String[] args) {
        int[] arr = { 7, 1, 5, 3, 6, 4 };
        System.out.println(new ComputeMaxDiff().maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        int[] maxArr = getMaxArr(prices);
        int result = 0;
        for (int max : maxArr) {
            if (result < max) {
                result = max;
            }
        }
        return result;
    }

    public int[] getMaxArr(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length - 1; i++) {
            int max = prices[i + 1] - prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if(prices[j] - prices[i] > max){
                    max = prices[j] - prices[i];
                }
            }
            result[i] = max;
        }
        return result;
    }

}
