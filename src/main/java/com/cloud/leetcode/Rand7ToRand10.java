/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Random;

/**
 * <p>
 *有一个rand7函数，实现一个rand10函数，但是不可以使用系统自带的
 * </p>
 * @author zhangyulei
 * @version :Rand7ToRand10.java v1.0 2021/10/9 5:54 下午 zhangyulei Exp $
 */
public class Rand7ToRand10 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(rand10());
        }
    }

    public static int rand10() {
        //思路：使用ran7在【0，6】直接选一个数 使用ran7在【0，5】之间选一个数
        int a = rand7();
        int b = rand7();
        //a不能为7 必须为【0，6】这样才能保证奇偶都是1/2概率
        while (a == 7) {
            a = rand7();
        }
        //b不能为5以上 因为一会可能要加5
        while (b > 5) {
            b = rand7();
        }
        //判断a奇偶性1/2 * b取值【0，5】之间1/5=1/10
        return ((a & 1) == 0 ? 0 : 5) + b;
    }

    public static int rand7() {
        return new Random().nextInt(7) + 1;
    }
}
