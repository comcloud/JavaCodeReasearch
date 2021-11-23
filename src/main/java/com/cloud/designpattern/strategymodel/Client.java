package com.cloud.designpattern.strategymodel;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/5/28 6:52 下午
 */

public class Client {
    public static void main(String[] args) {
        Context c = new Context();
        Strategy s = new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s = new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }
}
