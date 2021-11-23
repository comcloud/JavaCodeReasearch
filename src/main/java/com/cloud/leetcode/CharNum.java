/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 给定一个字符串，找到出现最多字符的字数
 * </p>
 * @author zhangyulei
 * @version :CharNum.java v1.0 2021/9/22 10:08 下午 zhangyulei Exp $
 */
public class CharNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < line.length(); i++) {
                Integer value = map.get(line.charAt(i));
                if (Objects.nonNull(value)) {
                    value = value + 1;
                    map.put(line.charAt(i), value);
                } else {
                    map.put(line.charAt(i), 1);
                }
            }

            Optional<Map.Entry<Character, Integer>> maxResult = map.entrySet().stream()
                    .reduce((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? entry1 : entry2);
            Map.Entry<Character, Integer> entry = maxResult.orElse(null);
            List<Map.Entry<Character, Integer>> entryList = map.entrySet().stream()
                    .filter(e -> e.getValue().equals(entry.getValue())).collect(Collectors.toList());
            System.out.println("数量最大的字符：" + entry.getKey() + "\t对应的数量:" + entry.getValue());
            System.out.println(entryList);
        }
    }
}
