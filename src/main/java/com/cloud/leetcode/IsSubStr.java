/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *  给定两个字符串，判断其中一个是否是另外一个的子串
 * </p>
 * @author zhangyulei
 * @version :IsSubStr.java v1.0 2021/9/27 9:03 下午 zhangyulei Exp $
 */
public class IsSubStr {

    public static void main(String[] args) {

    }

    /**
     * 判断第二个参数是否是第一个参数的子串
     */
    public static boolean isSubStr(String str1, String str2) {
        return str1.contains(str2);

    }
}
