package com.cloud.designpattern.strategymodel;

/**
 * @version v1.0
 * @ClassName ConcreteStrategyB
 * @Author rayss
 * @Datetime 2021/5/28 7:03 下午
 */
class ConcreteStrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}
