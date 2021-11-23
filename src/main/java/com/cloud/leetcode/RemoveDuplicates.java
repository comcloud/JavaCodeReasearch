package com.cloud.leetcode;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @version v1.0
 * @ClassName RemoveDuplicates
 * @Author rayss
 * @Datetime 2021/6/24 1:18 下午
 */

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4};
        int newLen = removeDuplicates(nums);
        System.out.println("newLen = " + newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + "\t");
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            //把不重复最后一个位置的数据的取出来在数组中顺序存放
            if (nums[i - 1] != nums[i]) {
                nums[count] = nums[i - 1];
                count++;
            }
        }
        //把最后一个位置的数据存放，之所要进行存放最后一个数字，是因为如果最后的数字有重复的那么将不会存放最后一个，如果只有一个也不存放
        nums[count] = nums[nums.length - 1];
        //此时count是表示的索引，索引返回是长度要进行+1操作
        return count + 1;
    }
}
