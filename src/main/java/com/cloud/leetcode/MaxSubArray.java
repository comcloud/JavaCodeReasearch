package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName MaxSubArray
 * @Author rayss
 * @Datetime 2021/6/23 2:43 下午
 */

public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = { -1, 3, -2, 3, 5 };
        System.out.println(maxSubArray(nums));
    }

    /**
     * 假设sum<=0，那么后面的子序列肯定不包含目前的子序列，所以令sum = num；如果sum > 0对于后面的子序列是有好处的。
     * res = Math.max(res, sum)保证可以找到最大的子序和。
     * */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
