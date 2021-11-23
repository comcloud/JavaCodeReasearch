package com.cloud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CountUtil {
    private static Map<String, Integer> map = null;

    static {
        map = new HashMap<String, Integer>();
    }


    /**
     * 实现一个简单的计数器
     *
     * @param str
     * @return
     */
    public static int countNum(String str) {
        if (!"".equals(str) && str != null) {
            Integer count = map.get(str);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map.put(str, count);
        }
        return map.get(str);
    }


    public static void main(String[] args) {

        for (int i = 0; i < new Random().nextInt(50); i++) {
            countNum("a");
        }
        countNum("a");
        countNum("b");
        countNum("b");
        countNum("c");
        int countA = countNum("a");
        int countB = countNum("b");
        countNum("c");
        countNum("c");
        int countC = countNum("c");

        System.out.println(" count_a_num: " + countA + " count_b_num: " + countB + " count_c_num: " + countC);


    }
}