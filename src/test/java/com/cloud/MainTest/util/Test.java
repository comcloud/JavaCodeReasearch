/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.MainTest.util;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test.java v1.0 2021/9/30 4:49 下午 zhangyulei Exp $
 */
public interface Test {

    static void of(){
        System.out.println("");
    }

    default void it(){

    }

    Runnable run = () ->{

    };
}
