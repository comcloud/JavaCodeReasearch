package com.cloud.designpattern.strategymodel.example;

/**
 * @version v1.0
 * @ClassName Strategy
 * @Author rayss
 * @Datetime 2021/5/29 8:39 上午
 */

public interface Strategy {
    /**
     * 处理消息方法
     * @param message 消息
     */
    void process(String message);
}
