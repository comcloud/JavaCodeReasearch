package com.cloud.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
 * 返回它将会被按顺序插入的位置。
 *
 * @version v1.0
 * @ClassName SearchInsertLocation
 * @Author rayss
 * @Datetime 2021/4/26 2:06 下午
 */

public class SearchInsertLocation {
    private static final ReentrantLock LOCK = new ReentrantLock(true);
    private static final Condition CONDITION = LOCK.newCondition();

    public static void main(String[] args) throws InterruptedException {

        try {
            LOCK.lock();
            int[] nums = {1, 3};
//            CONDITION.await();
            int searchInsert = new SearchInsertLocation().searchInsert(nums, 3);
//            CONDITION.signal();
            System.out.println("searchInsert = " + searchInsert);
        } finally {
            LOCK.unlock();
        }

    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            } else if (i == nums.length - 1) {
                return nums[i] < target ? nums.length : i;
            } else if (nums[i] < target && nums[i + 1] > target) {
                return i + 1;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }
}
