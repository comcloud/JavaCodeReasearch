package com.cloud.designpattern.strategymodel.example;

import java.util.Map;

/**
 * @version v1.0
 * @ClassName StrategyContext
 * @Author rayss
 * @Datetime 2021/5/29 8:44 上午
 */

public class StrategyContext {
    public Strategy getStrategy(String commandType){
        Strategy strategy = null;
        Map<String, String> allClazz = CommandEnum.getAllClazz();
        String clazz = allClazz.get(commandType.trim().toLowerCase());
        try {
            strategy = (Strategy) Class.forName(clazz).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return strategy;
    }
}
