/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :OtherMain.java v1.0 2021/9/12 6:30 下午 zhangyulei Exp $
 */
public class OtherMain {
    public static void main(String[] args) {
        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine();
        /*
         * 分为左右两段字符串
         * 两边遍历，左边获取到0，右边必须要有1，否则删除0
         * */
        String leftStr = str.substring(0, str.length() / 2);
        String rightStr = str.substring(str.length() / 2);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < leftStr.length(); i++) {
            if(leftStr.charAt(i) == '0'){
                int indexOf = rightStr.indexOf('1');
                if(indexOf != -1){
                    builder.append(leftStr.charAt(i));
                }
            }else if(leftStr.charAt(i) == '1'){
                int indexOf = rightStr.indexOf('2');
                if(indexOf != -1){
                    builder.append(leftStr.charAt(i));
                }
            }else if(leftStr.charAt(i) == '2'){
                int indexOf = rightStr.indexOf('3');
                if(indexOf != -1){
                    builder.append(leftStr.charAt(i));
                }
            }
        }
        String s = builder.toString();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0'){
                builder.append('1');
            }else if(s.charAt(i) == '1'){
                builder.append('2');
            }else if(s.charAt(i) == '2'){
                builder.append('3');
            }
        }
        System.out.println(builder.length());
    }
}
