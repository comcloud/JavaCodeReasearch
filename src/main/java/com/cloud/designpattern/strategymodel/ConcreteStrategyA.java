package com.cloud.designpattern.strategymodel;

/**
 * @version v1.0
 * @ClassName ConcreteStrategyA
 * @Author rayss
 * @Datetime 2021/5/28 7:03 下午
 */
class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}
