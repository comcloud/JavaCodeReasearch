/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *数列的定义如下： 数列的第一项为n，以后各项为前一项的平方根，求数列的前m项的和。
 * 输入描述
 * 输入数据有多组，每组占一行，由两个整数n（n<10000）和m(m<1000)组成，n和m的含义如前所述。
 *
 * 输出描述
 * 对于每组输入数据，输出该数列的和，每个测试实例占一行，要求精度保留2位小数。
 * </p>
 * @author zhangyulei
 * @version :SumOfSeries.java v1.0 2021/9/10 7:10 下午 zhangyulei Exp $
 */
public class SumOfSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SumOfSeries series = new SumOfSeries();
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            System.out.printf("%.2f\n" ,series.sum(n, m));
        }
    }

    public double sum(double n, int m){
        double result = n;
        for (int i = 1; i < m; i++) {
            double sqrt =  Math.sqrt(n);
            result += sqrt;
            n = sqrt;
        }
        return result;
    }

}
