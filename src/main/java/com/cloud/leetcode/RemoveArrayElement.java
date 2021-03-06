package com.cloud.leetcode;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @version v1.0
 * @ClassName RemoveArrayElement
 * @Author rayss
 * @Datetime 2021/6/24 12:54 下午
 */

public class RemoveArrayElement {
    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val = 2;
        int newLen = removeElement(nums, val);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.println(nums[i]);
        }
    }

    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }
        return count;
    }

    public static int removeElementByTwoPointer(int[] nums, int val) {
        int first = 0, second = 0;
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            while (first < nums.length && nums[first] != val) {
                //循环到相等
                first++;
                length++;
            }
            second = first + 1;
            while (second < nums.length && nums[second] == val) {
                second++;
            }
            if (first >= nums.length || second >= nums.length) {
                break;
            }
            nums[first] = nums[second];
            nums[second] = val;
        }
        return length;
    }
}
