package com.cloud.leetcode;

/**
 * 给定两个字符串查看第二个字符串是否可以组成第一个字符串
 *
 * @version v1.0
 * @ClassName IsForm
 * @Author rayss
 * @Datetime 2021/6/22 5:14 下午
 */

public class IsForm {
    public static void main(String[] args) {
        String s1 = "abc",s2 = "bca";
        System.out.println(checkPermutation(s1, s2));
    }

    public static boolean checkPermutation(String s1, String s2) {
        char[] charArray = s1.toCharArray();
        for (char c : charArray) {
            int index = s2.indexOf(c);
            if (index == -1) {
                return false;
            } else {
                s2 = s2.substring(0, index) + s2.substring(index + 1);
            }
        }
        return "".equals(s2);
    }
}
