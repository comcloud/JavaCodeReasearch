package com.cloud.MainTest.datastructure;

/**
 * @version v1.0
 * @ClassName Stack
 * @Author rayss
 * @Datetime 2021/3/29 6:05 下午
 */

public class ArrayStack  {

    private int maxSize;
    private int[] array;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int ele){
        if(isFull()) {
            System.out.println("栈满");
        }
        array[++top] = ele;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return array[--top];
    }
}
