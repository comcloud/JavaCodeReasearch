/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 输入：cards = [1,2,8,9], cnt = 3
 * 输出：18
 *
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 将数组分为奇偶两部分，重点在于cnt的方案配置
 * - cnt == 1 那么直接获取偶数，没有返回0
 * - cnt >= 2 就是相当于cnt  与cnt - 1配比
 * cnt获取一个奇数，cnt - 1获取的和也为奇数
 * cnt获取一个偶数，cnt - 1获取的和也为偶数
 * </p>
 * @author zhangyulei
 * @version :MaxScore.java v1.0 2021/10/12 9:25 下午 zhangyulei Exp $
 */
public class MaxScore {

    List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {

    }

    public int maxScore(int[] cards, int cnt) {
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int card : cards) {
            if (card % 2 == 0) {
                even.add(card);
            } else {
                odd.add(card);
            }
        }
        return result.size() == 0 ? 0 : result.stream().max(Comparator.comparingInt(o -> o)).get();

    }

    public void fillList(List<Integer> even, List<Integer> odd, int cnt) {
        if (cnt == 1) {
            if (even.size() > 0) {
                result.add(even.stream().max(Comparator.comparingInt(o -> o)).get());
            }
            return;
        } else if (cnt == 2) {
            //这里要考虑一个问题，不断递归最终只会计算这几个数字，所以要想递归实现应该一个递归流程中需要展现出来，故需要使用栈或者队列弹出数据
            //故一个完成的递归流程使用一个数据源
            if(even.size() > 0){
                //获取两个偶数或者两个奇数


            }else if(odd.size() >= 2){
                //获取最大两个奇数
            }
        }
        fillList(even, odd, cnt - 1);
    }

    public int getMax(List<Integer> even, List<Integer> odd, int cnt){
        if (cnt == 1) {
            if (even.size() > 0) {
                result.add(even.stream().max(Comparator.comparingInt(o -> o)).get());
            }
            return 0 ;
        }else if(cnt == 2){

        }else{

        }
        return 0;
    }

}
