package com.cloud.MainTest.datastructure;

/**
 * @version v1.0
 * @ClassName Stack
 * @Author rayss
 * @Datetime 2021/3/29 6:06 下午
 */

public interface Stack <E>{
    public E push(E e);

    public E pop();

    E peek();

    boolean empty();
}
