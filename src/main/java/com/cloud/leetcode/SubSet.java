/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :SubSet.java v1.0 2021/10/18 8:14 下午 zhangyulei Exp $
 */
public class SubSet {
    public static List<List<Integer>> subsets(int[] nums) {
        //record the final answer
        List<List<Integer>> list = new ArrayList<>();
        //record one of the subSet
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(nums);
        //calling the backtrack function
        getSubset(list, tempList, 0, nums);
        return list;
    }

    private static void getSubset(List<List<Integer>> list, List<Integer> tempList, int startLen, int[] nums) {
        //by calling itself to add tempList to the list
        list.add(new ArrayList<>(tempList));
        for (int i = startLen; i < nums.length; i++) {
            // add element to tempList
            tempList.add(nums[i]);
            //calling itself
            getSubset(list, tempList, i + 1, nums);
            //backtrack and remove the top element in tempList
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 3 };
        List<List<Integer>> list = subsets(nums);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }
}
