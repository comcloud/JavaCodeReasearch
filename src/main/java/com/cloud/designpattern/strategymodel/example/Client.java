package com.cloud.designpattern.strategymodel.example;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/5/29 8:46 上午
 */

public class Client {
    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext();
        Strategy select = strategyContext.getStrategy("select");
        select.process("你好");
    }
}
