package com.cloud.MainTest.datastructure;

import java.util.List;

/**
 * 中缀表达式运算，借助了两个栈，一个操作数栈，一个操作符栈
 * @version v1.0
 * @ClassName CalculatorDemo
 * @Author rayss
 * @Datetime 2021/5/12 11:56 上午
 */

public class CalculatorDemo {

    public static void main(String[] args) {

    }

    public static void infix(){
        //一个数据栈，一个操作数栈
        ArrayStack dataStack = new ArrayStack(10);
        ArrayStack operatorStack = new ArrayStack(10);
        String expression = "100+7+9*6-54";
        char[] chars = expression.toCharArray();
        //临时变量，用来记录数值，而不是判断为数字直接压入栈结构
        int stayPush = 0;
        for (char aChar : chars) {
            if (operatorStack.isOperator(aChar)) {
                dataStack.push(stayPush);
                stayPush = 0;
                //此时如果是一个操作符，那就要判断操作符栈中是否为空，如果为空则直接入栈，不为空则判断优先级
                if (!operatorStack.isEmpty()) {
                    if (operatorStack.priority(operatorStack.peek()) >= operatorStack.priority(aChar)) {
                        //此时说明要入栈的操作符优先级比内部的低，所以先从操作数栈中取出两个数计算
                        int num1 = dataStack.pop();
                        int num2 = dataStack.pop();
                        dataStack.push(dataStack.cal(num1, num2, operatorStack.pop()));
                    }
                }
                operatorStack.push(aChar);
            } else {
                stayPush = stayPush * 10 + (aChar - 48);
            }
        }
        //最后需要把这个数字压入栈，因为上述循环后还有一个数字结尾，但是并没有将数据压入栈
        dataStack.push(stayPush);
        while (!operatorStack.isEmpty()) {
            int num1 = dataStack.pop();
            int num2 = dataStack.pop();
            dataStack.push(dataStack.cal(num1, num2, operatorStack.pop()));
        }
        System.out.println("dataStack.peek() = " + dataStack.peek());
    }
    private static class ArrayStack {

        private int maxSize;
        private int[] array;
        private int top = -1;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            array = new int[maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int ele) {
            if (isFull()) {
                System.out.println("栈满");
            }
            array[++top] = ele;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return array[top--];
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return array[top];
        }

        public boolean isOperator(char operator) {
            return operator == '+' || operator == '-' || operator == '*' || operator == '/';
        }

        public int priority(int operator) {
            if (operator == '*' || operator == '/') {
                return 1;
            } else if (operator == '+' || operator == '-') {
                return 0;
            } else {
                //这里假定只有+-*/
                return -1;
            }
        }

        public int cal(int num1, int num2, int operator) {
            switch (operator) {
                case '+':
                    return num1 + num2;
                case '-':
                    return num2 - num1;
                case '*':
                    return num1 * num2;
                case '/':
                    return num2 / num1;
                default:
                    return 0;
            }
        }
    }

}
