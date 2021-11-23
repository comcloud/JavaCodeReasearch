package com.cloud.MainTest.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version v1.0
 * @ClassName InfixToSuffix
 * @Author rayss
 * @Datetime 2021/5/22 5:42 下午
 */

public class InfixToSuffix {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixList = toInfixList(expression);
        System.out.println(infixList);
    }

    private static List<String> toInfixList(String expression) {
        char[] chars = expression.toCharArray();
        List<String> expressionList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 48 || chars[i] > 57) {
                expressionList.add("" + chars[i]);
            } else {
                //此时是数字，为了防止为多位数，所以这里使用循环添加
                StringBuilder builder = new StringBuilder();
                do {
                    builder.append(chars[i]);
                    i++;
                } while (i < chars.length && chars[i] >= 48 && chars[i] <= 57);
                expressionList.add(builder.toString());
                i--;//因为执行for循环时候要执行i++，这里需要减减，否则会跳过值
            }
        }
        return expressionList;
    }

    private static List<String> infixToSuffix(List<String> infixList) {
        Stack<String> charStack = new Stack<>();
        List<String> interResult = new ArrayList<>();

        infixList.forEach(infix -> {
            if (infix.matches("\\d")) {
                interResult.add(infix);
            } else if ("(".equals(infix)) {
                charStack.push(infix);
            } else if (")".equals(infix)) {
                while (!charStack.peek().equals("(")) {
                    interResult.add(charStack.pop());
                }
                //消除(
                charStack.pop();
            }
        });
        return interResult;
    }
}
