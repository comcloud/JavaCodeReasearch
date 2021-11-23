/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Arrays;

/**
 * <p>
 * 排序数组找到目标值所在的范围，也就是开始位置与结束位置
 * </p>
 * @author zhangyulei
 * @version :SearchRange.java v1.0 2021/11/3 8:37 下午 zhangyulei Exp $
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = { 7 };
        int target = 7;
        System.out.println(Arrays.toString(new SearchRange().searchRange(nums, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums == null || nums.length == 0) {
            return res;
        }
        int l = 0, r = 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                //从mid左右往两边扩
                l = mid;
                while (l > 0 && nums[l - 1] == target) {
                    l--;
                }
                r = mid;
                while (r < nums.length - 1 && nums[r + 1] == target) {
                    r++;
                }
                res[0] = l;
                res[1] = r;
                break;
            }
        }
        return res;
    }
}
