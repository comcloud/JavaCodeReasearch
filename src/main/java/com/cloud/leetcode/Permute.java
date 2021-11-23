/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 给定数组的所有排列的情况
 * </p>
 * @author zhangyulei
 * @version :Permute.java v1.0 2021/11/10 5:04 下午 zhangyulei Exp $
 */
public class Permute {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        System.out.println(new Permute().permuteTo(nums));
    }

    public List<List<Integer>> permuteTo(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        return doPermuteTo(new ArrayList<>(), new ArrayList<>(), nums);
    }


    public List<List<Integer>> doPermuteTo(List<List<Integer>> result , List<Integer> intermediateResult, int[] nums) {
        if(intermediateResult.size() == nums.length) {
            result.add(new ArrayList<>(intermediateResult));
            return result;
        }

        for (int num : nums) {
            if(intermediateResult.contains(num)){
                //中间结果中已经有这个数据，则直接跳过
                continue;
            }
            intermediateResult.add(num);
            doPermuteTo(result, intermediateResult, nums);
            intermediateResult.remove(intermediateResult.size() - 1);
        }
        return result;
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        //从first开始，将自己之后所有情况交换一次
        //123，
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

}
