/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :LetterCombinations.java v1.0 2021/11/14 5:08 下午 zhangyulei Exp $
 */
public class LetterCombinations {

    String[]     map = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    List<String> ans = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) {
            return ans;
        }
        nextDfs(new StringBuilder(), digits, 0);
        return ans;
    }

    public void nextDfs(StringBuilder builder, String digits, int curr) {
        if (curr == digits.length()) {
            ans.add(builder.toString());
            return;
        }
        String s = map[digits.charAt(curr) - '2'];
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i));
            nextDfs(builder, digits, curr + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
