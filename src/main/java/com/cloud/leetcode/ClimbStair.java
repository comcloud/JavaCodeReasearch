package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName ClimbStair
 * @Author rayss
 * @Datetime 2021/6/26 5:30 下午
 */

public class ClimbStair {

    public static void main(String[] args) {
        //1，2，1
        //1，1，2
        //1，1，1，1
        //2，1，1
        //2，2
        System.out.println(climbStairsByRecursion(5));
    }

    /**
     * 递归写法，但是重复计算的内容太多，不推荐
     */
    public static int climbStairsByRecursion(int n) {
        if (n <= 1) {
            return 1;
        }else if(n == 2){
            return 2;
        }
        return climbStairsByRecursion(n - 1) + climbStairsByRecursion(n - 2);
    }

    public static int climbStairsByIteration(int n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            int result = 0;
            for (int first = 1, second = 2, total = 3; total <= n; total++) {
                result = first + second;
                first = second;
                second = result;
            }
            return result;
        }
    }
}
