/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
 * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 *
 * 判断两个字符串是否有重复的可以使用一个int类型然后以二进制的方式标识，26位就足够
 * </p>
 * @author zhangyulei
 * @version :MaxProduct.java v1.0 2021/10/12 7:49 下午 zhangyulei Exp $
 */
public class MaxProduct {

    public static void main(String[] args) {
        String[] words = { "asdf", "qwer", "sewghw", "lhlk", "oiu" };
        System.out.println(new MaxProduct().maxProduct(words));
    }

    public int maxProduct(String[] words) {
        //转换为二进制数组
        int[] binary = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                //先让1左移，左移的位数就是当前位置的字符-'a'，这样表示对应二进制位置有这样一个字符，后面用于&比较，有重复字符说明有相同的位置都是1
                //然后对原先的结果进行｜运算，就是异或，只有一个是1和另一个是0才是1
                binary[i] |= 1 << words[i].charAt(j) - 'a';
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((binary[i] & binary[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}
