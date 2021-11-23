/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :CombinationSum.java v1.0 2021/11/3 9:18 下午 zhangyulei Exp $
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        System.out.println(new CombinationSum().combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        dfs(combinations, new ArrayList<>(), 0, target, candidates);
        return combinations;
    }

    /**
     * 深度遍历，每次我们有两种选择，一是使用当前数字，一种不实用去使用下一个
     * @param combinations 结果列表
     * @param combination 中间列表，存储中间结果
     * @param curr 数组的当前位置
     * @param target 目标值，每次目标值可能会改动
     * @param candidates 给定数组
     */
    public void dfs(List<List<Integer>> combinations, List<Integer> combination, int curr, int target,
                    int[] candidates) {
        if (curr == candidates.length) {
            return;
        }
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        //直接跳过，即不实用当前数字，使用下一个
        dfs(combinations, combination, curr + 1, target, candidates);

        //选择当前数
        if (target - candidates[curr] >= 0) {
            combination.add(candidates[curr]);
            dfs(combinations, combination, curr, target - candidates[curr], candidates);
            combination.remove(combination.size() - 1);
        }
    }

}
