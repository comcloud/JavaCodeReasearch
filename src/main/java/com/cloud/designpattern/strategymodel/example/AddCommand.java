package com.cloud.designpattern.strategymodel.example;

/**
 * @author rayss
 */
public class AddCommand implements Strategy {
 
    @Override
    public void process(String message) {
        System.out.println("command type:"+message);
 
    }
}

