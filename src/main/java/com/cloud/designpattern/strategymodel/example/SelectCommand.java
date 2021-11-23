package com.cloud.designpattern.strategymodel.example;

/**
 * @version v1.0
 * @ClassName SelectCommand
 * @Author rayss
 * @Datetime 2021/5/29 8:40 上午
 */

public class SelectCommand implements Strategy{
    @Override
    public void process(String message) {
        System.out.println("command type:"+message);
    }
}
