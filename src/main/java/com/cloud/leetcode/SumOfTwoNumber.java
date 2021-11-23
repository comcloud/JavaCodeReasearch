package com.cloud.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @version v1.0
 * @ClassName SumOfTwoNumber
 * @Author rayss
 * @Datetime 2021/6/26 8:20 下午
 */

public class SumOfTwoNumber {

    public static void main(String[] args) {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,0L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        int[] numbers = {1, -4, 4, 10};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] two(int[] numbers, int target) {
        int[] sum = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    sum[0] = i + 1;
                    sum[1] = j + 1;
                }
            }
        }
        return sum;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            //这个判断相当于就是说target - nums[i]是否在数组中，不过因为此时map已经记录了发生过的值，所以可以直接到Map查询
            //这里使用map的思想是将原先我们需要判断数组中两个数字和改为结果值-数组中某个数字==数组中另一个数字
            if (m.get(target - nums[i]) != null) {
                return new int[]{m.get(target - nums[i]) + 1, i + 1};
            }
            //每次都把结果记录下来
            m.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

}
