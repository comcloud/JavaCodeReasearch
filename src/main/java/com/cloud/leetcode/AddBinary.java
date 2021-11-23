package com.cloud.leetcode;

/**
 *
 * 两个二进制数相加
 * @version v1.0
 * @ClassName AddBinary
 * @Author rayss
 * @Datetime 2021/6/25 8:29 上午
 */

public class AddBinary {

    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        int c = 0, l = a.length() - 1, r = b.length() - 1;
        StringBuilder res = new StringBuilder();
        while(l >= 0 || r >= 0 || c != 0){
            if(l >= 0) {
                c += a.charAt(l -- ) - '0';
            }
            if(r >= 0) {
                c += b.charAt(r -- ) - '0';
            }
            //因为二进制运算，逢2进1，所以这里存储的是对2取余的结果
            res.append(c % 2);
            //自身除2
            c /= 2;
        }
        return res.reverse().toString();
    }
}
