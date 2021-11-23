package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName Divide
 * @Author rayss
 * @Datetime 2021/8/18 10:56 上午
 */

public class Divide {

    public static void main(String[] args) {
        Divide divide = new Divide();
        System.out.println(divide.rightDivide(-9, -3));
    }
    public int rightDivide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean flag = a > 0 && b > 0 || a < 0 && b < 0;
        long d = a;
        long v = b;
        d = d > 0 ? d : -d;
        v = v > 0 ? v : -v;
        int res = 0;
        while (d >= v) {
            int cur = 1;
            long tmp = v;
            while (tmp + tmp <= d) {
                tmp += tmp;
                cur += cur;
            }
            res += cur;
            d -= tmp;
        }
        return flag ? res : -res;
    }
}
