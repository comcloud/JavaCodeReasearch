/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * </p>
 * @author zhangyulei
 * @version :MaxProfit.java v1.0 2021/10/26 9:31 下午 zhangyulei Exp $
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        System.out.println(new MaxProfit().maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            if (price - min > max) {
                max = price - min;
            }
        }
        return max;
    }
}
